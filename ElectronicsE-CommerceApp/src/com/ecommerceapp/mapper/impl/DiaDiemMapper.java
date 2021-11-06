/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.mapper.impl;

import com.ecommerceapp.dto.DiaDiemDTO;
import com.ecommerceapp.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kossp
 */
public class DiaDiemMapper implements RowMapper<DiaDiemDTO> {
    @Override
        public DiaDiemDTO mapRow(ResultSet rs) {
            try {
                DiaDiemDTO diaDiem = new DiaDiemDTO();
                diaDiem.setId(rs.getLong("id"));
                diaDiem.setTenDiaDiem(rs.getString("ten_dia_diem"));
                diaDiem.setDiaChi(rs.getString("dia_chi"));
                diaDiem.setGioiThieu(rs.getString("gioi_thieu"));
                diaDiem.setHinhAnh(rs.getBytes("hinh_anh"));
                diaDiem.setIdTinh(rs.getLong("id_tinh"));
                
                return diaDiem;
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
            return null;
        }    
}
