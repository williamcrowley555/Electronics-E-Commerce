/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal;

import com.ecommerceapp.dto.BrandDTO;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IBrandDAL extends GenericDAL<BrandDTO> {
    
    List<BrandDTO> findAll();
    BrandDTO findById(Long id);
    Long save(BrandDTO brand);
    void update(BrandDTO brand);
    void delete(Long id);
}
