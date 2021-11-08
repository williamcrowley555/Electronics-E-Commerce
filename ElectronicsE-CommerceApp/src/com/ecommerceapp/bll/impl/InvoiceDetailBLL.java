/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll.impl;

import com.ecommerceapp.bll.IInvoiceDetailBLL;
import java.util.ArrayList;
import java.util.List;
import com.ecommerceapp.dal.IInvoiceDetailDAL;
import com.ecommerceapp.dal.IProductDAL;
import com.ecommerceapp.dal.impl.InvoiceDetailDAL;
import com.ecommerceapp.dal.impl.ProductDAL;
import com.ecommerceapp.dto.InvoiceDetailDTO;
import com.ecommerceapp.dto.ProductDTO;

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
