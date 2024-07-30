/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.Locations_DAO;
import DTO.Locations_DTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class Locations_BUS {
    Locations_DAO locationDAO = new Locations_DAO();

    public ArrayList<Locations_DTO> getAllLocations() {
        return locationDAO.locationsList();
    }

    public void addLocation(int id, String city, String address) throws SQLException {
        Locations_DTO location = new Locations_DTO();
        location.setId(id);
        location.setCity(city);
        location.setAddress(address);
        locationDAO.addLocation(location);
    }

    public void updateLocation(int id, String city, String address) throws SQLException {
        Locations_DTO location = new Locations_DTO();
        location.setId(id);
        location.setCity(city);
        location.setAddress(address);
        locationDAO.updateLocation(location);
    }

    public void deleteLocation(int id) throws SQLException {
        locationDAO.deleteLocation(id);
    }
    
    public Locations_DTO getLocationById(int locationId) throws SQLException {
        return locationDAO.getLocationById(locationId);
    }
    
    public ResultSet getData(String query) {
        return locationDAO.getData(query);
    }
    
    public ArrayList<Locations_DTO> locationsListByCity(String city) {
        return locationDAO.locationsListByCity(city);
    }
}
