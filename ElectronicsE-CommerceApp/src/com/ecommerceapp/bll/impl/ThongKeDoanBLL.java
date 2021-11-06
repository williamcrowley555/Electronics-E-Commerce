/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll.impl;

import com.ecommerceapp.bll.IThongKeDoanBLL;
import com.ecommerceapp.dal.impl.ThongKeDoanDAL;
import com.ecommerceapp.dto.ThongKeDoanDTO;
import java.util.List;
import com.ecommerceapp.dal.IThongKeDoanDAL;

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
