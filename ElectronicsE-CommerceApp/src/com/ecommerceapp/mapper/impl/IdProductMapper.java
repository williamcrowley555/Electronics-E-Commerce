/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.mapper.impl;

import com.ecommerceapp.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kossp
 */
public class IdProductMapper implements RowMapper<Long> {
    @Override
        public Long mapRow(ResultSet rs) {
            try {
                Long id = null;
                id = (rs.getLong("product_id"));
                
                return id;
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
            return null;
        }    
}
