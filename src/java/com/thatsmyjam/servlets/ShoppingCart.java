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
import static com.thatsmyjam.data.DBUtil.executeSongInsert;
import com.thatsmyjam.data.User;
import java.io.IOException;
import static java.lang.System.out;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        
//        HttpSession session = request.getSession(false);
//        if(session == null)
//        {
//            throw new NullPointerException("getSession(false) will return null and the getAttribute throw nullptr");
//        }
        
        InfoBean info = (InfoBean) request.getSession().getAttribute("infoBean");
        CartBean cart = (CartBean) request.getSession().getAttribute("cartBean");
        User user = (User) request.getSession().getAttribute("user");

        if (cart == null) 
        {
            cart = new CartBean();
            HttpSession session = request.getSession();
            session.setAttribute("cartBean", cart);
        }

        if (request.getParameter("addAlbumCart") != null) 
        {
            Item itm = new Item(info.getAlbumName(), info.getImageName(), info.getArtistName(), info.getArtistID(), info.getAlbumName(), info.getAlbumID(), null, 0, info.getAlbumPrice());
            cart.addItem(itm);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cart.jsp");
            dispatcher.forward(request, response);
        } 
        else if ((request.getParameter("song") != null)) 
        {
            String[] songs = request.getParameterValues("song");
            String song = songs[0];
            String delim = "_";
            String[] songNameID = song.split(delim);
            Item itm = new Item(songNameID[0], "song_icon.png", info.getArtistName(), info.getArtistID(), info.getAlbumName(), info.getAlbumID(), songNameID[0], Integer.parseInt(songNameID[1]), .6f);
            cart.addItem(itm);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cart.jsp");
            dispatcher.forward(request, response);
        } 
        else if ((request.getParameter("remove") != null)) 
        {
            String[] removeItm = request.getParameterValues("remove");
            out.println(removeItm[0]);
            cart.removeItem(removeItm[0]);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cart.jsp");
            dispatcher.forward(request, response);
        } 
        else if ((request.getParameter("continue_shop") != null)) 
        {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/homepage.jsp");
            dispatcher.forward(request, response);
        } 
        else if ((request.getParameter("checkout") != null)) 
        {
            if(user == null)
            {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("profileController/cart");
                dispatcher.forward(request, response);
                return;
            }
            
            cart.setPurchasedDate(); //maybe store the date purchased?
            ResultSet results;
            String sql_albumsong;
            
            String sql_song = "INSERT INTO OwnedSongs(UserID, SongID) VALUES (?,?)";

            for (int i = 0; i < cart.getNumItems(); i++) 
            {
                //if the item in the cart is a song
                if (cart.getItems().get(i).getSongID() != 0) 
                {
                    try 
                    {
                        executeSongInsert(sql_song, user.getUserID(), cart.getItems().get(i).getSongID());

                    } catch (SQLException ex) {
                        Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    finally
                    {
                        DBUtil.closeInsertObjects();
                    }
                //if the item is an album get all the songs 
                } 
                else 
                {
                    ArrayList<Integer> songIDs = new ArrayList<Integer>();
                    try
                    {
                        sql_albumsong = "SELECT SongID FROM Song "
                                      + "INNER JOIN Album ON Album.AlbumID = Song.AlbumID "
                                      + "WHERE Album.AlbumID = " + cart.getItems().get(i).getAlbumID();
                        results = DBUtil.executeSelect(sql_albumsong);

                        if(results.isBeforeFirst())
                        {
                            while(results.next())
                            {
                                songIDs.add(results.getInt("SongID"));
                            }
                        }
                    }
                    catch(SQLException e)
                    {
                        // ?
                    }
                    finally
                    {
                        DBUtil.closeSelectObjects();
                    }

                    try
                    {
                        for(int id : songIDs)
                        {
                            executeSongInsert(sql_song, user.getUserID(), id);
                            DBUtil.closeInsertObjects();
                        }
                    }
                    catch(SQLException e)
                    {
                        // ?
                    }
                    finally
                    {
                        DBUtil.closeInsertObjects();
                    }
                }

            }
            // Empty the cart and forward the no response message
            cart.setItems(new ArrayList());
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/checkout.jsp");
            dispatcher.forward(request, response);
        }
    }
}
