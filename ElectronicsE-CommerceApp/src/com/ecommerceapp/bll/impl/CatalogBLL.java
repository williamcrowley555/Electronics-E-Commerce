/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll.impl;

import com.ecommerceapp.bll.ICatalogBLL;
import com.ecommerceapp.bll.ILoaiDuLichBLL;
import com.ecommerceapp.dal.ICatalogDAL;
import com.ecommerceapp.dal.impl.LoaiDuLichDAL;
import com.ecommerceapp.dto.LoaiDuLichDTO;
import java.util.List;
import com.ecommerceapp.dal.ILoaiDuLichDAL;
import com.ecommerceapp.dal.impl.CatalogDAL;
import com.ecommerceapp.dto.CatalogDTO;

/**
 *
 * @author HP
 */
public class CatalogBLL implements ICatalogBLL {

    private ICatalogDAL catalogDAL;

    public CatalogBLL() {
        this.catalogDAL = new CatalogDAL();
    }
    
    @Override
    public List<CatalogDTO> findAll() {
        return catalogDAL.findAll();
    }

    @Override
    public CatalogDTO findById(Long id) {
        return catalogDAL.findById(id);
    }

    @Override
    public Long save(CatalogDTO catalog) {
        return catalogDAL.save(catalog);
    }

    @Override
    public void update(CatalogDTO catalog) {
        catalogDAL.update(catalog);
    }

    @Override
    public void delete(Long id) {
        catalogDAL.delete(id);
    }
}
