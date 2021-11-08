/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll.impl;

import com.ecommerceapp.bll.IInvoiceBLL;
import com.ecommerceapp.dal.IInvoiceDAL;
import java.util.List;
import com.ecommerceapp.dal.impl.InvoiceDAL;
import com.ecommerceapp.dto.InvoiceDTO;

/**
 *
 * @author HP
 */
public class InvoiceBLL implements IInvoiceBLL {

    private IInvoiceDAL invoiceDAL;

    public InvoiceBLL() {
        this.invoiceDAL = new InvoiceDAL();
    }
    
    @Override
    public List<InvoiceDTO> findAll() {
        return invoiceDAL.findAll();
    }

    @Override
    public InvoiceDTO findById(Long id) {
        return invoiceDAL.findById(id);
    }

    @Override
    public Long save(InvoiceDTO invoice) {
        return invoiceDAL.save(invoice);
    }

    @Override
    public void update(InvoiceDTO invoice) {
        invoiceDAL.update(invoice);
    }

    @Override
    public void delete(Long id) {
        invoiceDAL.delete(id);
    }
}
