/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal;

import com.ecommerceapp.dto.CatalogDTO;
import com.ecommerceapp.dto.InvoiceDTO;
import com.ecommerceapp.dto.RevenueDTO;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IInvoiceDAL extends GenericDAL<InvoiceDTO> {
    
    List<InvoiceDTO> findAll();
    InvoiceDTO findById(Long id);
    Long save(InvoiceDTO invoice);
    void update(InvoiceDTO invoice);
    void delete(Long id);
    List<RevenueDTO> getMonthlyProductReport(int month);
}
