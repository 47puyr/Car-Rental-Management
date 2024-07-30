/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DTO.*;
import java.sql.*;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class Cars_DB {
    private static String serverName = "localhost";
    private static String userName = "root";
    private static String dbName = "java_car_rental";
    private static int portNumber = 3306;
    private static String pass = "";
    
    // create a function to create and return the connection
    public static Connection getConnection()
    {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + serverName + "/" + dbName, userName, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Cars_DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return connection;
    }
}
