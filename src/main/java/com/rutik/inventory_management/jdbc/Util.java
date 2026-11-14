package com.rutik.inventory_management.jdbc;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Util {
    @Value("${db.url}")
    private static String URL;

    @Value("${db.url}")
    private static String NAME;

    @Value("${db.url}")
    private static String PASSWORD;

    //Register Driver
    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    //get connection
    public static Connection getConnection() throws SQLException {

//        return DriverManager.getConnection(URL,NAME,PASSWORD);
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory_db","root","root");
    }

    //close connection
    public static void closeResources(Connection conn, PreparedStatement ps) throws SQLException{
        conn.close();
        ps.close();
    }
}
