/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.mapper.impl;

import com.ecommerceapp.dto.VaiTroNhanVienDoanDTO;
import com.ecommerceapp.mapper.RowMapper;
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
