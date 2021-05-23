/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll.impl;

import com.tourdulich.bll.ICatalogBLL;
import com.tourdulich.bll.IInvoiceBLL;
import com.tourdulich.bll.ILoaiDuLichBLL;
import com.tourdulich.dal.ICatalogDAL;
import com.tourdulich.dal.IInvoiceDAL;
import com.tourdulich.dal.impl.LoaiDuLichDAL;
import com.tourdulich.dto.LoaiDuLichDTO;
import java.util.List;
import com.tourdulich.dal.ILoaiDuLichDAL;
import com.tourdulich.dal.impl.CatalogDAL;
import com.tourdulich.dal.impl.InvoiceDAL;
import com.tourdulich.dto.CatalogDTO;
import com.tourdulich.dto.InvoiceDTO;

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
