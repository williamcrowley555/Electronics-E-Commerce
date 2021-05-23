/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll;

import com.tourdulich.dto.DsNhanVienDoanDTO;
import com.tourdulich.dto.InvoiceDetailDTO;
import com.tourdulich.dto.KhachHangDTO;
import com.tourdulich.dto.NhanVienDTO;
import com.tourdulich.dto.ProductDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IInvoiceDetailBLL {
    List<InvoiceDetailDTO> findAll();
    InvoiceDetailDTO findById(Long idInvoice, Long idProduct);
    List<ProductDTO> findByIdInvoice(Long idInvoice);
    Long save(InvoiceDetailDTO invoiceDetail);
    void deleteByIdInvoice(Long idInvoice);
}
