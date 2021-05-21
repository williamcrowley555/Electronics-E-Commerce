/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.mapper.impl;

import com.tourdulich.dto.DichVuDTO;
import com.tourdulich.dto.ProductDTO;
import com.tourdulich.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class ProductMapper implements RowMapper<ProductDTO>{

    @Override
    public ProductDTO mapRow(ResultSet rs) {
        try {
            ProductDTO product = new ProductDTO();
            product.setId(rs.getLong("id"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setImage(rs.getBytes("image"));
            product.setPrice(rs.getLong("price"));
            product.setQuantity(rs.getInt("quantity"));
            product.setBrandId(rs.getLong("brand_id"));
            return product;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}
