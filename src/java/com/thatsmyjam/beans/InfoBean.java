/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam.beans;

import static com.thatsmyjam.constants.Constants.*;

import com.thatsmyjam.data.DBUtil;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author cpournaras11
 * 
 * Bean to obtain generic (not-user based) information from the database
 */
public class InfoBean implements Serializable {

    /**
     * Constructor
     */
    public InfoBean()
    {
    }
    
    /**
     * Gets an HTML formatted section for the album/artist id stored in the bean
     * 
     * @param isArtist - True if looking for an Artist, false for an Album
     * @param id - ID of the Artist/Album to get the page information for
     * @return - HTML to display to the user
     */
    public String getPage(boolean isArtist, String id)
    {
        try
        {
            Integer.parseInt(id);
        }
        catch(NumberFormatException e)
        {
            return "<p>The id value entered was not recognized and the request could not be completed";
        }
        
        String query;
        
        if(isArtist)
        {
            query = "SELECT Album.AlbumID, Album.AlbumName, Album.ReleaseYear, "
                    + "Album.ImageName as AlbumImage, Artist.ImageName as ArtistImage, Artist.ArtistName "
                    + "FROM Album INNER JOIN Artist ON Artist.ArtistID = Album.ArtistID "
                    + "WHERE Artist.ArtistID = " + id + ";";
        }
        else 
        {
            query = "SELECT Artist.ArtistID, Artist.ArtistName, Album.ReleaseYear, "
                    + "Album.ImageName, Album.AlbumName FROM Artist INNER JOIN Album ON "
                    + "Album.ArtistID = Artist.ArtistID WHERE AlbumID = " + id + ";";
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
                        String newImage = IMAGE.replace(SRC_REP, results.getString("ArtistImage"))
                                               .replace(ALT_REP, artist)
                                               .replace(HREF_REP, "")
                                               .replace(SIZE_REP, "width=\"500\" height=\"500\"");
                        first = false;
                        html += newImage + "<h1>" + artist + "</h1>";
                    }
                    String albumImage = IMAGE.replace(SRC_REP, results.getString("AlbumImage"))
                                             .replace(ALT_REP, results.getString("AlbumName"))
                                             .replace(TYPE_REP, "album")
                                             .replace(HREF_VAL, results.getString("AlbumID"))
                                             .replaceAll(PIXEL_REP, "350");
                    // TODO Formatting html output
                    html += albumImage + results.getString("AlbumName") + " " + results.getString("ReleaseYear");
                }
                DBUtil.closeSelectObjects();
            }
            else
            {
                // Move cursor to first result
                results.next();
                html += "<table><tr></td>";
                html += IMAGE.replace(SRC_REP, results.getString("ImageName"))
                             .replace(ALT_REP, results.getString("AlbumName"))
                             .replace(HREF_REP, "")
                             .replace(PIXEL_REP, "500");
                html += "</td><td>";
                int releaseYear = results.getInt("ReleaseYear");
                int artistID = results.getInt("ArtistID");
                String artistName = results.getString("ArtistName");
                DBUtil.closeSelectObjects();
                
                query = "SELECT SongName, AlbumName FROM Song INNER JOIN Album ON Album.AlbumID = Song.AlbumID WHERE Album.AlbumID = " + id;
                
                results = DBUtil.executeSelect(query);
                
                // TODO Formatting HTML output
                boolean first = true;
                while(results.next())
                {
                    if(first)
                    {
                        html += "<h2>Songs on " + results.getString("AlbumName")  + "<br/>by <a href=/ThatsMyJam/info.jsp?artist=" + artistID +
"                        + \">" + artistName + "</a></h2><ul style=\"list-style:none;\">";
                        first = false;
                    }
                    html += "<li>" + results.getString("SongName") + "</li>";
                }
                html += "</ul></td></tr><tr><td>" + releaseYear + "</td><td></td></tr></table>";
            }
        }
        catch(SQLException e)
        {
            html += "<p>An error occurred while processing the request<br/><br/>" + e.getMessage() + "<br/><br/>" + query + "</p>";
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
        String query = "SELECT AlbumID, AlbumName, ImageName FROM ALBUM ORDER BY RAND() LIMIT 8;";
        ResultSet rs = null;
        String htmlOutput = "";
        
        try
        {
            rs = DBUtil.executeSelect(query);
        }
        catch(SQLException e)
        {
            return "<p>An error occurred while processing your request<br/>" + e.getMessage() + "</p>";
        }
        
        try
        {
            while(rs.next())
            {
                htmlOutput += IMAGE.replace(TYPE_REP, "album")
                                   .replace(HREF_VAL, rs.getString("AlbumID")) 
                                   .replace(SRC_REP, rs.getString("ImageName"))
                                   .replace(ALT_REP, rs.getString("AlbumName"))
                                   .replace(SIZE_REP, "");
            }
        }
        catch(SQLException e)
        {
            htmlOutput = "<p>" + e + "</p>";
        }
        finally
        {
            DBUtil.closeSelectObjects();
        }
        return htmlOutput;
    }
}
