package com.thatsmyjam.data;

import java.sql.*;

public class DBUtil {

    public static void closeStatement(Statement s) {
        try {
            if (s != null) {
                s.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void closePreparedStatement(Statement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
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
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement s = null;
        ResultSet rs = null;
        
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
        finally 
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(s);
            pool.freeConnection(connection);
        }
    }
}