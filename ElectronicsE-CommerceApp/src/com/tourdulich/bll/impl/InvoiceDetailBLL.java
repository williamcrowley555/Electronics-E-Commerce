/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll.impl;

import com.tourdulich.bll.IDsDiaDiemTourBLL;
import com.tourdulich.bll.IInvoiceDetailBLL;
import com.tourdulich.dal.impl.DiaDiemDAL;
import com.tourdulich.dal.impl.DsDiaDiemTourDAL;
import com.tourdulich.dto.DiaDiemDTO;
import com.tourdulich.dto.DsDiaDiemTourDTO;
import java.util.ArrayList;
import java.util.List;
import com.tourdulich.dal.IDiaDiemDAL;
import com.tourdulich.dal.IDsDiaDiemTourDAL;
import com.tourdulich.dal.IInvoiceDetailDAL;
import com.tourdulich.dal.IProductDAL;
import com.tourdulich.dal.impl.InvoiceDetailDAL;
import com.tourdulich.dal.impl.ProductDAL;
import com.tourdulich.dto.InvoiceDetailDTO;
import com.tourdulich.dto.ProductDTO;

/**
 *
 * @author HP
 */
public class InvoiceDetailBLL implements IInvoiceDetailBLL {

    private IInvoiceDetailDAL invoiceDetailDAL;
    private IProductDAL productDAL;
    public InvoiceDetailBLL() {
        this.invoiceDetailDAL = new InvoiceDetailDAL();
        this.productDAL = new ProductDAL();
    }
    
    @Override
    public List<InvoiceDetailDTO> findAll() {
        return invoiceDetailDAL.findAll();
    }

    

    @Override
    public Long save(InvoiceDetailDTO invoiceDetail) {
        int quantity = invoiceDetail.getQuantity();
        ProductDTO temp = productDAL.findById(invoiceDetail.getProduct_id());
        temp.setQuantity(temp.getQuantity()-quantity);
        productDAL.update(temp);
        return invoiceDetailDAL.save(invoiceDetail);
    }

    @Override
    public List<ProductDTO> findByIdInvoice(Long idInvoice) {   
        List<Long> productIds = invoiceDetailDAL.findByIdInvoice(idInvoice);
        List<ProductDTO> productList = new ArrayList<>();
        for (Long productId : productIds)
        {   
            productList.add(productDAL.findById(productId));      
        }
        return productList;
    }

   

    @Override
    public InvoiceDetailDTO findById(Long idInvoice, Long idProduct) {
         return invoiceDetailDAL.findById(idInvoice, idProduct);
    }

  

    @Override
    public void deleteByIdInvoice(Long idInvoice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
