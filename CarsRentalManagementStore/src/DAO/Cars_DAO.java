/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Cars_DTO;
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
public class Cars_DAO {
    public ArrayList<Cars_DTO> carsList() {
        ArrayList<Cars_DTO> carList = new ArrayList<>();
        String selectQuery = "SELECT * FROM `cars`";
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(selectQuery);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Cars_DTO car = new Cars_DTO(rs.getInt("id"), rs.getInt("brand"), 
                        rs.getString("model"), rs.getString("fuel"), 
                        rs.getString("color"), rs.getString("class"), 
                        rs.getInt("passengers"), rs.getString ("gearbox"), 
                        rs.getInt("price"), rs.getString("air_conditioning"), 
                        rs.getString("air_bag"), rs.getString ("sunroof"), 
                        rs.getString("heated_seats"), rs.getString("nav_system"), 
                        rs.getString("bluetooth"), rs.getString ("electric_windows"), 
                        rs.getString ("gps")
                );
                carList.add(car);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cars_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carList;
    }
        
    public void addCar(Cars_DTO car) throws SQLException {
        String insertQuery = "INSERT INTO `cars`(`id`, `brand`, `model`, `fuel`, `color`, `class`,"
                + " `passengers`, `gearbox`, `price`, `air_conditioning`, `air_bag`, `sunroof`, `heated_seats`, "
                + "`nav_system`, `bluetooth`, "
                + "`electric_windows`, `gps`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(insertQuery)) {
            
            ps.setInt(1, car.getId());
            ps.setInt(2, car.getBrand());
            ps.setString(3, car.getModel());
            ps.setString(4, car.getFuel());
            ps.setString(5, car.getColor());
            ps.setString(6, car.getClass_());
            ps.setInt(7, car.getPassengers());
            ps.setString(8, car.getGearbox());
            ps.setInt(9, car.getPrice());
            ps.setString(10, car.getAir_cond());
            ps.setString(11, car.getAirbag());
            ps.setString(12, car.getSunroof());
            ps.setString(13, car.getHeated_seats());
            ps.setString(14, car.getNav_sys());
            ps.setString(15, car.getBluetooth());
            ps.setString(16, car.getElec_window());
            ps.setString(17, car.getGps());
            
            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "The New Car Has Been Added", "Add Car", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Car Not Added", "Add Car", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Invalid Add " +ex.getMessage(), "Add Car", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void editCar(Cars_DTO car) throws SQLException {
        String updateQuery = "UPDATE `cars` SET `brand`= ?, `model`=?, `fuel`=?, `color`=?, `class`=?, "
                + "`passengers`=?, `gearbox`=?, `price`=?, `air_conditioning`=?, `air_bag`=?, "
                + "`sunroof`=?, `heated_seats`=?, `nav_system`=?, `bluetooth`=?, `electric_windows`=?, "
                + "`gps`=? WHERE `id` = ?";
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(updateQuery)) {
            
            ps.setInt(1, car.getBrand());
            ps.setString(2, car.getModel());
            ps.setString(3, car.getFuel());
            ps.setString(4, car.getColor());
            ps.setString(5, car.getClass_());
            ps.setInt(6, car.getPassengers());
            ps.setString(7, car.getGearbox());
            ps.setInt(8, car.getPrice());
            ps.setString(9, car.getAir_cond());
            ps.setString(10, car.getAirbag());
            ps.setString(11, car.getSunroof());
            ps.setString(12, car.getHeated_seats());
            ps.setString(13, car.getNav_sys());
            ps.setString(14, car.getBluetooth());
            ps.setString(15, car.getElec_window());
            ps.setString(16, car.getGps());
            ps.setInt(17, car.getId());
            
            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "The Car Has Been Updated", "Update Car", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Car Not Updated", "Update Car", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Invalid Edit " +ex.getMessage(), "Edit Car", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void deleteCar(int id) throws SQLException {
        String deleteQuery = "DELETE FROM `cars` WHERE `id`=?";
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(deleteQuery)) {
            
            ps.setInt(1, id);
            
            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "The Car Has Been Deleted", "Delete Car", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Car Not Deleted", "Delete Car", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cars_DAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; // throw exception so that calling methods can handle it
        }
    }
    
    public ResultSet getData(String query) {
        ResultSet rs = null;
        try (Connection conn = Cars_DB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Cars_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
 
    //create a function to search car by id
    public Cars_DTO getCarById(int id) throws SQLException {
        String query = "SELECT * FROM `cars` WHERE `id` = ?";
        Cars_DTO car = null;

        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    car = new Cars_DTO(rs.getInt("id"), rs.getInt("brand"), 
                        rs.getString("model"), rs.getString("fuel"), 
                        rs.getString("color"), rs.getString("class"), 
                        rs.getInt("passengers"), rs.getString("gearbox"), 
                        rs.getInt("price"), rs.getString("air_conditioning"), 
                        rs.getString("air_bag"), rs.getString("sunroof"), 
                        rs.getString("heated_seats"), rs.getString("nav_system"), 
                        rs.getString("bluetooth"), rs.getString("electric_windows"), 
                        rs.getString("gps"));
                } else {
                    JOptionPane.showMessageDialog(null, "No Car With This ID", "Invalid ID", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cars_DAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; // throw exception so that calling methods can handle it
        }

        return car;
    }
    
    public ArrayList<Cars_DTO> carsByBrandList(int car_brand_id) {
        ArrayList<Cars_DTO> carList = new ArrayList<>();
        String selectQuery = "SELECT * FROM `cars` WHERE brand = " + car_brand_id;
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(selectQuery);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Cars_DTO car = new Cars_DTO(rs.getInt("id"), rs.getInt("brand"), 
                        rs.getString("model"), rs.getString("fuel"), 
                        rs.getString("color"), rs.getString("class"), 
                        rs.getInt("passengers"), rs.getString ("gearbox"), 
                        rs.getInt("price"), rs.getString("air_conditioning"), 
                        rs.getString("air_bag"), rs.getString ("sunroof"), 
                        rs.getString("heated_seats"), rs.getString("nav_system"), 
                        rs.getString("bluetooth"), rs.getString ("electric_windows"), 
                        rs.getString ("gps")
                );
                carList.add(car);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cars_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carList;
    }
}
