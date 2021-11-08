/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal;

import com.ecommerceapp.dto.InvoiceDetailDTO;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IInvoiceDetailDAL extends GenericDAL<InvoiceDetailDTO> {
    
    List<InvoiceDetailDTO> findAll();
    InvoiceDetailDTO findById(Long idInvoice, Long idProduct);
    List<Long> findByIdInvoice(Long idInvoice);
    Long save(InvoiceDetailDTO invoiceDetail);
    void deleteByIdInvoice(Long idInvoice);
}
