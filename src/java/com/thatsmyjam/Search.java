/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cpour
 */
@WebServlet(name = "Search", urlPatterns = {"/Search"})
public class Search extends HttpServlet {

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
            dispatcher = servletContext.getRequestDispatcher("/artist.jsp");
            dispatcher.forward(request, response);
        }
        
        String albumID = (String)request.getAttribute("album");
        
        if(albumID != null && !albumID.trim().equals(""))
        {
            dispatcher = servletContext.getRequestDispatcher("/album.jsp");
            dispatcher.forward(request, response);
        }
        
        String searchValue = (String)request.getAttribute("search");
        
        if(!searchValue.trim().equals(""))
        {
            try
            {
                String query = "select ArtistName from Artist where ArtistName like '%{" + request.getAttribute("search") + "}%'";
                ResultSet artistsFound = DatabaseConnector.getInstance().executeQuery(query);

                query = "select AlbumName from Album where AlbumName like '%{" + request.getAttribute("search") + "}%'";
                ResultSet albumsFound = DatabaseConnector.getInstance().executeQuery(query);

                query = "select SongName from Song where SongName like '%{" + request.getAttribute("search") + "}%'";
                ResultSet songsFound = DatabaseConnector.getInstance().executeQuery(query);

                // TODO Use ResultSet to generate html/jsp to display to the user on search results page
                
                dispatcher = servletContext.getRequestDispatcher("/searchResults.jsp");
                dispatcher.forward(request, response);
            }
            catch(Exception e)
            {
                // TODO Error message/error page
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

}
