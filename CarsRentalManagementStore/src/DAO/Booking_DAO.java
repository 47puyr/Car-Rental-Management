/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Booking_DTO;
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
public class Booking_DAO {
    //add a new booking
    public void addNewBooking(Booking_DTO booking) throws SQLException
    {
        String insertQuery = "INSERT INTO `reservation`(`car_id`, `customer_id`, `pickup_city`, `pickup_address`, `pickup_date`, `pickup_time`, `dropoff_city`, `dropoff_address`, `dropoff_date`, `dropoff_time`, `total_price`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(insertQuery)) {
            
            ps.setInt(1, booking.getCar_id());
            ps.setInt(2, booking.getCustomer_id());
            ps.setString(3, booking.getPickup_city());
            ps.setString(4, booking.getPickup_address());
            ps.setString(5, booking.getPickup_date());
            ps.setString(6, booking.getPickup_time());
            ps.setString(7, booking.getDropoff_city());
            ps.setString(8, booking.getDropoff_address());
            ps.setString(9, booking.getDropoff_date());
            ps.setString(10, booking.getDropoff_time());
            ps.setInt(11, booking.getTotal_price());
            
            
            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "This Car Has Been Booked", "Booking", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "This Car Not Booked", "Booking", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Booking_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void removeBooking(int id) throws SQLException
    {
        String deleteQuery = "DELETE FROM `reservation` WHERE `id`=?";
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(deleteQuery)) {
            
            ps.setInt(1, id);
            
            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "The Reservation Has Been Deleted", "Delete Reservation", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Reservation Not Deleted", "Delete Reservation", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Booking_DAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; 
        }
    }
    
    public void editBooking(Booking_DTO booking) throws SQLException {
        String editQuery = "UPDATE `reservation` SET `car_id`= ?, `customer_id` = ?, `pickup_city`= ?, `pickup_address`= ? ,`pickup_date`= ?,`pickup_time`= ?,`dropoff_city`= ?,`dropoff_address`= ?,`dropoff_date`= ?,`dropoff_time`= ?,`total_price`= ? WHERE `id` = ?";
    
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(editQuery)) {

            ps.setInt(1, booking.getCar_id());
            ps.setInt(2, booking.getCustomer_id());
            ps.setString(3, booking.getPickup_city());
            ps.setString(4, booking.getPickup_address());
            ps.setString(5, booking.getPickup_date());
            ps.setString(6, booking.getPickup_time());
            ps.setString(7, booking.getDropoff_city());
            ps.setString(8, booking.getDropoff_address());
            ps.setString(9, booking.getDropoff_date());
            ps.setString(10, booking.getDropoff_time());
            ps.setInt(11, booking.getTotal_price());
            ps.setInt(12, booking.getId());

            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "The Reservation Has Been Updated", "Update Reservation", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Reservation Not Updated", "Update Reservation", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Booking_DAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; 
        }
    }
    
    public ArrayList<Booking_DTO> bookingList() {
        ArrayList<Booking_DTO> booklist = new ArrayList<>();
        String selectQuery = "SELECT * FROM `reservation`";
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(selectQuery);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Booking_DTO book = new Booking_DTO(rs.getInt("id"), rs.getInt("car_id"), rs.getInt("customer_id"), rs.getString("pickup_city"), rs.getString("pickup_address"), rs.getString("pickup_date"), rs.getString("pickup_time"), rs.getString("dropoff_city"), rs.getString("dropoff_address"), rs.getString("dropoff_date"), rs.getString("dropoff_time"), rs.getInt("total_price"));
                booklist.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Booking_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return booklist;
    }
    
    public List<Booking_DTO> getData(String query) {
        List<Booking_DTO> booklist = new ArrayList<>();
        try (Connection conn = Cars_DB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                booklist.add(new Booking_DTO(rs.getInt("id"), rs.getInt("car_id"), rs.getInt("customer_id"), rs.getString("pickup_city"), rs.getString("pickup_address"), rs.getString("pickup_date"), rs.getString("pickup_time"), rs.getString("dropoff_city"), rs.getString("dropoff_address"), rs.getString("dropoff_date"), rs.getString("dropoff_time"), rs.getInt("total_price")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Booking_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return booklist;
    }
}
