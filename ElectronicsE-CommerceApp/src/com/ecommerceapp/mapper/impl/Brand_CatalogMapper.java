/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.mapper.impl;

import com.ecommerceapp.dto.Brand_CatalogDTO;
import com.ecommerceapp.dto.DsDiaDiemTourDTO;
import com.ecommerceapp.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class Brand_CatalogMapper implements RowMapper<Brand_CatalogDTO>{

    @Override
    public Brand_CatalogDTO mapRow(ResultSet rs) {
        try {
            Brand_CatalogDTO brand_catalog = new Brand_CatalogDTO();
            brand_catalog.setBrand_id(rs.getLong("brand_id"));
            brand_catalog.setCatalog_id(rs.getLong("catalog_id"));   
            return brand_catalog;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}
