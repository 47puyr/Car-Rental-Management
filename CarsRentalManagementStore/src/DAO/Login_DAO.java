package DAO;

import DTO.Users_DTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login_DAO {

    public Users_DTO checkLogin(String username, String password) {
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT * FROM `users` WHERE `username`= ? AND `password` = ?";

        try {
            ps = Cars_DB.getConnection().prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                Users_DTO user = new Users_DTO();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
