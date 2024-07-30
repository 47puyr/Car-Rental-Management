/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import DTO.Brands_DTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class Brands_DAO {

    // Method to add a brand
    public void addBrand(Brands_DTO brands) throws SQLException {
        String insertQuery = "INSERT INTO `brands` (`id`, `name`, `logo`) VALUES (?, ?, ?)";
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(insertQuery)) {
            
            ps.setInt(1, brands.getId());
            ps.setString(2, brands.getName());
            ps.setBytes(3, brands.getLogo());
            
            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "The New Brand Has Been Added", "Add Brand", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Brand Not Added", "Add Brand", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Invalid Add " +ex.getMessage(), "Add Brand", JOptionPane.ERROR_MESSAGE);
//            Logger.getLogger(Brands_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Method to update a brand    
    public void updateBrand(Brands_DTO brands) throws SQLException {
        String updateQuery = "UPDATE `brands` SET `name` = ?, `logo` = ? WHERE `id` = ?";
    
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(updateQuery)) {

            ps.setString(1, brands.getName());
            ps.setBytes(2, brands.getLogo());
            ps.setInt(3, brands.getId());

            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "The Brand Has Been Updated", "Update Brand", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Brand Not Updated", "Update Brand", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Use a Smaller Size Image", "Brand Logo", 2);
        }
    }
    
    // Method to delete a brand
    public void deleteBrand(int id) throws SQLException {
        String deleteQuery = "DELETE FROM `brands` WHERE `id` = ?";
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(deleteQuery)) {
            
            ps.setInt(1, id);
            
            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "The Brand Has Been Deleted", "Delete Brand", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Brand Not Deleted", "Delete Brand", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Brands_DAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; // throw exception so that calling methods can handle it
        }
    }

    // Method to get a brand by its ID
    public Brands_DTO getBrandById(int id) throws SQLException {
        String query = "SELECT * FROM `brands` WHERE `id` = ?";
        Brands_DTO brand = null;

        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    brand = new Brands_DTO(rs.getInt("id"), rs.getString("name"), rs.getBytes("logo"));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Edit Brand Error [Select The Brand You Want to Edit]", "Edit Brand", 2);
            //Logger.getLogger(Brands_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return brand;
    }    
    // Method to get all brands
    public ArrayList<Brands_DTO> brandsList() {
        ArrayList<Brands_DTO> list = new ArrayList<>();
        String selectQuery = "SELECT * FROM `brands`";
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(selectQuery);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Brands_DTO brand = new Brands_DTO(rs.getInt("id"), rs.getString("name"), rs.getBytes("logo"));
                list.add(brand);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Brands_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    // Method to execute a custom query and return a ResultSet
    public List<Brands_DTO> getData(String query) {
        List<Brands_DTO> brands = new ArrayList<>();
        try (Connection conn = Cars_DB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                brands.add(new Brands_DTO(rs.getInt("id"), rs.getString("name"), rs.getBytes("logo")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Brands_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return brands;
    }
    
    // create a function to poplate a hashmap with brands (id and name)
    public HashMap<Integer, String> brandsHashMap() {
        HashMap<Integer, String> brands_map = new HashMap<>();
        List<Brands_DTO> brands = getData("SELECT * FROM `brands`");
        for (Brands_DTO brand : brands) {
            brands_map.put(brand.getId(), brand.getName());
        }
        return brands_map;
    }
}
