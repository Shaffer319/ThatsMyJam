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
 * 
 * Bean to obtain generic (not-user based) information from the database
 */
public class InfoBean implements Serializable {

    private String artistID;
    private String albumID;
    private boolean isArtist;
    
    private final String HREF_REP = "HREF_REPLACE";
    private final String TYPE_REP = "TYPE_REPLACE";
    private final String HREF_VAL = "HREF_VALUE";
    private final String HREF_LINK = "href=\"/Search?" + TYPE_REP + "=" + HREF_VAL + "\"";
    private final String SRC_REP = "SOURCE_REPLACE";
    private final String ALT_REP = "ALT_REPLACE";
    private final String SIZE_REP = "SIZE_REPLACE";
    private final String IMAGE = "<div class=\"col-lg-3 col-md-4 col-xs-6 thumb\">"
                         + "<a class=\"thumbnail\" " + HREF_REP + ">"
                         + "<img class=\"img-responsive\" src=\"images/" + SRC_REP + "\" "
                         + "alt=\"" + ALT_REP + "\" " + SIZE_REP + "></a></div>";
    
    /**
     * Constructor
     */
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
            
            if(isArtist)
            {
                boolean first = true;
                while(results.next())
                {
                    if(first)
                    {
                        String artist = results.getString("ArtistName");
                        String newImage = IMAGE.replace(SRC_REP, "images/" + results.getString("ArtistImage"))
                                               .replace(ALT_REP, artist)
                                               .replace(HREF_REP, "")
                                               .replace(SIZE_REP, "width=\"500\" height=\"500\"");
                        first = false;
                        html += newImage + "<h1>" + artist + "</h1>";
                    }
                    String albumImage = IMAGE.replace(SRC_REP, "images/" + results.getString("AlbumImage"))
                                             .replace(ALT_REP, results.getString("AlbumName"))
                                             .replace(HREF_REP, HREF_LINK)
                                             .replace(TYPE_REP, "album")
                                             .replace(HREF_VAL, results.getString("AlbumID"))
                                             .replace(SIZE_REP, "width=\"350\" height=\"350\"");
                    // TODO Formatting html output
                    html += albumImage + results.getString("AlbumName") + " " + results.getString("ReleaseYear");
                }
                DBUtil.closeSelectObjects();
            }
            else
            {
                html += "<h1><a href=/Search?artist=" + results.getString("ArtistID") 
                        + ">" + results.getString(html) + "</a></h1>";
                
                DBUtil.closeSelectObjects();
                
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
        catch(SQLException e)
        {
            System.out.println(e);
            html = "<p>An error occurred while processing the request</p>";
        }
        finally
        {
            DBUtil.closeSelectObjects();
        }
        
        return html;
    }
    
    /**
     * Gets the first 10 albums from the database to display on the homepage
     * 
     * @return - Formatted HTML to display the first 10 Albums for the database
     */
    public String getAlbumGallery()
    {
        String query = "SELECT AlbumID, AlbumName, ImageName FROM ALBUM LIMIT 10";
        
        ResultSet rs = DBUtil.executeSelect(query);
        
        String htmlOutput = "";
        try
        {
            while(rs.next())
            {
                htmlOutput += IMAGE.replace(HREF_REP, HREF_LINK)
                                   .replace(TYPE_REP, "album")
                                   .replace(HREF_VAL, rs.getString("AlbumID"))
                                   .replace(SRC_REP, rs.getString("ImageName"))
                                   .replace(ALT_REP, rs.getString("AlbumName"))
                                   .replace(SIZE_REP, "");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        finally
        {
            DBUtil.closeSelectObjects();
        }
        return htmlOutput;
    }
}
