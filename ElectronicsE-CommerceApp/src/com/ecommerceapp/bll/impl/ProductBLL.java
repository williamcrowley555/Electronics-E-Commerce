/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll.impl;

import com.ecommerceapp.bll.IProductBLL;
import java.util.List;
import com.ecommerceapp.dal.IProductDAL;
import com.ecommerceapp.dal.impl.ProductDAL;
import com.ecommerceapp.dto.ProductDTO;

/**
 *
 * @author HP
 */
public class ProductBLL implements IProductBLL {

    private IProductDAL productDAL;

    public ProductBLL() {
        this.productDAL = new ProductDAL();
    }
    
    @Override
    public List<ProductDTO> findAll() {
        return productDAL.findAll();
    }

    @Override
    public List<ProductDTO> findBySupplierId(Long supplierId) {
        return productDAL.findBySupplierId(supplierId);
    }

    @Override
    public ProductDTO findById(Long id) {
        return productDAL.findById(id);
    }

    @Override
    public Long save(ProductDTO product) {
        return productDAL.save(product);
    }

    @Override
    public void update(ProductDTO product) {
        productDAL.update(product);
    }

    @Override
    public void delete(Long id) {
        productDAL.delete(id);
    }
}
