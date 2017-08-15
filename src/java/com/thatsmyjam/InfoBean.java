/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam;

import com.thatsmyjam.data.ConnectionPool;
import com.thatsmyjam.data.DBUtil;
import java.io.Serializable;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author cpournaras11
 */
public class InfoBean implements Serializable {

    private String artistID;
    private String albumID;
    private boolean isArtist;
    
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
     * Getter method for isArtist
     * 
     * @return - If the infoBean is for an Artist, otherwise Album
     */
    public boolean getIsArtist()
    {
        return isArtist;
    }
    
    /**
     * Setter method for isArtist
     * 
     * @param isArtist - Boolean to set
     */
    public void setIsArtist(boolean isArtist)
    {
        this.isArtist = isArtist;
    }
    
    /**
     * Gets an HTML formatted section for the album/artist id stored in the bean
     * 
     * @return - HTML to display to the user
     */
    public String getPage()
    {
        String query = "";
        
        if(isArtist)
        {
            query = "SELECT Album.AlbumID, Album.AlbumName, Album.ReleaseYear, "
                    + "Album.ImageName as AlbumImage, Artist.ImageName, as ArtistImage, Artist.ArtistName"
                    + "FROM Album INNER JOIN Artist ON Artist.ArtistID = Album.ArtistID "
                    + "WHERE ArtistID = " + artistID;
        }
        else 
        {
            query = "SELECT Artist.ArtistID, Artist.ArtistName, Album.ReleaseYear, "
                    + "Album.ImageName, Album.AlbumName FROM Artist INNER JOIN Album ON "
                    + "Album.ArtistID = Artist.ArtistID WHERE AlbumID = " + albumID;
        }
        
        String html = "";
        
        try
        {
            ResultSet results = DBUtil.executeSelect(query);
            
            String image = "<div class=\"col-lg-3 col-md-4 col-xs-6 thumb\">"
                    + "<img class=\"img-responsive\" src=\"SOURCE\" alt=\"ALT\" "
                    + "width=\"500\" height=\"500\"></div>";
            
            if(isArtist)
            {
                boolean first = true;
                while(results.next())
                {
                    if(first)
                    {
                        String artist = results.getString("ArtistName");
                        String newImage = image.replace("SOURCE", "images/" + results.getString("ArtistImage"));
                        newImage = newImage.replace("ALT", artist);
                        first = false;
                        html += newImage + "<h1>" + artist + "</h1>";
                        
                        image = image.replaceAll("500", "350");
                    }
                    
                    // TODO Formatting html output
                    html += image.replace("SOURCE", "images/" + results.getString("AlbumImage"));
                    html += results.getString("AlbumName") + " " + results.getString("ReleaseYear");
                }
            }
            else
            {
                html += "<h1><a href=/Search?artist=" + results.getString("ArtistID") 
                        + ">" + results.getString(html) + "</a></h1>";
                
                query = "SELECT SongName FROM Song INNER JOIN Album ON Album.AlbumID = Song.AlbumID WHERE AlbumID = " + albumID;
                
                results = DBUtil.executeSelect(query);
                
                // TODO Formatting HTML output
                html += "<ul style=\"list-style:none;\">";
                while(results.next())
                {
                    html += "<li>" + results.getString("SongName") + "</li>";
                }
                html += "</ul>";
            }
        }
        catch(Exception e)
        {
            html = "<p>An error occurred while processing the request</p>";
        }
        return html;
    }
}
