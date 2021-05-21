/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal;

import com.tourdulich.dto.CatalogDTO;
import com.tourdulich.dto.Brand_CatalogDTO;
import com.tourdulich.dto.LoaiDuLichDTO;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IBrand_CatalogDAL extends GenericDAL<Brand_CatalogDTO> {
    
    List<Brand_CatalogDTO> findAll();
    List<Long> findByIdCatalog(Long idCatalog);
    
    CatalogDTO findById(Long idBrand, Long idCatalog);
    Long save(Brand_CatalogDTO catalog_brand);
    void update(Brand_CatalogDTO catalog_brand);
    void delete(Long idCatalog, Long idBrand);
}
