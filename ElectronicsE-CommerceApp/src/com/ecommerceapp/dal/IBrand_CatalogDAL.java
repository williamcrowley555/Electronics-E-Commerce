/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal;

import com.ecommerceapp.dto.CatalogDTO;
import com.ecommerceapp.dto.Brand_CatalogDTO;
import com.ecommerceapp.dto.LoaiDuLichDTO;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IBrand_CatalogDAL extends GenericDAL<Brand_CatalogDTO> {
    
    List<Brand_CatalogDTO> findAll();
    List<Long> findByIdCatalog(Long idCatalog);
    
    Brand_CatalogDTO findById(Long idBrand, Long idCatalog);
    Long save(Brand_CatalogDTO catalog_brand);
    void update(Brand_CatalogDTO catalog_brand);
    void delete(Long idCatalog, Long idBrand);
}
