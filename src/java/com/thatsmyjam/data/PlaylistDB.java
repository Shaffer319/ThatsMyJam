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
import java.util.List;

/**
 *
 * @author mshaffer
 */
public class PlaylistDB {

    public static String getPlaylistName(int playlistID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "SELECT PlaylistName FROM Playlist "
                + "WHERE PlaylistID = " + playlistID;

        String playlistName = "";

        try {
            ps = connection.prepareStatement(query);
            ResultSet result = ps.executeQuery(query);

            result.next();
            playlistName = result.getString("PlaylistName");

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

        return playlistName;
    }

    public static List<Playlist> getPlaylistNamesForUser(int UserID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        List<Playlist> list = new ArrayList<>();

        String query = "SELECT PlaylistID, PlaylistName FROM Playlist WHERE UserID = "
                + UserID;
        try {
            ps = connection.prepareStatement(query);
            ResultSet results = ps.executeQuery(query);
            while (results.next()) {
                Playlist playlist = new Playlist();

                playlist.setPlaylistID(results.getInt("PlaylistID"));
                playlist.setPlaylistName(results.getString("PlaylistName"));

                list.add(playlist);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

        return list;
    }

    public static int createNewPlaylist(int userID, String playlistName) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT into Playlist(UserID, PlaylistName) values (?, ?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            ps.setString(2, playlistName);

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int addSongToPlayList(int playlistID, int songID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT into SongsInPlaylist(PlaylistID, SongID) values (?, ?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, playlistID);
            ps.setInt(2, songID);

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
