/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal;

import com.tourdulich.dto.ChiPhiDoanDTO;
import com.tourdulich.dto.ProductDTO;
import java.util.List;

/**
 *
 * @author kossp
 */
public interface IProductDAL {
    List<ProductDTO> findAll();
    ProductDTO findById(Long id);
    Long save(ProductDTO product);
    void update(ProductDTO product);
    void delete(Long id);    
}