/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.mapper.impl;

import com.ecommerceapp.dto.ProductPriceHistoryDTO;
import com.ecommerceapp.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class ProductPriceHistoryMapper implements RowMapper<ProductPriceHistoryDTO>{

    @Override
    public ProductPriceHistoryDTO mapRow(ResultSet rs) {
        try {
            ProductPriceHistoryDTO productPriceHistory = new ProductPriceHistoryDTO();
            productPriceHistory.setId(rs.getLong("id"));
            productPriceHistory.setEffective_date(rs.getDate("effective_date"));
            productPriceHistory.setPrice(rs.getLong("price"));
            productPriceHistory.setProduct_id(rs.getLong("product_id"));
            return productPriceHistory;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}
