/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll.impl;

import com.ecommerceapp.bll.IThongKeTourBLL;
import com.ecommerceapp.dal.impl.ThongKeTourDAL;
import com.ecommerceapp.dto.ThongKeTourDTO;
import java.util.List;
import com.ecommerceapp.dal.IThongKeTourDAL;

/**
 *
 * @author kossp
 */
public class ThongKeTourBLL implements IThongKeTourBLL{
private IThongKeTourDAL thongKeTourDAO;

    public ThongKeTourBLL() {
        this.thongKeTourDAO = new ThongKeTourDAL();
    }

    @Override
    public List<ThongKeTourDTO> findAll() {
        return thongKeTourDAO.findAll();
    }    
}
