/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam.servlets;

import com.thatsmyjam.beans.InfoBean;
import com.thatsmyjam.data.DBUtil;
import com.thatsmyjam.data.PlaylistDB;
import com.thatsmyjam.data.User;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cpour
 */
@WebServlet(name = "Playlists", urlPatterns = {"/Playlists/*"})
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
        User user = (User) request.getSession().getAttribute("user");

        String url = "/playlists.jsp";
        String requestURI = request.getRequestURI().split(";")[0]; // Remove the session id

        if (user == null) {
            // They have to login go through profile controller then back to here
            url = "/profileController/playlists";

        } else if (requestURI.endsWith("CreateNewPlaylist")) {
            url = handleCreateNewPlaylist(user, request, response);

        } else if (requestURI.endsWith("AddSongToPlaylist")) {
            url = handleAddSongToPlaylist(user, request, response);

        } // Display Songs in playlist
        else if (playlist != null && !playlist.trim().equals("")) {
            String query = "SELECT Song.SongName FROM SongsInPlaylist "
                    + "INNER JOIN SONG ON Song.SongID = SongsInPlaylist.SongID "
                    + "WHERE SongsInPlaylist.PlaylistID = " + playlist;

            String playlistname = PlaylistDB.getPlaylistName(Integer.parseInt(playlist));

            try {
                ResultSet results = DBUtil.executeSelect(query);
                String html = "";

                html += "<h1> Playlist: " + playlistname + " </h1>";
                html += "<ul style=\"list-style: none;\">";

                if (results.isBeforeFirst()) {
                    while (results.next()) {
                        html += "<li>" + results.getString("Song.SongName") + "</li>";
                    }
                    html += "</ul>";
                } else {
                    html += "<h1> Playlist is empty! </h1>";
                }

                playlistBean.setPlaylistResults(html);
            } catch (SQLException e) {
                String html = "<p> An error occurred while processing your request, try again</p>" + e.getMessage();
                playlistBean.setPlaylistResults(html);
            } finally {
                DBUtil.closeSelectObjects();
            }
        } else {// Display list of all playlists
            String query = "SELECT PlaylistID, PlaylistName FROM Playlist WHERE UserID = "
                    + user.getUserID();

            try {
                ResultSet results = DBUtil.executeSelect(query);
                String html = "";
                if (results.isBeforeFirst()) {
                    html += "<h1> Your Playlists </h1><ul style=\"list-style: none;\">";
                    while (results.next()) {
                        html += "<a href=/ThatsMyJam/Playlists?playlist=" + results.getInt("PlaylistID") + ">"
                                + "<li>" + results.getString("PlaylistName") + "</li></a>";

                    }
                }

                // Create new Playlist link at the end of the results
                if (html.equals("")) {
                    html += "<h1> No Playlists found </h1><ul style=\"list-style: none;\">";
                    // TODO list item to make a new playlist
                    html += "</ul>";
                } else {
                    // TODO list item to make a new playlist
                }
                playlistBean.setPlaylistResults(html);
            } catch (SQLException e) {
                String html = "<p>An error occurred while processing your request, try again</p> " + e.getMessage();
                playlistBean.setPlaylistResults(html);
            } finally {
                DBUtil.closeSelectObjects();
            }
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private String handleCreateNewPlaylist(User user, HttpServletRequest request, HttpServletResponse response) {
        String playlistName = request.getParameter("playlistName");

        // validation
        if (playlistName == null) {
            request.setAttribute("message", "Could not add Playlist '" + playlistName + "' to database.");
            return "/mySongs.jsp";

        } else if (playlistName.trim().isEmpty()) {
            request.setAttribute("message", "Could not add Playlist '" + playlistName + "' to database.");
            return "/mySongs.jsp";

        } else if (playlistName.length() > 30) {
            request.setAttribute("message", "Could not add Playlist '" + playlistName + "' to database greater than max length.");
            return "/mySongs.jsp";

        } else {

            // do work
            int count = PlaylistDB.createNewPlaylist(user.getUserID(), playlistName);

            // Post validation
            // Todo check if user has a playlist with this name
            if (count == 0) {
                request.setAttribute("message", "Could not add Playlist '" + playlistName + "' to database.");
                return "/mySongs.jsp";

            }
        }
        return "/Playlists";

    }

    private String handleAddSongToPlaylist(User user, HttpServletRequest request, HttpServletResponse response) {
        String sPlaylistID = request.getParameter("playlistID");
        String sSongID = request.getParameter("songID");
        int playlistID = 0;
        try {
            playlistID = Integer.parseInt(sPlaylistID);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid playlistID.");
            return "/mySongs.jsp";
        }
        int songID = 0;
        try {
            songID = Integer.parseInt(sSongID);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid songID.");
            return "/mySongs.jsp";
        }

        int count = PlaylistDB.addSongToPlayList(playlistID, songID);
        return "/Playlists?playlist=" + playlistID;

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
