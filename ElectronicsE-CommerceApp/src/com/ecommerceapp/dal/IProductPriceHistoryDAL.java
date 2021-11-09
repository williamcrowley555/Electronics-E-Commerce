/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal;

import com.ecommerceapp.dto.ProductPriceHistoryDTO;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IProductPriceHistoryDAL extends GenericDAL<ProductPriceHistoryDTO> {
    
    List<ProductPriceHistoryDTO> findAll();
    ProductPriceHistoryDTO findById(Long id);
    Long save(ProductPriceHistoryDTO brand);
    void update(ProductPriceHistoryDTO brand);
    void delete(Long id);
}
