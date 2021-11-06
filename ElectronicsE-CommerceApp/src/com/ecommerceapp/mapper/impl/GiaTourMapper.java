/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.mapper.impl;

import com.ecommerceapp.dto.GiaTourDTO;
import com.ecommerceapp.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kossp
 */
public class GiaTourMapper implements RowMapper<GiaTourDTO> {
    @Override
        public GiaTourDTO mapRow(ResultSet rs) {
            try {
                GiaTourDTO giaTour = new GiaTourDTO();
                giaTour.setId(rs.getLong("id"));
                giaTour.setIdTour(rs.getLong("id_tour"));            
                giaTour.setNgayBatDau(rs.getDate("ngay_bat_dau").toLocalDate());
                giaTour.setNgayKetThuc(rs.getDate("ngay_ket_thuc").toLocalDate());
                giaTour.setGiaTien(rs.getLong("gia_tien"));
                
                return giaTour;
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
            return null;
        }        
}
