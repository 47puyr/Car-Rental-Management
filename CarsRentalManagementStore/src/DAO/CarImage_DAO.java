/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.CarImage_DTO;
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
public class CarImage_DAO {
    public ArrayList<CarImage_DTO> carImagesList(int car_id) {
        ArrayList<CarImage_DTO> images = new ArrayList<>();
        
        String selectQuery = "SELECT `id`, `car_id`, `c_image` FROM `car_images` WHERE `car_id` = " + car_id;
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(selectQuery);
             ResultSet rs = ps.executeQuery()) {
             CarImage_DTO car_image;
            while (rs.next()) {
                car_image = new CarImage_DTO();
                car_image.setImg_id(rs.getInt(1));
                car_image.setCar_id(rs.getInt(2));
                car_image.setCar_img(rs.getBytes(3));
                images.add(car_image);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cars_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return images;
    }
    
     // Method to add a new car IMAGE
    public void addCarImage(int car_id, byte[] car_image){
        String insertQuery = "INSERT INTO `car_images`(`car_id`, `c_image`) VALUES (?,?)";
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(insertQuery)) {
            
            ps.setInt(1, car_id);
            ps.setBytes(2, car_image);
            
            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "The New Image Has Been Added", "Add Image", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Image Not Added", "Add Image", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Use a Smaller Size Image", "Car Image", 2);
        }
    }
    // create function to remove car
    public void removeCarImage(int img_id) throws SQLException
    {
        String removeQuery = "DELETE FROM `car_images` WHERE `id`=?";
        PreparedStatement ps;
        
        try {
            ps = Cars_DB.getConnection().prepareStatement (removeQuery);
            ps.setInt(1, img_id);
            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "This Car Image Has Been Deleted", "Delete Image", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Car Image Not Deleted", "Delete Image", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarImage_DAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
}
