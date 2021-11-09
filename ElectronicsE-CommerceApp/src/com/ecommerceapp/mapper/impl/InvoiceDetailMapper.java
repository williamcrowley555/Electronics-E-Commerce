/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.mapper.impl;

import com.ecommerceapp.dto.InvoiceDetailDTO;
import com.ecommerceapp.mapper.RowMapper;
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
            invoiceDetail.setPrice(rs.getLong("price"));
            invoiceDetail.setSubTotal(rs.getLong("sub_total"));
            return invoiceDetail;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}
