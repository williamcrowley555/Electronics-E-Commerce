/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class DBConnectionUtil {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/tour_du_lich";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "admin123";
    private static Connection connection = null;
    
    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return connection;
    }
}
