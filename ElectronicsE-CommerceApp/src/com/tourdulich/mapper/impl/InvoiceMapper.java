/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.mapper.impl;

import com.tourdulich.dto.CatalogDTO;
import com.tourdulich.dto.InvoiceDTO;
import com.tourdulich.dto.LoaiDuLichDTO;
import com.tourdulich.mapper.RowMapper;
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
            invoice.setNote(rs.getString("note"));
            invoice.setPhone(rs.getString("phone"));
            invoice.setStatus(rs.getInt("status"));
            invoice.setAddress(rs.getString("address"));
            invoice.setRecipientLastName(rs.getString("recipient_last_name"));
            invoice.setRecipientFirstName(rs.getString("recipient_first_name"));
            invoice.setOrderDate(rs.getDate("order_date").toLocalDate());
            invoice.setPaymentDate(rs.getDate("payment_date").toLocalDate());
            invoice.setCancellingDate(rs.getDate("cancelling_date").toLocalDate());
            invoice.setConfirmationDate(rs.getDate("confirmation_date").toLocalDate());
            invoice.setShipDate(rs.getDate("ship_date").toLocalDate());
            invoice.setTotal(rs.getLong("total"));
          
            
            return invoice;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}
