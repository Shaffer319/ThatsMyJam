/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam.data;

/**
 *
 * @author bean51591
 */
public class Song {

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */
    private int songID;
    private String songName;
    private String artistName;
    private String albumName;
    private int releaseYear;
    //private String imageName;

    /**
     * Constructor
     */
    public Song() {
    }

    /**
     * @return the songID
     */
    public int getSongID() {
        return songID;
    }

    /**
     * @param songID the songID to set
     */
    public void setSongID(int songID) {
        this.songID = songID;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    /**
     * @return the songName
     */
    public String getSongName() {
        return songName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    /**
     * @return the releaseYear
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * @param releaseYear the releaseYear to set
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * @return the imageName
     *
     * public String getImageName() { return imageName; }
     *
     *
     * @param imageName the imageName to set
     *
     * public void setImageName(String imageName) { this.imageName = imageName;
     * }
     *
     */
}
