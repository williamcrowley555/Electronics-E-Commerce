/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.mapper.impl;

import com.ecommerceapp.dto.CatalogDTO;
import com.ecommerceapp.dto.InvoiceDTO;
import com.ecommerceapp.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class InvoiceMapper implements RowMapper<InvoiceDTO>{

    @Override
    public InvoiceDTO mapRow(ResultSet rs) {
        try {
            InvoiceDTO invoice = new InvoiceDTO();
            invoice.setId(rs.getLong("id"));
            invoice.setUserId(rs.getLong("user_id"));
            invoice.setEmployeeId(rs.getLong("employee_id") == 0 ? null : rs.getLong("employee_id"));
            invoice.setNote(rs.getString("note"));
            invoice.setPhone(rs.getString("phone"));
            invoice.setStatus(rs.getInt("status"));
            invoice.setAddress(rs.getString("address"));
            invoice.setRecipientLastName(rs.getString("recipient_last_name"));
            invoice.setRecipientFirstName(rs.getString("recipient_first_name"));
            if (rs.getDate("order_date") != null)
            invoice.setOrderDate(rs.getDate("order_date").toLocalDate());
             if (rs.getDate("payment_date") != null)
            invoice.setPaymentDate(rs.getDate("payment_date").toLocalDate());
            if (rs.getDate("cancelling_date") != null) 
            invoice.setCancellingDate(rs.getDate("cancelling_date").toLocalDate());
            if (rs.getDate("confirmation_date") != null) 
            invoice.setConfirmationDate(rs.getDate("confirmation_date").toLocalDate());
            if (rs.getDate("ship_date") != null) 
            invoice.setShipDate(rs.getDate("ship_date").toLocalDate());
            invoice.setTotal(rs.getLong("total"));
          
            return invoice;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}
