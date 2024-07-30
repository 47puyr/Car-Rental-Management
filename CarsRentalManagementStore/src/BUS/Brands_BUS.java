package BUS;

import DAO.Brands_DAO;
import DTO.Brands_DTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


public class Brands_BUS {
    Brands_DAO brandDAO = new Brands_DAO();

    public ArrayList<Brands_DTO> getAllBrands() {
        return brandDAO.brandsList();
    }

    public void addBrand(int id, String name, byte[] logo) throws SQLException {
        Brands_DTO brand = new Brands_DTO();
        brand.setId(id);
        brand.setName(name);
        brand.setLogo(logo);
        brandDAO.addBrand(brand);
    }

    public void updateBrand(int id, String name, byte[] logo) throws SQLException {
        Brands_DTO brand = new Brands_DTO();
        brand.setId(id);
        brand.setName(name);
        brand.setLogo(logo);
        brandDAO.updateBrand(brand);
    }

    public void deleteBrand(int id) throws SQLException {
        brandDAO.deleteBrand(id);
    }

    public Brands_DTO getBrandById(int brandId) throws SQLException {
        return brandDAO.getBrandById(brandId);
    }
    
    // Method to get data based on a custom query
     public List<Brands_DTO> getData(String query) {
        return brandDAO.getData(query);
    }
    
    // Method to get HashMap of brands
    public HashMap<Integer, String> getBrandsHashMap() {
        return brandDAO.brandsHashMap();
    }
}
