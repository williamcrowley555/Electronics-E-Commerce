/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal.impl;

import com.ecommerceapp.dto.TinhDTO;
import com.ecommerceapp.mapper.impl.TinhMapper;
import java.util.List;
import com.ecommerceapp.dal.ITinhDAL;

/**
 *
 * @author kossp
 */
public class TinhDAL extends AbstractDAL<TinhDTO> implements ITinhDAL {
    @Override
    public List<TinhDTO> findAll() {
        String sql = "SELECT * FROM tinh";
        return query(sql, new TinhMapper());
    }

    @Override
    public TinhDTO findById(Long id) {
        String sql = "SELECT * FROM tinh WHERE id = ?";
        List<TinhDTO> tinh = query(sql, new TinhMapper(), id);
        return tinh.isEmpty() ? null : tinh.get(0);
    }

    @Override
    public Long save(TinhDTO tinh) {
        String sql = "INSERT INTO tinh(ten_tinh) VALUES(?)";
        return insert(sql, tinh.getTenTinh());
    }

    @Override
    public void update(TinhDTO tinh) {
        String sql = "UPDATE tinh SET ten_tinh = ? WHERE id = ?";
        update(sql, tinh.getTenTinh(), tinh.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM tinh WHERE id = ?";
        update(sql, id);
    }        
}
