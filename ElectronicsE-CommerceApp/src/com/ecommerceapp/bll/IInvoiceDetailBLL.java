/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll;

import com.ecommerceapp.dto.DsNhanVienDoanDTO;
import com.ecommerceapp.dto.InvoiceDetailDTO;
import com.ecommerceapp.dto.KhachHangDTO;
import com.ecommerceapp.dto.NhanVienDTO;
import com.ecommerceapp.dto.ProductDTO;
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
