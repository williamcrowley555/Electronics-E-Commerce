/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.mapper.impl;

import com.ecommerceapp.dto.RevenueDTO;
import com.ecommerceapp.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class RevenueMapper implements RowMapper<RevenueDTO>{

    @Override
    public RevenueDTO mapRow(ResultSet rs) {
        try {
            RevenueDTO revenue = new RevenueDTO();
            revenue.setProductId(rs.getLong("product_id"));
            revenue.setProductName(rs.getString("product_name"));
            revenue.setProductPrice(rs.getLong("product_price"));
            revenue.setProductTotalQuantity(rs.getInt("total_quantity"));
            revenue.setProductSubTotal(rs.getLong("sub_total"));
            return revenue;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
