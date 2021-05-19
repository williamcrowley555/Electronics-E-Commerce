/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll.impl;

import com.tourdulich.bll.IThongKeTourBLL;
import com.tourdulich.dal.impl.ThongKeTourDAL;
import com.tourdulich.dto.ThongKeTourDTO;
import java.util.List;
import com.tourdulich.dal.IThongKeTourDAL;

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
