/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam.servlets;

import com.thatsmyjam.beans.CartBean;
import com.thatsmyjam.beans.InfoBean;
import com.thatsmyjam.beans.Item;
import com.thatsmyjam.data.DBUtil;
import static com.thatsmyjam.data.DBUtil.executeSongInsertUpdate;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bean51591
 */
@WebServlet(name = "ShoppingCart", urlPatterns = {"/ShoppingCart"})
public class ShoppingCart extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShoppingCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShoppingCart at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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

        InfoBean info = (InfoBean) request.getSession(false).getAttribute("infoBean");
        CartBean cart = (CartBean) request.getSession(false).getAttribute("cartBean");
        if (cart == null) {
            cart = new CartBean();
            HttpSession session = request.getSession();
            session.setAttribute("cartBean", cart);
        }

        if (request.getParameter("addAlbumCart") != null) {
            Item itm = new Item(info.getAlbumName(), info.getImageName(), info.getArtistName(), info.getArtistID(), info.getAlbumName(), info.getAlbumID(), null, 0, info.getAlbumPrice());
            cart.addItem(itm);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cart.jsp");
            dispatcher.forward(request, response);
        } else if ((request.getParameter("song") != null)) {
            String[] songs = request.getParameterValues("song");
            String song = songs[0];
            String delim = "_";
            String[] songNameID = song.split(delim);
            Item itm = new Item(songNameID[0], "song_icon.png", info.getArtistName(), info.getArtistID(), info.getAlbumName(), info.getAlbumID(), songNameID[0], Integer.parseInt(songNameID[1]), .6f);
            cart.addItem(itm);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cart.jsp");
            dispatcher.forward(request, response);
        } else if ((request.getParameter("remove") != null)) {
            String[] removeItm = request.getParameterValues("remove");
            out.println(removeItm[0]);
            cart.removeItem(removeItm[0]);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cart.jsp");
            dispatcher.forward(request, response);
        } else if ((request.getParameter("continue_shop") != null)) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/homepage.jsp");
            dispatcher.forward(request, response);
        } else if ((request.getParameter("final") != null)) {
            try {
                cart.setPurchasedDate(); //maybe store the date purchased?
                ResultSet results;
                String sql_song;
                String sql_albumsong;
                boolean update = true;
                //*********************************************************************Replace 1 with the actual user ID*************************************************
                String getUser = "SELECT UserID FROM OwnedSongs WHERE OwnedSongs.UserID = 1";
                results = DBUtil.executeSelect(getUser);
                //*****************************update userID here *******************************************
                if (results == null) {
                    sql_song = "INSERT INTO OwnedSongs(UserID, SongID) VALUES (1,?)";
                } else {
                    sql_song = "UPDATE OwnedSongs SET SongID=? WHERE UserID=1";
                }
                for (int i = 0; i < cart.getNumItems(); ++i) {
                    //if the item in the cart is a song
                    if (cart.getItems().get(i).getSongID() != 0) {
                        try {
                            int[] songA = {cart.getItems().get(i).getSongID()};
                            int rowsUpdated = executeSongInsertUpdate(sql_song, songA);
                            if (rowsUpdated > 0) {
                                System.out.println("A new user was inserted successfully!");
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //if the item is an album get all the songs 
                    } else {
                        sql_albumsong = "SELECT SongID FROM Song INNER JOIN Album ON Album.AlbumID = Song.AlbumID WHERE Album.AlbumID = " + cart.getItems().get(i).getAlbumID();
                        results = DBUtil.executeSelect(sql_albumsong);
                        results.last();
                        int totalRows = results.getRow();
                        int[] songA2 = {};
                        results.beforeFirst();
                        for (int j = 0; j < totalRows; j++) {
                            while (results.next()) {
                                int songID = results.getInt("songID");
                                songA2[j] = songID;
                            }
                        }
                        int rowsUpdated2 = executeSongInsertUpdate(sql_albumsong, songA2);
                        if (rowsUpdated2 > 0) {
                            System.out.println("A new user was inserted successfully!");
                        }
                    }

                }
                //   RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/checkout.jsp");
                //   dispatcher.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
