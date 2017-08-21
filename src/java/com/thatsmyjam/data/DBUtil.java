package com.thatsmyjam.data;

import java.sql.*;

public class DBUtil {

    private static ConnectionPool pool;
    private static Connection connection;
    private static PreparedStatement ps;
    private static Statement s;
    private static ResultSet rs;
    private static String error = "";
    
    /**
     * Closes the Statement used to query the Database
     * 
     * @param s - Statement to close 
     */
    public static void closeStatement(Statement s) {
        try {
            if (s != null) {
                s.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Close the PreparedStatement used to query the Database
     * 
     * @param ps - PreparedStatement to close
     */
    public static void closePreparedStatement(Statement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    /**
     * Closes the ResultSet containing results from querying the Database
     * 
     * @param rs - ResultSet to close
     */
    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    /**
     * Executes a Select query passed in and returns the resultSet
     * @param query
     * @return 
     */
    public static ResultSet executeSelect(String query) throws SQLException
    {
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        s = null;
        rs = null;
        
        s = connection.createStatement();
        rs = s.executeQuery(query);

        return rs;
    }
    
    /**
     * Executes an Insert query passed in and returns the number of rows updated
     * 
     * @param query - Insert statement to execute
     * @param song - SongID to insert
     * @return - Number of rows modified
     * @throws SQLException 
     */
    public static int executeSongInsert(String query, int song) throws SQLException{
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        ps = connection.prepareStatement(query);
        ps.setInt(1, song);
           
        return ps.executeUpdate();
    }
    
    /**
     * Returns an error message from the select query if one occurred
     * 
     * @return - Message from the error
     */
    public static String getErrorMsg()
    {
        return error;
    }
    
    /**
     * Closes all of the objects after calling executeSelect
     */
    public static void closeSelectObjects()
    {
        closeStatement(s);
        closeResultSet(rs);
        pool.freeConnection(connection);
    }
    
    /**
     * Closes all of the objects after calling executeSongInsert
     */
    public static void closeInsertObjects()
    {
        closePreparedStatement(ps);
        closeResultSet(rs);
        pool.freeConnection(connection);
    }
}