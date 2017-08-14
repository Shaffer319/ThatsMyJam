/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam;

import java.io.Serializable;
import java.sql.ResultSet;

/**
 *
 * @author cpour
 */
public class InfoBean implements Serializable {

    private String artistID;
    private String albumID;
    
    public InfoBean()
    {
    }
    
    /**
     * Getter method for the ArtistID
     * 
     * @return - The Artist ID
     */
    public String getArtistID()
    {
        return artistID;
    }
    
    /**
     * Setter method for the ArtistID
     * 
     * @param artistID - ArtistID to set
     */
    public void setArtistID(String artistID)
    {
        this.artistID = artistID;
    }
    
    /**
     * Getter method for the AlbumID
     * 
     * @return - The Album ID
     */
    public String getAlbumID()
    {
        return albumID;
    }
    
    /**
     * Setter method for the AlbumID
     * 
     * @param albumID - Album ID to set
     */
    public void setAlbumID(String albumID)
    {
        this.albumID = albumID;
    }
    
    /**
     * Gets the path to the image for the artist using the artist id stored in the bean
     * 
     * @return - The Artist's image path
     */
    public String getArtistImageLoc()
    {
        String query = "select ImageName from Artist where ArtistID = " + artistID;
         
        return getImageLoc(query);
    }
            
    /**
     * Gets the path to the image for the album using the album id stored in the bean
     * @return 
     */
    public String getAlbumImageLoc()
    {
        String query = "select ImageName from Album where AlbumID = " + albumID;
        
        return getImageLoc(query);
    }
    
    /**
     * Returns the image path based on the query passed in
     * @param query - Query to find the image of an Artist/Album
     * @return Relative path to the image
     */
    public String getImageLoc(String query)
    {
        try
        {
            ResultSet results = DatabaseConnector.getInstance().executeQuery(query);
            String path = "images/" + results.getString("ImageName");
            return path;
        }
        catch(Exception e)
        {
            return "";
        }
    }
}
