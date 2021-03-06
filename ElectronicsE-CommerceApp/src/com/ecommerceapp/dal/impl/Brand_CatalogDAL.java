/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal.impl;

import com.ecommerceapp.dal.IBrand_CatalogDAL;
import java.util.List;
import com.ecommerceapp.dto.Brand_CatalogDTO;
import com.ecommerceapp.dto.CatalogDTO;
import com.ecommerceapp.mapper.impl.Brand_CatalogMapper;
import com.ecommerceapp.mapper.impl.IdBrandMapper;

/**
 *
 * @author HP
 */
public class Brand_CatalogDAL extends AbstractDAL<Brand_CatalogDTO> implements IBrand_CatalogDAL {

    @Override
    public List<Brand_CatalogDTO> findAll() {
        String sql = "SELECT * FROM brand_catalog";
        return query(sql, new Brand_CatalogMapper());
    }

    @Override
    public List<Long> findByIdCatalog(Long idCatalog) {
        String sql = "SELECT * FROM brand_catalog WHERE catalog_id = ?";
        return query(sql, new  IdBrandMapper(), idCatalog);
    }

    @Override
    public Long save(Brand_CatalogDTO catalog_brand) {
        String sql = "INSERT INTO brand_catalog(brand_id,catalog_id) VALUES (?,?)";
        return insert(sql,catalog_brand.getBrand_id(),catalog_brand.getCatalog_id());
    }

    @Override
    public void update(Brand_CatalogDTO catalog_brand) {
        String sql = "UPDATE brand_catalog SET brand_id = ? WHERE catalog_id = ?";
        update(sql, catalog_brand.getBrand_id(), catalog_brand.getCatalog_id());
    }

    @Override
    public void delete(Long idCatalog, Long idBrand) {
        String sql = "DELETE FROM brand_catalog WHERE catalog_id = ? AND brand_id = ?";
        update(sql, idCatalog, idBrand);
    }

    @Override
    public Brand_CatalogDTO findById(Long idBrand, Long idCatalog) {
        String sql = "SELECT * FROM brand_catalog WHERE brand_id = ? AND catalog_id = ?";
        List<Brand_CatalogDTO> brand_Catalog = query(sql, new Brand_CatalogMapper(), idBrand, idCatalog);
        return brand_Catalog.isEmpty() ? null : brand_Catalog.get(0);
    }
}
