/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam;

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
        
        String artistID = (String)request.getAttribute("artist");
        
        if(artistID != null && !artistID.trim().equals(""))
        {
            request.getSession().setAttribute("artist", artistID);
            dispatcher = servletContext.getRequestDispatcher("/info.jsp");
            dispatcher.forward(request, response);
        }
        
        String albumID = (String)request.getAttribute("album");
        
        if(albumID != null && !albumID.trim().equals(""))
        {
            request.getSession().setAttribute("album", albumID);
            dispatcher = servletContext.getRequestDispatcher("/info.jsp");
            dispatcher.forward(request, response);
        }
        
        String searchValue = (String)request.getAttribute("search");
        
        if(searchValue != null && !searchValue.trim().equals(""))
        {
            try
            {
                htmlOutput = "";
                
                String query = "SELECT ArtistID, ArtistName FROM Artist "
                             + "WHERE ArtistName LIKE '%{" + searchValue + "}%'";
                ResultSet artistsFound = DBUtil.executeSelect(query);

                boolean artists, albums, songs;
                artists = albums = songs = false;
                
                // This checks if there is a first record otherwise the Artist section will be skipped
                if(artistsFound.isBeforeFirst())
                {
                    artists = true;
                    htmlOutput += "<h1> Artists </h1><ul>";
                    while(artistsFound.next())
                    {
                        htmlOutput += "<a href=/Search?artist=" + artistsFound.getString("ArtistID") + ">";
                        htmlOutput += "<li>" + artistsFound.getString("ArtistName") + "</li></a>";
                    }
                    htmlOutput += "</ul>";
                }
                DBUtil.closeSelectObjects();
                
                
                query = "SELECT AlbumID, AlbumName FROM Album "
                      + "WHERE AlbumName LIKE '%{" + searchValue + "}%'";
                ResultSet albumsFound = null;//DC.getInstance().executeQuery(query);

                if(albumsFound.isBeforeFirst())
                {
                    albums = true;
                    while(albumsFound.next())
                    {

                    }
                }
                DBUtil.closeSelectObjects();
                
                query = "SELECT AlbumID, SongName FROM Song "
                      + "INNER JOIN Album ON Album.AlbumID = Song.AlbumID "
                      + "WHERE SongName LIKE '%{" + searchValue + "}%'";
                ResultSet songsFound = DBUtil.executeSelect(query);

                if(songsFound.isBeforeFirst())
                {
                    songs = true;
                    while(songsFound.next())
                    {
                    }
                }
                DBUtil.closeSelectObjects();
                
                if(!artists && !albums && !songs)
                {
                    htmlOutput = "<p>No results were found</p>";
                }
                
                dispatcher = servletContext.getRequestDispatcher("/searchResults.jsp");
                dispatcher.forward(request, response);
            }
            catch(SQLException e)
            {
                System.out.println(e);
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
