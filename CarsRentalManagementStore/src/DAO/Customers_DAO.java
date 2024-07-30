/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Customers_DTO;
import java.util.*;
import java.util.logging.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author ADMIN
 */
public class Customers_DAO {
    public void addCustomer(Customers_DTO customers) throws SQLException {
        String insertQuery = "INSERT INTO `customers`(`id`, `fullname`, `birth_date`, `phone`, `email`, `address`) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(insertQuery)) {
            
            ps.setInt(1, customers.getId());
            ps.setString(2, customers.getFullname());
            ps.setString(3, customers.getBirthday());
            ps.setString(4, customers.getPhone());
            ps.setString(5, customers.getEmail());
            ps.setString(6, customers.getAddress());
            
            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "The New Customer Has Been Added", "Add Customer", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Customer Not Added", "Add Customer", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customers_DAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; // throw exception so that calling methods can handle it
        }
    }
    
    //create s function to update a location
    public void editCustomer(Customers_DTO customers) throws SQLException {
        String editQuery = "UPDATE `customers` SET `fullname`= ? ,`birth_date`= ?, `phone`=?, `email`=?, `address`=? WHERE `id` = ?";
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(editQuery)) {
            
            ps.setString(1, customers.getFullname());
            ps.setString(2, customers.getBirthday());
            ps.setString(3, customers.getPhone());
            ps.setString(4, customers.getEmail());
            ps.setString(5, customers.getAddress());
            ps.setInt(6, customers.getId());
            
            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "The Customer Info Has Been Updated", "Update Customer", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Customer Info Not Updated", "Update Customer", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customers_DAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; // throw exception so that calling methods can handle it
        }
    }
    
    //create s function to delete a location
    public void removeCustomer(int id) throws SQLException {
        String removeQuery = "DELETE FROM `customers` WHERE `id` = ?";
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(removeQuery)) {
            
            ps.setInt(1, id);
            
            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "The Customer Has Been Deleted", "Delete Customer", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Customer Not Deleted", "Delete Customer", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customers_DAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; // throw exception so that calling methods can handle it
        }
    }
    
    // create a function to return a resultset
    public ResultSet getData(String query) {
        ResultSet rs = null;
        try (Connection conn = Cars_DB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Customers_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    // create a function to get all locations and return an arraylist
    public ArrayList<Customers_DTO> customersList() {
        ArrayList<Customers_DTO> list = new ArrayList<>();
        String selectQuery = "SELECT * FROM `customers`";
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(selectQuery);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Customers_DTO customer = new Customers_DTO(rs.getInt("id"), rs.getString("fullname"), rs.getString("birth_date"), rs.getString("phone"), rs.getString("email"), rs.getString("address"));
                list.add(customer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customers_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Customers_DTO getCustomerById(int customer_id) throws SQLException {
        String query = "SELECT * FROM `customers` WHERE `id` = ?";
        Customers_DTO customer = null;
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, customer_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    customer = new Customers_DTO(rs.getInt("id"), rs.getString("fullname"), rs.getString("birth_date"), rs.getString("phone"), rs.getString("email"), rs.getString("address"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customers_DAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; // throw exception so that calling methods can handle it
        }

        return customer;
    }
}
