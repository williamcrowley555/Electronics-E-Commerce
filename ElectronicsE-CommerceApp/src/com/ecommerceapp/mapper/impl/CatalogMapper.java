/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.mapper.impl;

import com.ecommerceapp.dto.CatalogDTO;
import com.ecommerceapp.dto.LoaiDuLichDTO;
import com.ecommerceapp.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class CatalogMapper implements RowMapper<CatalogDTO>{

    @Override
    public CatalogDTO mapRow(ResultSet rs) {
        try {
            CatalogDTO catalog = new CatalogDTO();
            catalog.setId(rs.getLong("id"));
            catalog.setName(rs.getString("name"));
            
            return catalog;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}
