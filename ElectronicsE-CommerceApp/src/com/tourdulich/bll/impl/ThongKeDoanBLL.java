/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll.impl;

import com.tourdulich.bll.IThongKeDoanBLL;
import com.tourdulich.dal.impl.ThongKeDoanDAL;
import com.tourdulich.dto.ThongKeDoanDTO;
import java.util.List;
import com.tourdulich.dal.IThongKeDoanDAL;

/**
 *
 * @author kossp
 */
public class ThongKeDoanBLL implements IThongKeDoanBLL {
    
    private IThongKeDoanDAL thongKeDoanDAO;
    
    public ThongKeDoanBLL() {
        this.thongKeDoanDAO = new ThongKeDoanDAL();
    }    

    @Override
    public List<ThongKeDoanDTO> findByIdTour(Long id) {
       return thongKeDoanDAO.findByIdTour(id);
    }
}
