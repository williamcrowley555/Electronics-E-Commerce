/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll;

import com.tourdulich.dto.BrandDTO;
import com.tourdulich.dto.Brand_CatalogDTO;
import com.tourdulich.dto.CatalogDTO;
import com.tourdulich.dto.DiaDiemDTO;
import com.tourdulich.dto.DsDiaDiemTourDTO;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IBrand_CatalogBLL {
    List<Brand_CatalogDTO> findAll();
    List<BrandDTO> findByIdCatalog(Long idCatalog);
    Brand_CatalogDTO findById(Long idBrand, Long idCatalog);
    Long save(Brand_CatalogDTO catalog_brand);
    void update(Brand_CatalogDTO catalog_brand);
    void delete(Long idCatalog, Long idBrand);
}

