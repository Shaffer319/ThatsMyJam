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
    private String firstName;
    private String lastName;
    private String email;
    
    public User(){
        
    }
    
    public void setLoggedIn(boolean loggedIn){
        this.loggedIn = loggedIn;
    }
    
    public boolean isLoggedIn(){
        return this.loggedIn;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getEmail(){
        return email;
    }
    
}
