/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.mapper.impl;

import com.ecommerceapp.dto.SupplierDTO;
import com.ecommerceapp.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class SupplierMapper implements RowMapper<SupplierDTO> {

    @Override
    public SupplierDTO mapRow(ResultSet rs) {
        try {
            SupplierDTO supplier = new SupplierDTO();
            supplier.setId(rs.getLong("id"));
            supplier.setCompanyName(rs.getString("company_name"));
            supplier.setContactName(rs.getString("contact_name"));
            supplier.setContactJobTitle(rs.getString("contact_job_title"));
            supplier.setAddress(rs.getString("address"));
            supplier.setPhone(rs.getString("phone"));
            supplier.setEmail(rs.getString("email"));
            supplier.setCountry(rs.getString("country"));
            return supplier;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
