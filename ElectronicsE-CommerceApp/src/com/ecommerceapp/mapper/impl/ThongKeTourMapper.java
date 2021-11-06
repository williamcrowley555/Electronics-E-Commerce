/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.mapper.impl;

import com.ecommerceapp.dto.ThongKeTourDTO;
import com.ecommerceapp.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kossp
 */
public class ThongKeTourMapper implements RowMapper<ThongKeTourDTO>{
    @Override
    public ThongKeTourDTO mapRow(ResultSet rs) {
        try {
            ThongKeTourDTO thongKeTour = new ThongKeTourDTO();
            thongKeTour.setTourId(rs.getLong("tour_id"));
            thongKeTour.setTenTour(rs.getString("ten_tour"));
            thongKeTour.setTongSoDoan(rs.getLong("tong_so_doan"));
            thongKeTour.setTongDoanhThu(rs.getLong("tong_doanh_thu"));
            thongKeTour.setTongChiPhi(rs.getLong("tong_chi_phi"));
            thongKeTour.setLai(rs.getLong("lai"));
            
            return thongKeTour;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
       
}
