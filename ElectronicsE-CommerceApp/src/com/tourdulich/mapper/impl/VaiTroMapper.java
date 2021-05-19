/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.mapper.impl;

import com.tourdulich.dto.VaiTroDTO;
import com.tourdulich.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class VaiTroMapper implements RowMapper<VaiTroDTO>{

    @Override
    public VaiTroDTO mapRow(ResultSet rs) {
        try {
            VaiTroDTO vaiTro = new VaiTroDTO();
            vaiTro.setId(rs.getLong("id"));
            vaiTro.setTenVaiTro(rs.getString("ten_vai_tro"));
            
            return vaiTro;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}
