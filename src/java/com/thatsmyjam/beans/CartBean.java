/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam.beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author bean51591
 */
public class CartBean implements Serializable {

    private List<Item> items;
    private String songAlbumName = "";
    private String albumSongName = "";
    private String message;
    boolean addFlag;
    private boolean addedAlbumSong;
    private boolean songSameAlbum;
    private boolean albumSameSong;
    private String purchasedDate = null;
    private DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    Date today;

    //creating a list of items in cart
    public CartBean() {
        items = new ArrayList();
    }

    //setting list
    public void setItems(List<Item> items) {
        this.items = items;
    }

    //getting list
    public List<Item> getItems() {
        return items;
    }

    //get number of items in cart
    public int getNumItems() {
        return this.items.size();
    }

    public String printCart() {
        String output = "";
        for (int i = 0; i < getNumItems(); ++i) {
            output += "<p>In Cart -- Item Name: "
                    + items.get(i).getItemTitle()
                    + " Album:" + items.get(i).getAlbumName()
                    + " Artist:" + items.get(i).getArtistName()
                    + " Price:" + items.get(i).getPrice() + "</p> <br></br>";

        }
        return output;
    }

    //get total
    public float getTotal() throws SQLException {
        float total = 0;
        for (int i = 0; i < getNumItems(); ++i) {
            total += items.get(i).getPrice();
            //all songs will be .60
        }

        return total;
    }

    //adds album/song to cartlist unless its already in the cart
    public void addItem(Item item) {
        message = null;
        addFlag = true;
        addedAlbumSong = false;
        songSameAlbum = false;
        albumSameSong = false;

        if (items.isEmpty()) {
            this.items.add(item);
        } else {
            for (int i = 0; i < getNumItems(); ++i) {
                if (item.getItemTitle().equals(items.get(i).getItemTitle())) {
                    addedAlbumSong = true;
                    setMessage();
                    addFlag = false;
                    break;
                }
                if (item.getAlbumName().equals(items.get(i).getAlbumName())) {
                    if (item.getSongID() == 0) {
                        setAlbumIncludedSong(items.get(i).getSongName());
                        albumSameSong = true;
                        setMessage();
                    } else {
                        setSongIncludedAlbum(items.get(i).getAlbumName());
                        songSameAlbum = true;
                        setMessage();
                    }
                    addFlag = false;
                    break;
                }
            }
            if (addFlag) {
                this.items.add(item);
            }
        }
    }

    //removes item from cart
    public void removeItem(String item) {

        items.removeIf(itm -> itm.getItemTitle().equals(item));

    }

    public void removeAll() {
        items.removeAll(items);
    }

    public void setSongIncludedAlbum(String songAlbumName) {
        this.songAlbumName = songAlbumName;
    }

    public String getSongIncludedAlbum() {
        return songAlbumName;
    }

    public void setAlbumIncludedSong(String albumSongName) {
        this.albumSongName = albumSongName;
    }

    public String getAlbumIncludedSong() {
        return albumSongName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage() {
        if (addedAlbumSong) {
            this.message = "You've already added this item to your cart!";
        } else if (songSameAlbum) {
            this.message = "The song you are trying to add is already included in <b>" + getSongIncludedAlbum() + "</b>";
        } else if (albumSameSong) {
            this.message = "The album you are trying to add contains the song <b>" + getAlbumIncludedSong() + "</b>."
                    + " Remove this song if you would like to purchase the album";
        }
    }
    
    public String getPurchasedDate(){
        return purchasedDate;
    }

    public void setPurchasedDate() {
        today = Calendar.getInstance().getTime();
        this.purchasedDate = df.format(today);        
    }
}
