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
    private int artistID;
    private int releaseYear;
    //private String imageName;
    
    /**
     * Constructor
     */
    public Song(){
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

    /**
     * @return the songName
     */
    public String getSongName() {
        return songName;
    }

    /**
     * @param songName the songName to set
     */
    public void setAlbumName(String songName) {
        this.songName = songName;
    }

    /**
     * @return the artistID
     */
    public int getArtistID() {
        return artistID;
    }

    /**
     * @param artistID the artistID to set
     */
    public void setArtistID(int artistID) {
        this.artistID = artistID;
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
     
    public String getImageName() {
        return imageName;
    }

    
     * @param imageName the imageName to set
     
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    * */

}
