/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll;

import com.ecommerceapp.dto.ProductPriceHistoryDTO;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IProductPriceHistoryBLL {
    
    List<ProductPriceHistoryDTO> findAll();
    ProductPriceHistoryDTO findById(Long id);
    Long save(ProductPriceHistoryDTO productPriceHistory);
    void update(ProductPriceHistoryDTO productPriceHistory);
    void delete(Long id);
}
