/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal.impl;

import com.tourdulich.dto.LoaiDuLichDTO;
import com.tourdulich.mapper.impl.LoaiDuLichMapper;
import java.util.List;
import com.tourdulich.dal.ILoaiDuLichDAL;

/**
 *
 * @author HP
 */
public class LoaiDuLichDAL extends AbstractDAL<LoaiDuLichDTO> implements ILoaiDuLichDAL {

    @Override
    public List<LoaiDuLichDTO> findAll() {
        String sql = "SELECT * FROM loai_du_lich";
        return query(sql, new LoaiDuLichMapper());
    }

    @Override
    public LoaiDuLichDTO findById(Long id) {
        String sql = "SELECT * FROM loai_du_lich WHERE id = ?";
        List<LoaiDuLichDTO> loaiDuLich = query(sql, new LoaiDuLichMapper(), id);
        return loaiDuLich.isEmpty() ? null : loaiDuLich.get(0);
    }

    @Override
    public Long save(LoaiDuLichDTO loaiDuLich) {
        String sql = "INSERT INTO loai_du_lich(ten_loai_du_lich) VALUES(?)";
        return insert(sql, loaiDuLich.getTenLoaiDuLich());
    }

    @Override
    public void update(LoaiDuLichDTO loaiDuLich) {
        String sql = "UPDATE loai_du_lich SET ten_loai_du_lich = ? WHERE id = ?";
        update(sql, loaiDuLich.getTenLoaiDuLich(), loaiDuLich.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM loai_du_lich WHERE id = ?";
        update(sql, id);
    }
}
