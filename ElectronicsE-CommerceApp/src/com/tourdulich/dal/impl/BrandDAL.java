/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal.impl;

import com.tourdulich.dal.IBrandDAL;
import com.tourdulich.dto.LoaiDuLichDTO;
import com.tourdulich.mapper.impl.LoaiDuLichMapper;
import java.util.List;
import com.tourdulich.dal.ILoaiDuLichDAL;
import com.tourdulich.dto.BrandDTO;
import com.tourdulich.mapper.impl.BrandMapper;

/**
 *
 * @author HP
 */
public class BrandDAL extends AbstractDAL<BrandDTO> implements IBrandDAL {

    @Override
    public List<BrandDTO> findAll() {
        String sql = "SELECT * FROM brand";
        return query(sql, new BrandMapper());
    }

    @Override
    public BrandDTO findById(Long id) {
        String sql = "SELECT * FROM brand WHERE id = ?";
        List<BrandDTO> brand = query(sql, new BrandMapper(), id);
        return brand.isEmpty() ? null : brand.get(0);
    }

    @Override
    public Long save(BrandDTO brand) {
        String sql = "INSERT INTO brand(name) VALUES(?)";
        return insert(sql, brand.getName());
    }

    @Override
    public void update(BrandDTO brand) {
        String sql = "UPDATE brand SET name = ? WHERE id = ?";
        update(sql, brand.getName(), brand.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM brand WHERE id = ?";
        update(sql, id);
    }
}
