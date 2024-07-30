/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.Customers_DAO;
import DTO.Customers_DTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class Customers_BUS {
    Customers_DAO customersDAO = new Customers_DAO();
    
    public ArrayList<Customers_DTO> getAllCustomers() {
        return customersDAO.customersList();
    }
    
    public void addCustomer(int id, String fullname, String birthday, String phone, String email, String address) throws SQLException{
        Customers_DTO customer = new Customers_DTO();
        customer.setId(id);
        customer.setFullname(fullname);
        customer.setBirthday(birthday);
        customer.setPhone(phone);
        customer.setEmail(email);
        customer.setAddress(address);
        customersDAO.addCustomer(customer);
    }
    
    public void editCustomer(int id, String fullname, String birthday, String phone, String email, String address) throws SQLException{
        Customers_DTO customer = new Customers_DTO();
        customer.setId(id);
        customer.setFullname(fullname);
        customer.setBirthday(birthday);
        customer.setPhone(phone);
        customer.setEmail(email);
        customer.setAddress(address);
        customersDAO.editCustomer(customer);
    }
    
    public void removeCustomer(int id) throws SQLException {
        customersDAO.removeCustomer(id);
    }
    
    public Customers_DTO getCustomerById(int customerId) throws SQLException {
        return customersDAO.getCustomerById(customerId);
    }
    
    public ResultSet getData(String query) {
        return customersDAO.getData(query);
    }
}
