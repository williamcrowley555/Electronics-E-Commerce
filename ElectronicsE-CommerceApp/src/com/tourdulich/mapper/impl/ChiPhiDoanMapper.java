/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.mapper.impl;

import com.tourdulich.dto.ChiPhiDoanDTO;
import com.tourdulich.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author kossp
 */
public class ChiPhiDoanMapper implements RowMapper<ChiPhiDoanDTO>{

    @Override
    public ChiPhiDoanDTO mapRow(ResultSet rs) {
        try {
            ChiPhiDoanDTO chiPhiDoan = new ChiPhiDoanDTO();
            chiPhiDoan.setId(rs.getLong("id"));
            chiPhiDoan.setIdDoan(rs.getLong("id_doan"));
            chiPhiDoan.setIdDichVu(rs.getLong("id_dich_vu"));
            chiPhiDoan.setChiPhi(rs.getInt("chi_phi"));
            chiPhiDoan.setHoaDon(rs.getString("hoa_don"));
            chiPhiDoan.setNgayHoaDon(rs.getDate("ngay_hoa_don").toLocalDate());
            
            return chiPhiDoan;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}
