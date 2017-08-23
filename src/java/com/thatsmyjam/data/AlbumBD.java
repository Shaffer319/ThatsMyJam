/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mike
 */
public class AlbumBD {

    public static Album getAlbum(int AlbumID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "SELECT AlbumName, ArtistName FROM Album "
                + "INNER JOIN Artist ON Artist.ArtistID = Album.ArtistID "
                + "WHERE AlbumID = " + AlbumID;
        try {
            ps = connection.prepareStatement(query);

            ResultSet resultSet = ps.executeQuery();
            resultSet.next();

            Album album = new Album();

            album.setAlbumName(resultSet.getString("AlbumName"));
            album.setArtistName(resultSet.getString("ArtistName"));
            
            return album;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
