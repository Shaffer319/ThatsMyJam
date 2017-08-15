/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thatsmyjam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author cpournaras11
 */
public class DatabaseConnector {
    
    private static DatabaseConnector instance;
    private Connection conn;
    
    /**
     * Constructor - initialize database connection
     */
    public DatabaseConnector()
    {
        if(instance == null)
        {
            // TODO
            String url = "jdbc:mysql://path_to_db";
            
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(url, "root", "sesame");
            }
            catch(Exception e)
            {
                // Error Message Here?
                return;
            }
        }
        
        instance = this;
    }
    
    /**
     * Static function to get the singleton database connection
     * 
     * @return DatabaseConnector instance
     */
    public static DatabaseConnector getInstance()
    {
        if(instance == null)
        {
            new DatabaseConnector();
        }
        
        return instance;
    }
    
    /**
     * Function to query the database using the singleton instance
     * @param queryToExecute - Self-explanatory
     * 
     * @return - ResultSet of the query to be parsed accordingly/
     * Null if an error occurred
     */
    public ResultSet executeQuery(String queryToExecute) throws SQLException
    {
        Statement statement = conn.createStatement();
        return statement.executeQuery(queryToExecute);
    }
}
