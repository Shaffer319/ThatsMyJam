/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam.data;

import java.util.ArrayList;

/**
 *
 * @author cpournaras11
 */
public class Album 
{
    private int albumID;
    private String albumName;
    private String artistName;
    private int artistID;
    private int releaseYear;
    private String imageName;
    private float albumPrice;
    private ArrayList<String> songList;
    
    public String getArtistName(){
        return this.artistName;
    }
    public void setArtistName(String artistName){
        this.artistName = artistName;
    }
    
    /**
     * Constructor
     */
    public Album(){
    }

    /**
     * @return the albumID
     */
    public int getAlbumID() {
        return albumID;
    }

    /**
     * @param albumID the albumID to set
     */
    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    /**
     * @return the albumName
     */
    public String getAlbumName() {
        return albumName;
    }

    /**
     * @param albumName the albumName to set
     */
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
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
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * @param imageName the imageName to set
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    
     /**
     * @return the albumPrice
     */
    public float getAlbumPrice() {
        return albumPrice;
    }

    /**
     * @param albumPrice the albumPrice to set
     */
    public void setAlbumPrice(float albumPrice) {
        this.albumPrice = albumPrice;
    }
    
    public void setSongList(ArrayList<String>songList){
        this.songList=songList;
    }
    
    public ArrayList<String> getSongList(){
        return songList;
    }
    
}
