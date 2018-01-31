/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam.beans;

import java.text.NumberFormat;

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

    // Needs empty constructor to be a bean
    public Item(){
        
    }
    
    public  Item(String itemTitle, String image, String artistName,int artistID,String albumName,int albumID, String songName, int songID, float price){
        this.itemTitle = itemTitle;
        this.image = image;
        this.artistName = artistName;
        this.artistID = artistID;
        this.albumName = albumName;
        this.albumID = albumID;
        this.songName = songName;
        this.songID = songID;
        this.price = price;
    }

    /**
     * @return the itemTitle
     */
    public String getItemTitle() {
        return itemTitle;
    }

    /**
     * @param itemTitle the itemTitle to set
     */
    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
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
     * @return the songName
     */
    public String getSongName() {
        return songName;
    }

    /**
     * @param songName the songName to set
     */
    public void setSongName(String songName) {
        this.songName = songName;
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
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @return the price formatted to $X.XX
     */
    public String getFormattedPrice() {
         NumberFormat formatter = NumberFormat.getCurrencyInstance();
         return formatter.format(price);
    }
    
    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }          
}
