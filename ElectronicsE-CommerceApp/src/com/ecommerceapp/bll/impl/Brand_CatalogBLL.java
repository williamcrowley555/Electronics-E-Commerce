/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll.impl;

import com.ecommerceapp.bll.IBrand_CatalogBLL;
import com.ecommerceapp.dal.IBrandDAL;
import com.ecommerceapp.dal.IBrand_CatalogDAL;
import com.ecommerceapp.dal.impl.BrandDAL;
import com.ecommerceapp.dal.impl.Brand_CatalogDAL;
import java.util.ArrayList;
import java.util.List;
import com.ecommerceapp.dto.BrandDTO;
import com.ecommerceapp.dto.Brand_CatalogDTO;
import com.ecommerceapp.dto.CatalogDTO;

/**
 *
 * @author HP
 */
public class Brand_CatalogBLL implements IBrand_CatalogBLL {
    private IBrand_CatalogDAL brand_CatalogDAL;
    private IBrandDAL brandDAL;
    public Brand_CatalogBLL() {
        this.brand_CatalogDAL = new Brand_CatalogDAL();
        this.brandDAL = new BrandDAL();
    }
    
    @Override
    public List<Brand_CatalogDTO> findAll() {
        return brand_CatalogDAL.findAll();
    }

    @Override
    public Brand_CatalogDTO findById(Long idBrand, Long idCatalog) {
        return brand_CatalogDAL.findById(idBrand, idCatalog);
    }

    @Override
    public Long save(Brand_CatalogDTO catalog_brand) {
        return brand_CatalogDAL.save(catalog_brand);
    }

    @Override
    public void update(Brand_CatalogDTO catalog_brand) {
        brand_CatalogDAL.update(catalog_brand);
    }

    @Override
    public void delete(Long idCatalog, Long idBrand) {
        brand_CatalogDAL.delete(idCatalog, idBrand);
    }

    @Override
    public List<BrandDTO> findByIdCatalog(Long idCatalog) {
        List<Long> brandIds = brand_CatalogDAL.findByIdCatalog(idCatalog);
        List<BrandDTO> brandList = new ArrayList<>();
        for(Long brandId : brandIds)
        {
            brandList.add(brandDAL.findById(brandId));
        }
        return brandList;
    }
     
}
