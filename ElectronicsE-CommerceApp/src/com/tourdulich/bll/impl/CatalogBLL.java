/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll.impl;

import com.tourdulich.bll.ICatalogBLL;
import com.tourdulich.bll.ILoaiDuLichBLL;
import com.tourdulich.dal.ICatalogDAL;
import com.tourdulich.dal.impl.LoaiDuLichDAL;
import com.tourdulich.dto.LoaiDuLichDTO;
import java.util.List;
import com.tourdulich.dal.ILoaiDuLichDAL;
import com.tourdulich.dal.impl.CatalogDAL;
import com.tourdulich.dto.CatalogDTO;

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
