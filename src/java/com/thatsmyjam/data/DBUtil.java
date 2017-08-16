package com.thatsmyjam.data;

import java.sql.*;

public class DBUtil {

    private static ConnectionPool pool;
    private static Connection connection;
    private static Statement s;
    private static ResultSet rs;
    
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
    public static ResultSet executeSelect(String query)
    {
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        s = null;
        rs = null;
        
        try
        {
            s = connection.createStatement();
            rs = s.executeQuery(query);
            
            return rs;
        }
        catch(SQLException e)
        {
            System.out.println(e);
            return null;
        }
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
}