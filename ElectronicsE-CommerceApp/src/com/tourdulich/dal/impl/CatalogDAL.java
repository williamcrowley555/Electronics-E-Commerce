/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal.impl;

import com.tourdulich.dal.ICatalogDAL;
import com.tourdulich.dto.LoaiDuLichDTO;
import com.tourdulich.mapper.impl.LoaiDuLichMapper;
import java.util.List;
import com.tourdulich.dal.ILoaiDuLichDAL;
import com.tourdulich.dto.CatalogDTO;
import com.tourdulich.mapper.impl.CatalogMapper;

/**
 *
 * @author HP
 */
public class CatalogDAL extends AbstractDAL<CatalogDTO> implements ICatalogDAL {

    @Override
    public List<CatalogDTO> findAll() {
        String sql = "SELECT * FROM catalog";
        return query(sql, new CatalogMapper());
    }

    @Override
    public CatalogDTO findById(Long id) {
        String sql = "SELECT * FROM catalog WHERE id = ?";
        List<CatalogDTO> catalog = query(sql, new CatalogMapper(), id);
        return catalog.isEmpty() ? null : catalog.get(0);
    }

    @Override
    public Long save(CatalogDTO catalog) {
        String sql = "INSERT INTO catalog(name) VALUES(?)";
        return insert(sql, catalog.getName());
    }

    @Override
    public void update(CatalogDTO catalog) {
        String sql = "UPDATE catalog SET name = ? WHERE id = ?";
        update(sql, catalog.getName(), catalog.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM catalog WHERE id = ?";
        update(sql, id);
    }
}
