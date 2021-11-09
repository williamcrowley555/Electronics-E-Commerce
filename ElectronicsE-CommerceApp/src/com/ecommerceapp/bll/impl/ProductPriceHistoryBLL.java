/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll.impl;

import com.ecommerceapp.bll.IProductPriceHistoryBLL;
import com.ecommerceapp.dal.IProductPriceHistoryDAL;
import com.ecommerceapp.dal.impl.ProductPriceHistoryDAL;
import com.ecommerceapp.dto.ProductPriceHistoryDTO;
import java.util.List;

/**
 *
 * @author HP
 */
public class ProductPriceHistoryBLL implements IProductPriceHistoryBLL {

    private IProductPriceHistoryDAL productPriceHistoryDAL;

    public ProductPriceHistoryBLL() {
        this.productPriceHistoryDAL = new ProductPriceHistoryDAL();
    }
    
    @Override
    public List<ProductPriceHistoryDTO> findAll() {
        return productPriceHistoryDAL.findAll();
    }

    @Override
    public ProductPriceHistoryDTO findById(Long id) {
        return productPriceHistoryDAL.findById(id);
    }

    @Override
    public Long save(ProductPriceHistoryDTO productPriceHistory) {
        return productPriceHistoryDAL.save(productPriceHistory);
    }

    @Override
    public void update(ProductPriceHistoryDTO productPriceHistory) {
        productPriceHistoryDAL.update(productPriceHistory);
    }

    @Override
    public void delete(Long id) {
        productPriceHistoryDAL.delete(id);
    }
}
