/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam.beans;

import static com.thatsmyjam.constants.Constants.*;

import com.thatsmyjam.data.DBUtil;
import com.thatsmyjam.data.User;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cpournaras11
 *
 * Bean to obtain generic (not-user based) information from the database
 */
public class InfoBean implements Serializable {

    private int albumID;
    private String albumName,artistName;
    private int artistID;
    private int releaseYear;
    private String imageName;
    private float albumPrice;
    private ArrayList<String> songList;
    private static User currentUser;
    private String searchResults;
    private String playlistResults;
    
    /**
     * Constructor
     */
    public InfoBean() {
    }

    /**
     * Gets the dynamic title for the information page
     *
     * @param isArtist - Boolean for Artist info, false for Album info
     * @param id - ID for the Artist/Album
     *
     * @return - Dynamic title for the page
     */
    public String getTitle(boolean isArtist, String id) {
        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return "Error occurred";
        }

        String query = "";
        if (isArtist) {
            query = "SELECT ArtistName FROM Artist WHERE ArtistID = " + id;
        } else {
            query = "SELECT AlbumName, ArtistName FROM Album "
                    + "INNER JOIN Artist ON Artist.ArtistID = Album.ArtistID "
                    + "WHERE AlbumID = " + id;
        }

        String title;
        try {
            ResultSet results = DBUtil.executeSelect(query);
            results.next();
            if (isArtist) {
                title = "Artist: " + results.getString("ArtistName");
            } else {

                title = results.getString("AlbumName") + " by " + results.getString("ArtistName");
            }
        } catch (SQLException e) {
            title = "Error Occurred";
        } finally {
            DBUtil.closeSelectObjects();
        }
        return title;
    }

    /**
     * Gets an HTML formatted section for the album/artist id stored in the bean
     *
     * @param response - HttpServletResponse to link to another url from page results
     * @param isArtist - True if looking for an Artist, false for an Album
     * @param id - ID of the Artist/Album to get the page information for
     * @return - HTML to display to the user
     */
    public String getPage(HttpServletResponse response, boolean isArtist, String id) {
        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return "<p>The id value entered was not recognized and the request could not be completed";
        }

        String query;

        if (isArtist) {
            query = "SELECT Album.AlbumID, Album.AlbumName, Album.ReleaseYear, "
                    + "Album.ImageName as AlbumImage, Artist.ImageName as ArtistImage, Artist.ArtistName "
                    + "FROM Album INNER JOIN Artist ON Artist.ArtistID = Album.ArtistID "
                    + "WHERE Artist.ArtistID = " + id + " "
                    + "ORDER BY ReleaseYear DESC;";
        }
        else 
        {
            query = "SELECT Artist.ArtistID, Artist.ArtistName, Album.ReleaseYear, "
                    + "Album.ImageName, Album.AlbumName, Album.AlbumPrice FROM Artist INNER JOIN Album ON "
                    + "Album.ArtistID = Artist.ArtistID WHERE AlbumID = " + id + ";";

        }

        String html = "";

        try {
            ResultSet results = DBUtil.executeSelect(query);

            if (isArtist) {
                boolean first = true;
                
                int counter = 0;
                String[] albumNames = new String[3];
                String rowDiv = "<div class=\"col-lg-9 col-md-12 col-xs-18\">";
                String cellDiv = "<div style=\"text-align:center\" class=\"col-lg-3 col-md-4 col-xs-6\">REPLACE</div>";
                while(results.next())
                {
                    if(first)
                    {
                        setArtistName(results.getString("ArtistName"));
                        String newImage = IMAGE.replace(SRC_REP, results.getString("ArtistImage"))
                                               .replace(ALT_REP, getArtistName())
                                               .replace(HREF_REP, "")
                                               .replace(SIZE_REP, "width=\"500\" height=\"500\"");
                        first = false;
                        html += newImage + "<h1>" + getArtistName() + "</h1>";
                    }
                    
                    if(counter == 0)
                    {
                        html += rowDiv;
                    }
                    
                    String albumImage = IMAGE.replace(SRC_REP, results.getString("AlbumImage"))
                            .replace(ALT_REP, results.getString("AlbumName"))
                            .replace(TYPE_REP, "album")
                            .replace(HREF_VAL, results.getString("AlbumID"))
                            .replaceAll(PIXEL_REP, "350");
                    // TODO Formatting html output
                    html += albumImage;
                    albumNames[counter++] =  results.getString("AlbumName") + " " + results.getString("ReleaseYear");
                    
                    if(counter == 3)
                    {
                        html += "</div>";
                        html += rowDiv;
                        for(int i = 0; i < 3; i++)
                        {
                            html += cellDiv.replace("REPLACE", albumNames[i]);
                            albumNames[i] = null;
                        }
                        html += "</div>";
                        counter = 0;
                    }
                }
                
                if(counter != 0)
                {
                    html += "</div>" + rowDiv;
                    for(int i = 0; i < counter; i++)
                    {
                        html += cellDiv.replace("REPLACE", albumNames[i]);
                    }
                    html += "</div>";
                }
                
                DBUtil.closeSelectObjects();
            } else {
                // Move cursor to first result
                results.next();
                html += "<table class=\"col-xs-12 col-md-8\"><tr></td>";
                html += IMAGE.replace(SRC_REP, results.getString("ImageName"))
                        .replace(URL_ENCODED, response.encodeURL(URL_LINK))
                        .replace(ALT_REP, results.getString("AlbumName"))
                        .replace(HREF_REP, "")
                        .replace(PIXEL_REP, "500");
                html += "</td><td>";

                setArtistName(results.getString("ArtistName"));
                setArtistID(results.getInt("ArtistID"));
                setAlbumID(Integer.parseInt(id));
                setAlbumName(results.getString("AlbumName"));
                setReleaseYear(results.getInt("ReleaseYear"));
                setImageName(results.getString("ImageName"));
                setAlbumPrice(results.getFloat("AlbumPrice"));
				
                DBUtil.closeSelectObjects();
              
                //TODO UNCOMMENT WHEN LOGIN IS WORKING SO A USER CAN BE TRACKED
                query = "SELECT SongID FROM OwnedSongs WHERE UserID = 1";// + getCurrentUser().getUserID();
                
                results = DBUtil.executeSelect(query);
                ArrayList<Integer> ownedSongs = new ArrayList<Integer>();
                
                while(results.next())
                {
                    ownedSongs.add(results.getInt("SongID"));
                }
                
                query = "SELECT SongID, SongName, AlbumName FROM Song INNER JOIN Album ON Album.AlbumID = Song.AlbumID WHERE Album.AlbumID = " + id;
                
                results = DBUtil.executeSelect(query);

                // TODO Formatting HTML output
                boolean first = true;
                String album = "";
                while(results.next())
                {
                    if(first)
                    {
                        album = results.getString("AlbumName");
                        html += "<h2>Songs on " + album + "<br/>by <a href=/ThatsMyJam/info.jsp?artist=" + getArtistID()
                                + ">" + getArtistName() + "</a> released in " + getReleaseYear() + "</h2><ul style=\"list-style:none;\">";
                        first = false;
                        html += "<strong><em>$" + getAlbumPrice() + "</em></strong><br><br/>";
                    }
                    
                    int songID = results.getInt("SongID");
                    String songName = results.getString("SongName");
                    String targetLink = "<a target=\"_blank\" href=http://www.google.com/search?q=youtube+" 
                                      + songName.replaceAll(" ", "+") 
                                      + "+on+album+" + album.replaceAll(" ", "+") + "&m=0>";
   
                    if(ownedSongs.contains(songID))
                    {
                        html += "<li><div class=\"col-xs-12 col-md-8\">"
                                + targetLink
                                + "<div style=\"float:left\">" + results.getString("SongName") + "</div></a>"
                             + "<div style=\"flot:right\">Already Owned</li>";
                    }
                    else
                    {
                        html += "<li><div class=\"col-xs-12 col-md-8\">"
                                + targetLink
                                + "<div style=\"float:left\">" + songName + "</div></a>"
                                + "<div style=\"float:right\">"
                                + "<button name=\"song\" value=\"" +songName+"_"+songID + "\" title=\"Add to Cart\" style=\"height:20px\" type=\"submit\">"
                                + "<span class=\"glyphicon glyphicon-shopping-cart\"/>"
                                + "</button></div></div></li>";
                    }
                }
                html += "</ul></td></tr></table>";
            }
        } catch (SQLException e) {
            html += "<p>An error occurred while processing the request<br/><br/>" + e.getMessage() + "<br/><br/>" + query + "</p>";
        } finally {
            DBUtil.closeSelectObjects();
        }

        return html;
    }

    /**
     * Gets the first 10 albums from the database to display on the homepage
     *
     * @return - Formatted HTML to display the first 10 Albums for the database
     */
    public String getAlbumGallery(HttpServletResponse response) {
        String query = "SELECT AlbumID, AlbumName, ImageName FROM ALBUM ORDER BY RAND() LIMIT 8;";
        ResultSet rs = null;
        String htmlOutput = "";

        try {
            rs = DBUtil.executeSelect(query);
        } catch (SQLException e) {
            return "<p>An error occurred while processing your request<br/>" + e.getMessage() + "</p>";
        }

        try {
            while (rs.next()) {
                htmlOutput += IMAGE.replace(TYPE_REP, "album")
                        .replace(URL_ENCODED, response.encodeURL(URL_LINK))
                        .replace(HREF_VAL, rs.getString("AlbumID"))
                        .replace(SRC_REP, rs.getString("ImageName"))
                        .replace(ALT_REP, rs.getString("AlbumName"))
                        .replace(SIZE_REP, "");
            }
        } catch (SQLException e) {
            htmlOutput = "<p>" + e + "</p>";
        } finally {
            DBUtil.closeSelectObjects();
        }
        return htmlOutput;
    }
    
    /**
     * Checks to see if the user already owns an album
     * @param album - Album to check
    s * 
     * @return - True if the user owns the album, false otherwise 
     */
    public boolean albumOwned(String album)
    {
        int albumID = -1;
        try
        {
            albumID = Integer.parseInt(album);
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        
        // TODO Fix when login is working
        String query = "SELECT SongID FROM OwnedSongs WHERE UserID = 1"; // + InfoBean.getCurrentUser().getUserID();
        
        try
        {
            ResultSet results = DBUtil.executeSelect(query);
            ArrayList<Integer> ownedSongs = new ArrayList<Integer>();
            while(results.next())
            {
                ownedSongs.add(results.getInt("SongID"));
            }
            DBUtil.closeSelectObjects();
            
            query = "SELECT SongID FROM Song WHERE AlbumID = " + albumID;
            
            results = DBUtil.executeSelect(query);
            boolean albumOwned = true;
            while(results.next())
            {
                if(!ownedSongs.contains(results.getInt("SongID")))
                {
                    albumOwned = false;
                    break;
                }
            }
            DBUtil.closeSelectObjects();
            return albumOwned;
        }
        catch(SQLException e)
        {
            return false;
        }
        finally
        {
            DBUtil.closeSelectObjects();
        }
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
     * @return the currentUser
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * @param currentUser the currentUser to set
     */
    public static void setCurrentUser(User currentUser) {
        InfoBean.currentUser = currentUser;
    }

    /**
     * @return the searchResults
     */
    public String getSearchResults() {
        return searchResults;
    }

    /**
     * @param searchResults the searchResults to set
     */
    public void setSearchResults(String searchResults) {
        this.searchResults = searchResults;
    }

    /**
     * @return the playlistResults
     */
    public String getPlaylistResults() {
        return playlistResults;
    }

    /**
     * @param playlistResults the playlistResults to set
     */
    public void setPlaylistResults(String playlistResults) {
        this.playlistResults = playlistResults;
    }
    
}
