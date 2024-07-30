/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.CarImage_DAO;
import DTO.CarImage_DTO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class CarImage_BUS {
    CarImage_DAO carimageDAO = new CarImage_DAO();
    
    public void addCarImage(int car_id, byte[] car_image) {
        carimageDAO.addCarImage(car_id, car_image);
    }
    
    public ArrayList<CarImage_DTO> carImagesList(int car_id) {
        return carimageDAO.carImagesList(car_id);
    }
    
    public void removeCarImage(int img_id) throws SQLException {
        carimageDAO.removeCarImage(img_id);
    }
}
