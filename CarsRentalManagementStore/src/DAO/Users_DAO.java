package DAO;

import DTO.Users_DTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.*;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Users_DAO {
    //create s function to add a new user
    public void addUser(Users_DTO user) throws SQLException {
        String insertQuery = "INSERT INTO `users`(`id`, `fullname`, `username`, `password`, `user_type`, `image`, `phone`, `email`) VALUES (?,?,?,?,?,?,?,?)";
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(insertQuery)) {
            
            ps.setInt(1, user.getId());
            ps.setString(2, user.getFullname());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getUser_type());
            ps.setBytes(6, user.getPicture());
            ps.setString(7, user.getPhone());
            ps.setString(8, user.getEmail());
            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "The New User Has Been Added", "Add User", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "User Not Added", "Add User", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Users_DAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; 
        }
    }
    
    //create s function to update a user
    public void updateUser(Users_DTO user) throws SQLException {
        String updateQuery = "UPDATE `users` SET `fullname`=?,`username`=?, `password`=?, `user_type`=?, `image`=?, `phone`=?, `email`=? WHERE `id` = ?";
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(updateQuery)) {
            
            ps.setString(1, user.getFullname());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getUser_type());
            ps.setBytes(5, user.getPicture());
            ps.setString(6, user.getPhone());
            ps.setString(7, user.getEmail());
            ps.setInt(8, user.getId());
            
            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "The User Has Been Updated", "Update User", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "User Not Updated", "Update User", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Users_DAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; 
        }
    }
    
    //create s function to delete a user
    public void deleteUser(int id) throws SQLException {
        String deleteQuery = "DELETE FROM `users` WHERE `id` = ?";
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(deleteQuery)) {
            
            ps.setInt(1, id);
            
            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "The User Has Been Deleted", "Delete User", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "User Not Deleted", "Delete User", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Users_DAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; 
        }
    }
    
    // create a function to return a resultset
    public ResultSet getData(String query) {
        ResultSet rs = null;
        try (Connection conn = Cars_DB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Users_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    // create a function to get all locations and return an arraylist
    public ArrayList<Users_DTO> usersList() {
        ArrayList<Users_DTO> list = new ArrayList<>();
        String selectQuery = "SELECT * FROM `users`";
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(selectQuery);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Users_DTO user = new Users_DTO(
                    rs.getInt("id"),
                    rs.getString("fullname"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("user_type"),
                    rs.getBytes("image"),
                    rs.getString("phone"),
                    rs.getString("email")
                );
                list.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Users_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Users_DTO getUserById(int id) throws SQLException {
        String query = "SELECT * FROM `users` WHERE `id` = ?";
        Users_DTO user = null;
        
        try (Connection con = Cars_DB.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new Users_DTO();
                    user.setId(rs.getInt("id"));
                    user.setFullname(rs.getString("fullname"));
                    user.setUsername(rs.getString("username"));
                    user.setUser_type(rs.getString("user_type"));
                    user.setPicture(rs.getBytes("image"));
                    user.setPhone(rs.getString("phone"));
                    user.setEmail(rs.getString("email"));
                }
            }
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Enter a Valid User ID", "Error", 2);
            Logger.getLogger(Users_DAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; 
        }

        return user;
    }
    
    public Users_DTO getUser(String username, String password) {
        Users_DTO user = null;
        try {
            Connection conn = Cars_DB.getConnection();
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new Users_DTO();
                user.setUsername(rs.getString("username"));
                user.setUser_type(rs.getString("user_type"));
                user.setPicture(rs.getBytes("image")); 
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
