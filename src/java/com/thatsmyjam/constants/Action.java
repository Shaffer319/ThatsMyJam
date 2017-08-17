/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam.constants;

/**
 *
 * @author mshaffer
 */
public class Action {
    // ACTION CONSTANTS
    // These constants are static and can be accessted from the JSPs. 
    // This will let us change the action string in one place and have the action update everywhere.
    public static final String ACTION_SIGNUP = "signup"; // Allows User to enter data about themselves to create an account
    public static final String ACTION_LOGIN = "login"; // Allows user to login to there account
    public static final String ACTION_LOGOUT = "logout"; // Allows user to logout and invaidate their session
    public static final String ACTION_INDEX = "index"; // Main entry point of app that that will let the user login. If logged in they should go to home
    public static final String ACTION_UPDATE_USER = "updateuser"; // Takes user to page where they can modify their personal info
    public static final String ACTION_ACCOUNT = "account"; // 
    public static final String ACTION_CREATE_ACCOUNT = "createaccount";

}
