/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam;

/**
 *
 * @author mshaffer
 */
public class User {
    
    private boolean loggedIn = false;
    private String email;
    
    public User(){
        
    }
    
    public void setLoggedIn(boolean loggedIn){
        this.loggedIn = loggedIn;
    }
    
    public boolean isLoggedIn(){
        return this.loggedIn;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
}
