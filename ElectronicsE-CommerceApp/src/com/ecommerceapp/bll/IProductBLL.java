/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll;

import com.ecommerceapp.dto.ProductDTO;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IProductBLL {
    List<ProductDTO> findAll();
    List<ProductDTO> findBySupplierId(Long supplierId);
    ProductDTO findById(Long id);
    Long save(ProductDTO product);
    void update(ProductDTO product);
    void delete(Long id); 
}
