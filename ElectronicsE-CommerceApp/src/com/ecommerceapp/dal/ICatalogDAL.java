/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal;

import com.ecommerceapp.dto.CatalogDTO;
import java.util.List;

/**
 *
 * @author HP
 */
public interface ICatalogDAL extends GenericDAL<CatalogDTO> {
    
    List<CatalogDTO> findAll();
    CatalogDTO findById(Long id);
    Long save(CatalogDTO catalog);
    void update(CatalogDTO catalog);
    void delete(Long id);
}
