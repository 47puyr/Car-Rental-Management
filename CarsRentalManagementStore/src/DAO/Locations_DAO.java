/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.*;
import java.util.logging.*;
import DTO.Locations_DTO;
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
public class Locations_DAO {
    //create s function to add a new location
    public void addLocation(Locations_DTO location) throws SQLException {
        String insertQuery = "INSERT INTO `locations`(`id`, `city`, `location`) VALUES (?, ?, ?)";
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(insertQuery)) {
            
            ps.setInt(1, location.getId());
            ps.setString(2, location.getCity());
            ps.setString(3, location.getAddress());
            
            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "The New Location Has Been Added", "Add Location", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Location Not Added", "Add Location", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Locations_DAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; // throw exception so that calling methods can handle it
        }
    }
    
    //create s function to update a location
    public void updateLocation(Locations_DTO location) throws SQLException {
        String updateQuery = "UPDATE `locations` SET `city`=?, `location`=? WHERE `id`=?";
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(updateQuery)) {
            
            ps.setString(1, location.getCity());
            ps.setString(2, location.getAddress());
            ps.setInt(3, location.getId());
            
            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "The Location Has Been Updated", "Update Location", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Location Not Updated", "Update Location", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Locations_DAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; // throw exception so that calling methods can handle it
        }
    }
    
    //create s function to delete a location
    public void deleteLocation(int id) throws SQLException {
        String deleteQuery = "DELETE FROM `locations` WHERE `id`=?";
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(deleteQuery)) {
            
            ps.setInt(1, id);
            
            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "The Location Has Been Deleted", "Delete Location", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Location Not Deleted", "Delete Location", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Locations_DAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Locations_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    // create a function to get all locations and return an arraylist
    public ArrayList<Locations_DTO> locationsList() {
        ArrayList<Locations_DTO> list = new ArrayList<>();
        String selectQuery = "SELECT * FROM `locations`";
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(selectQuery);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Locations_DTO location = new Locations_DTO(rs.getInt("id"), rs.getString("city"), rs.getString("location"));
                list.add(location);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Locations_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Locations_DTO getLocationById(int id) throws SQLException {
        String query = "SELECT * FROM `locations` WHERE `id` = ?";
        Locations_DTO location = null;
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    location = new Locations_DTO(rs.getInt("id"), rs.getString("city"), rs.getString("location"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Locations_DAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; // throw exception so that calling methods can handle it
        }

        return location;
    }
    
    public ArrayList<Locations_DTO> locationsListByCity(String city) {
        ArrayList<Locations_DTO> loclist = new ArrayList<>();
        String selectQuery = "SELECT * FROM `locations` WHERE `city` = '" + city + "'";
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(selectQuery);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Locations_DTO location = new Locations_DTO(rs.getInt("id"), rs.getString("city"), rs.getString("location"));
                loclist.add(location);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Locations_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loclist;
    }
}
