/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal.impl;

import com.ecommerceapp.dal.IProductPriceHistoryDAL;
import com.ecommerceapp.dto.ProductPriceHistoryDTO;
import com.ecommerceapp.mapper.impl.ProductPriceHistoryMapper;
import java.util.List;

/**
 *
 * @author HP
 */
public class ProductPriceHistoryDAL extends AbstractDAL<ProductPriceHistoryDTO> implements IProductPriceHistoryDAL {

    @Override
    public List<ProductPriceHistoryDTO> findAll() {
        String sql = "SELECT * FROM product_price_history";
        return query(sql, new ProductPriceHistoryMapper());
    }

    @Override
    public ProductPriceHistoryDTO findById(Long id) {
        String sql = "SELECT * FROM product_price_history WHERE id = ?";
        List<ProductPriceHistoryDTO> productPriceHistory = query(sql, new ProductPriceHistoryMapper(), id);
        return productPriceHistory.isEmpty() ? null : productPriceHistory.get(0);
    }

    @Override
    public Long save(ProductPriceHistoryDTO productPriceHistory) {
        String sql = "INSERT INTO product_price_history(effective_date, price, product_id) VALUES(?,?,?)";
        return insert(sql, productPriceHistory.getEffective_date(), productPriceHistory.getPrice(), productPriceHistory.getProduct_id());
    }

    @Override
    public void update(ProductPriceHistoryDTO productPriceHistory) {
        String sql = "UPDATE product_price_history SET effective_date = ?, price = ?, product_id = ?  WHERE id = ?";
        update(sql,  productPriceHistory.getEffective_date(), productPriceHistory.getPrice(), productPriceHistory.getProduct_id(),productPriceHistory.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM product_price_history WHERE id = ?";
        update(sql, id);
    }
}
