/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam.data;

/**
 *
 * @author cpournaras11
 */
public class Artist 
{
    private int artistID;
    private String artistName;
    private String imageName;
  
    /**
     * Constructor
     */
    public Artist(){}

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
     * @return the artistName
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * @param artistName the artistName to set
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
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
}
