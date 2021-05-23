/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal;

import com.tourdulich.dto.CatalogDTO;
import com.tourdulich.dto.InvoiceDTO;
import com.tourdulich.dto.LoaiDuLichDTO;
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
}
