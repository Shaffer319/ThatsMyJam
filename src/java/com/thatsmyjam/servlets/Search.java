/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam.servlets;

import com.thatsmyjam.beans.InfoBean;
import com.thatsmyjam.data.DBUtil;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cpournaras11
 */
@WebServlet(name = "Search", urlPatterns = {"/Search"})
public class Search extends HttpServlet {

    private String htmlOutput = "";
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher;
        response.setContentType("text/html;charset=UTF-8");
        
        String searchValue = (String)request.getParameter("search");
        
        if(searchValue != null && !searchValue.trim().equals(""))
        {
            try
            {
                htmlOutput = "";
                
                String query = "SELECT ArtistID, ArtistName FROM Artist "
                             + "WHERE ArtistName LIKE '%" + searchValue + "%'";
                ResultSet artistsFound = DBUtil.executeSelect(query);

                // This checks if there is a first record otherwise the Artist section will be skipped
                if(artistsFound.isBeforeFirst())
                {
                    htmlOutput += "<h1> Artists </h1><ul style=\"list-style: none;\">";
                    while(artistsFound.next())
                    {
                        htmlOutput += "<a href=/ThatsMyJam/info.jsp?artist=" + artistsFound.getString("ArtistID") + ">";
                        htmlOutput += "<li>" + artistsFound.getString("ArtistName") + "</li></a>";
                    }
                    htmlOutput += "</ul>";
                }
                DBUtil.closeSelectObjects();
                
                query = "SELECT AlbumID, AlbumName FROM Album "
                      + "WHERE AlbumName LIKE '%" + searchValue + "%'";
                ResultSet albumsFound = DBUtil.executeSelect(query);

                if(albumsFound.isBeforeFirst())
                {
                    htmlOutput += "<h1> Albums </h1><ul style=\"list-style: none;\">";
                    while(albumsFound.next())
                    {
                        htmlOutput += "<a href=/ThatsMyJam/info.jsp?album=" + albumsFound.getString("AlbumID") + ">";
                        htmlOutput += "<li>" + albumsFound.getString("AlbumName") + "</li></a>";
                    }
                    htmlOutput += "</ul>";
                }
                DBUtil.closeSelectObjects();
                
                query = "SELECT SongID, SongName, ArtistName FROM Song "
                      + "INNER JOIN Artist ON Artist.ArtistID = Song.ArtistID "
                      + "WHERE SongName LIKE '%" + searchValue + "%'";
                ResultSet songsFound = DBUtil.executeSelect(query);

                if(songsFound.isBeforeFirst())
                {
                    htmlOutput += "<h1> Songs </h1><ul style=\"list-style: none;\">";
                    while(songsFound.next())
                    {
                        htmlOutput += "<li>" + songsFound.getString("SongName") + " by " + songsFound.getString("ArtistName") + "</li>";
                    }
                }
                DBUtil.closeSelectObjects();
                
                if(htmlOutput.equals(""))
                {
                    htmlOutput = "<p>No results were found</p>";
                }
                InfoBean bean = new InfoBean();
                bean.setSearchResults(htmlOutput);
                request.getSession().setAttribute("searchBean", bean);
                dispatcher = servletContext.getRequestDispatcher("/searchResults.jsp");
                dispatcher.forward(request, response);
            }
            catch(SQLException e)
            {
                InfoBean bean = new InfoBean();
                bean.setSearchResults(e.getMessage());
                request.getSession().setAttribute("searchBean", bean);
                dispatcher = servletContext.getRequestDispatcher("/searchResults.jsp");
                dispatcher.forward(request, response);
            }
            finally
            {
                DBUtil.closeSelectObjects();
            }
        } // Do not forward if no search is being performed
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Performs the Search functionality if the Search Bar is used, or "
                + "navigates to an Album/Artist when selected.";
    }// </editor-fold>

    /**
     * Getter method for the htmlOutput results of the search
     * @return - HTML to display to the user
     */
    public String getHtmlOutput()
    {
        return htmlOutput;
    }
}
