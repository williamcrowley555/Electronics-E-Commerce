/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.mapper.impl;

import com.tourdulich.dto.DoanDTO;
import com.tourdulich.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kossp
 */
public class DoanMapper implements RowMapper<DoanDTO> {
    @Override
        public DoanDTO mapRow(ResultSet rs) {
            try {
                DoanDTO doan = new DoanDTO();
                doan.setId(rs.getLong("id"));
                doan.setTenDoan(rs.getString("ten_doan"));            
                doan.setNgayKhoiHanh(rs.getDate("ngay_khoi_hanh").toLocalDate());
                doan.setNgayKetThuc(rs.getDate("ngay_ket_thuc").toLocalDate());
                doan.setIdTour(rs.getLong("id_tour"));
                doan.setSoLuong(rs.getInt("so_luong"));
                doan.setGiaTien(rs.getLong("gia_tien"));
                
                return doan;
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
            return null;
        }    
}
