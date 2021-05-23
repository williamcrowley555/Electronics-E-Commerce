/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.mapper.impl;

import com.tourdulich.dto.DsDiaDiemTourDTO;
import com.tourdulich.dto.InvoiceDetailDTO;
import com.tourdulich.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class InvoiceDetailMapper implements RowMapper<InvoiceDetailDTO>{

    @Override
    public InvoiceDetailDTO mapRow(ResultSet rs) {
        try {
            InvoiceDetailDTO invoiceDetail = new InvoiceDetailDTO();
            invoiceDetail.setInvoice_id(rs.getLong("invoice_id"));
            invoiceDetail.setProduct_id(rs.getLong("product_id"));
            invoiceDetail.setQuantity(rs.getInt("quantity"));
            invoiceDetail.setSubTotal(rs.getLong("sub_total"));
            
            return invoiceDetail;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}
