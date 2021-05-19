/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.mapper.impl;

import com.tourdulich.dto.ThongKeDoanDTO;
import com.tourdulich.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kossp
 */
public class ThongKeDoanMapper implements RowMapper<ThongKeDoanDTO> {
    @Override
        public ThongKeDoanDTO mapRow(ResultSet rs) {
            try {
                ThongKeDoanDTO thongKeDoan = new ThongKeDoanDTO();
                thongKeDoan.setId(rs.getLong("id"));
                thongKeDoan.setTenDoan(rs.getString("ten_doan"));
                thongKeDoan.setSoKhach(rs.getInt("so_khach"));
                thongKeDoan.setGiaTien(rs.getLong("gia_tien"));
                thongKeDoan.setDoanhThu(rs.getLong("doanh_thu"));
                thongKeDoan.setTongChiPhi(rs.getLong("tong_chi_phi"));
                thongKeDoan.setLai(rs.getLong("lai"));

                return thongKeDoan;
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
            return null;
        }    
}
