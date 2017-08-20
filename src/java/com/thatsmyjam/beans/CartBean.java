/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam.beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author bean51591
 */
public class CartBean implements Serializable {

    private List<Item> items;

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
        ListIterator<Item> itr = this.items.listIterator();
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

        for (int i = 0; i < getNumItems(); ++i) {
            if (item.getItemTitle().equals(items.get(i).getItemTitle())) {
                alreadyAddedMessage();
                System.out.println("Already added");
            } else {
                this.items.add(item);
            }

        }
        if (items.isEmpty()) {
            this.items.add(item);
        }
    }

    //removes item from cart
    public void removeItem(String item) {
     
        items.removeIf(itm -> itm.getItemTitle().equals(item));
 
    
    }

    public void removeAll() {
     items.removeAll(items);
    }

    public String alreadyAddedMessage() {
        return "<p> You've already added this item to your cart! </p>";
    }

}
