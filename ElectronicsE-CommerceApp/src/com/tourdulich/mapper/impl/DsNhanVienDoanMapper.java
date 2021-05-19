/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.mapper.impl;

import com.tourdulich.dto.DsNhanVienDoanDTO;
import com.tourdulich.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class DsNhanVienDoanMapper implements RowMapper<DsNhanVienDoanDTO>{

    @Override
    public DsNhanVienDoanDTO mapRow(ResultSet rs) {
        try {
            DsNhanVienDoanDTO dsNhanVienDoan = new DsNhanVienDoanDTO();
            dsNhanVienDoan.setId(rs.getLong("id"));
            dsNhanVienDoan.setIdDoan(rs.getLong("id_Doan"));
            dsNhanVienDoan.setIdNhanVien(rs.getLong("id_Nhan_Vien"));
            
            return dsNhanVienDoan;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}
