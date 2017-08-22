/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam.servlets;

import com.thatsmyjam.beans.InfoBean;
import com.thatsmyjam.data.DBUtil;
import com.thatsmyjam.data.User;
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
 * @author cpour
 */
@WebServlet(name = "Playlists", urlPatterns = {"/Playlists"})
public class Playlists extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        String playlist = request.getParameter("playlist");
        
        InfoBean playlistBean = new InfoBean();
        request.getSession().setAttribute("playlistBean", playlistBean);
        User user = (User)request.getSession().getAttribute("user");
        String url = "/playlists.jsp";
        
        if(user == null){
            // They have to login go through profile controller then back to here
            url = "/profileController/playlists";
        }
        else
        if(playlist != null && !playlist.trim().equals(""))
        {
            String query = "SELECT SongName, PlaylistName FROM SongsInPlaylist "
                         + "INNER JOIN Playlist ON Playlist.PlaylistID = SongsInPlaylist.PlaylistID "
                         + "WHERE SongsInPlaylist.PlaylistID = " + playlist
                         + " AND Playlist.UserID = " + user.getUserID(); //InfoBean.getCurrentUser().getUserID();
            
            try
            {
                ResultSet results = DBUtil.executeSelect(query);
                String html = "";
                if(results.isBeforeFirst())
                {
                    html += "<h1> " + results.getString("PlaylistName") + " </h1>";
                    html += "<ul style=\"list-style: none;\">";
                    while(results.next())
                    {
                        html += "<li>" + results.getString("SongName") + "</li>";
                    }
                    html += "</ul>";
                }
                else
                {
                    html += "<h1> Playlist not found </h1>";
                }
                playlistBean.setPlaylistResults(html);
            }
            catch(SQLException e)
            {
                String html = "<p> An error occurred while processing your request, try again</p>";
                playlistBean.setPlaylistResults(html);
            }
            finally
            {
                DBUtil.closeSelectObjects();
            }
        }
        else
        {
            String query = "SELECT PlaylistID, PlaylistName FROM Playlist WHERE UserID = "
                    + user.getUserID();

            try
            {
                ResultSet results = DBUtil.executeSelect(query);
                String html = "";
                if(results.isBeforeFirst())
                {
                    html += "<h1> Your Playlists </h1><ul style=\"list-style: none;\">";
                    while(results.next())
                    {
                        html += "<a href=/ThatsMyJam/Playlists?playlist=" + results.getInt("PlaylistID") + ">"
                              + "<li>" + results.getString("PlaylistName") + "</li></a>";
                                
                    }
                }
                
                // Create new Playlist link at the end of the results
                if(html.equals(""))
                {
                    html += "<h1> No Playlists found </h1><ul style=\"list-style: none;\">";
                    // TODO list item to make a new playlist
                    html += "</ul>";
                }
                else
                {
                    // TODO list item to make a new playlist
                }
                playlistBean.setPlaylistResults(html);
            }
            catch(SQLException e)
            {
                String html = "<p>An error occurred while processing your request, try again</p>";
                playlistBean.setPlaylistResults(html);
            }
            finally 
            {
                DBUtil.closeSelectObjects();
            }
        }
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher;
        dispatcher = servletContext.getRequestDispatcher(url);
        dispatcher.forward(request, response);
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
        return "Short description";
    }// </editor-fold>

}
