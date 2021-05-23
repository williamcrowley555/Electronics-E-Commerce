/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal.impl;

import com.tourdulich.dto.DsDiaDiemTourDTO;
import com.tourdulich.mapper.impl.DsDiaDiemTourMapper;
import com.tourdulich.mapper.impl.IdDiaDiemTourMapper;
import java.util.List;
import com.tourdulich.dal.IDsDiaDiemTourDAL;
import com.tourdulich.dal.IInvoiceDetailDAL;
import com.tourdulich.dto.InvoiceDetailDTO;
import com.tourdulich.mapper.impl.IdProductMapper;
import com.tourdulich.mapper.impl.InvoiceDetailMapper;

/**
 *
 * @author HP
 */
public class InvoiceDetailDAL extends AbstractDAL<InvoiceDetailDTO> implements IInvoiceDetailDAL {

    @Override
    public List<InvoiceDetailDTO> findAll() {
        String sql = "SELECT * FROM invoice_details ORDER BY stt ASC";
        return query(sql, new InvoiceDetailMapper());
    }


    @Override
    public Long save(InvoiceDetailDTO invoiceDetail) {
        String sql = "INSERT INTO invoice_details(invoice_id, product_id, quantity, sub_total) VALUES(?, ?, ?, ?)";
        return insert(sql, invoiceDetail.getInvoice_id(), invoiceDetail.getProduct_id(), invoiceDetail.getQuantity(), invoiceDetail.getSubTotal());
    }

    @Override
    public InvoiceDetailDTO findById(Long idInvoice, Long idProduct) {
       String sql = "SELECT * FROM invoice_details WHERE invoice_id = ? AND product_id = ?";
       List<InvoiceDetailDTO> invoiceDetails = query(sql, new InvoiceDetailMapper(), idInvoice, idProduct);
       return invoiceDetails.isEmpty() ? null : invoiceDetails.get(0);
    }

    @Override
    public List<Long> findByIdInvoice(Long idInvoice) {
        
        String sql = "SELECT * FROM invoice_details WHERE invoice_id = ?";
        return query(sql, new  IdProductMapper(), idInvoice);
    }

    @Override
    public void deleteByIdInvoice(Long idInvoice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}