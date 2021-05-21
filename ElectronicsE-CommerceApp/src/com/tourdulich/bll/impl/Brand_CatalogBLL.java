/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll.impl;

import com.tourdulich.bll.IBrand_CatalogBLL;
import com.tourdulich.bll.IDsDiaDiemTourBLL;
import com.tourdulich.dal.IBrandDAL;
import com.tourdulich.dal.IBrand_CatalogDAL;
import com.tourdulich.dal.impl.DiaDiemDAL;
import com.tourdulich.dal.impl.DsDiaDiemTourDAL;
import com.tourdulich.dto.DiaDiemDTO;
import com.tourdulich.dto.DsDiaDiemTourDTO;
import java.util.ArrayList;
import java.util.List;
import com.tourdulich.dal.IDiaDiemDAL;
import com.tourdulich.dal.IDsDiaDiemTourDAL;
import com.tourdulich.dal.impl.BrandDAL;
import com.tourdulich.dal.impl.Brand_CatalogDAL;
import com.tourdulich.dto.BrandDTO;
import com.tourdulich.dto.Brand_CatalogDTO;
import com.tourdulich.dto.CatalogDTO;

/**
 *
 * @author HP
 */
public class Brand_CatalogBLL implements IBrand_CatalogBLL {

    private IDsDiaDiemTourDAL dsDiaDiemTourDAO;
    private IDiaDiemDAL diaDiemDAO;
    
    private IBrand_CatalogDAL brand_catalogDAL;
    private IBrandDAL brandDAL;
    
    public Brand_CatalogBLL() {
         
        
        this.brand_catalogDAL = new Brand_CatalogDAL();
        this.brandDAL = new BrandDAL();
    }
    
    @Override
    public List<Brand_CatalogDTO> findAll() {
        return brand_catalogDAL.findAll();
    }

     

    @Override
    public List<BrandDTO> findByIdCatalog(Long idCatalog) {   
        List<Long> brandIds = brand_catalogDAL.findByIdCatalog(idCatalog);
        List<BrandDTO> brandList = new ArrayList<>();
        for (Long brandId : brandIds)
        {   
            brandList.add(brandDAL.findById(brandId));      
        }
        return brandList;
    }

    @Override
    public CatalogDTO findById(Long idBrand, Long idCatalog) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long save(Brand_CatalogDTO catalog_brand) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Brand_CatalogDTO catalog_brand) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long idCatalog, Long idBrand) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     
}
