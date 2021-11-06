/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.mapper.impl;

import com.ecommerceapp.dto.BrandDTO;
import com.ecommerceapp.dto.LoaiDuLichDTO;
import com.ecommerceapp.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class BrandMapper implements RowMapper<BrandDTO>{

    @Override
    public BrandDTO mapRow(ResultSet rs) {
        try {
            BrandDTO brand = new BrandDTO();
            brand.setId(rs.getLong("id"));
            brand.setName(rs.getString("name"));
            
            return brand;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}
