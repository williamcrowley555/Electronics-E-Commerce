/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal.impl;

import com.tourdulich.dal.IBrand_CatalogDAL;
import com.tourdulich.dto.DsDiaDiemTourDTO;
import com.tourdulich.mapper.impl.DsDiaDiemTourMapper;
import com.tourdulich.mapper.impl.IdDiaDiemTourMapper;
import java.util.List;
import com.tourdulich.dal.IDsDiaDiemTourDAL;
import com.tourdulich.dto.Brand_CatalogDTO;
import com.tourdulich.dto.CatalogDTO;
import com.tourdulich.mapper.impl.Brand_CatalogMapper;
import com.tourdulich.mapper.impl.IdBrandMapper;

/**
 *
 * @author HP
 */
public class Brand_CatalogDAL extends AbstractDAL<Brand_CatalogDTO> implements IBrand_CatalogDAL {

    @Override
    public List<Brand_CatalogDTO> findAll() {
        String sql = "SELECT * FROM ds_dia_diem_tour ORDER BY stt ASC";
        return query(sql, new Brand_CatalogMapper());
    }

    @Override
    public List<Long> findByIdCatalog(Long idCatalog) {
        String sql = "SELECT * FROM brand_catalog WHERE catalog_id = ?";
        return query(sql, new  IdBrandMapper(), idCatalog);
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

    @Override
    public CatalogDTO findById(Long idBrand, Long idCatalog) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
