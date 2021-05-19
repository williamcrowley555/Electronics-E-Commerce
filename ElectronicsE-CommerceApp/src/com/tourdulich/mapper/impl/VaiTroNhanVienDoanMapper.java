/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.mapper.impl;

import com.tourdulich.dto.VaiTroNhanVienDoanDTO;
import com.tourdulich.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kossp
 */
public class VaiTroNhanVienDoanMapper implements RowMapper<VaiTroNhanVienDoanDTO>{
    @Override
        public VaiTroNhanVienDoanDTO mapRow(ResultSet rs) {
            try {
                VaiTroNhanVienDoanDTO vaiTroNhanVienDoan = new VaiTroNhanVienDoanDTO();
                vaiTroNhanVienDoan.setIdDsNvDoan(rs.getLong("id_ds_nv_doan"));
                vaiTroNhanVienDoan.setIdVaiTro(rs.getLong("id_vai_tro"));            

                return vaiTroNhanVienDoan;
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
            return null;
        }            
}
