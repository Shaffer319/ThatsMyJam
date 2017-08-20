/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam.beans;

/**
 *
 * @author bean51591
 */
public class Item {

    private String itemTitle;
    private String image;
    private String artistName;
    private int artistID;
    private String albumName;
    private int albumID;
    private String songName;
    private int songID;
    private float price;

  
    
    public  Item(String itemTitle, String image, String artistName,int artistID,String albumName,int albumID, String songName, int songID, float price){
        setItemTitle(itemTitle);
        setImage(image);
        setArtistName(artistName);
        setArtistID(artistID);
        setAlbumName(albumName);
        setAlbumID(albumID);
        setSongName(songName);
        setSongID(songID);
        setPrice(price);
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public void setPrice(float price) {
        this.price = price;
    }
      
    
    public String getItemTitle(){
        return itemTitle;
    }
     public String getImage(){
        return image;
    }
      public String getArtistName(){
        return artistName;
    }
 
    public int getArtistID() {
        return artistID;
    }

    public String getAlbumName() {
        return albumName;
    }

    public int getAlbumID() {
        return albumID;
    }

    public String getSongName() {
        return songName;
    }

    public int getSongID() {
        return songID;
    }

    public float getPrice() {
        return price;
    }
           
}
