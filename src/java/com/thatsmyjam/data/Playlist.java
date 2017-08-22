/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam.data;

/**
 *
 * @author mshaffer
 */
public class Playlist {

    private int playlistID;
    private String playlistName;
//    private List<String>

    public Playlist() {

    }

    public int getPlaylistID() {
        return this.playlistID;
    }

    void setPlaylistID(int playlistID) {
        this.playlistID = playlistID;
    }

    public String getPlaylistName() {
        return this.playlistName;
    }

    void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

}
