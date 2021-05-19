/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal.impl;

import com.tourdulich.dto.DichVuDTO;
import com.tourdulich.mapper.impl.DichVuMapper;
import java.util.List;
import com.tourdulich.dal.IDichVuDAL;

/**
 *
 * @author HP
 */
public class DichVuDAL extends AbstractDAL<DichVuDTO> implements IDichVuDAL {

    @Override
    public List<DichVuDTO> findAll() {
        String sql = "SELECT * FROM dich_vu";
        return query(sql, new DichVuMapper());
    }

    @Override
    public DichVuDTO findById(Long id) {
        String sql = "SELECT * FROM dich_vu WHERE id = ?";
        List<DichVuDTO> dichVu = query(sql, new DichVuMapper(), id);
        return dichVu.isEmpty() ? null : dichVu.get(0);
    }

    @Override
    public Long save(DichVuDTO dichVu) {
        String sql = "INSERT INTO dich_vu(ten_dich_vu, mo_ta) VALUES(?, ?)";
        return insert(sql, dichVu.getTenDichVu(), dichVu.getMoTa());
    }

    @Override
    public void update(DichVuDTO dichVu) {
        String sql = "UPDATE dich_vu SET ten_dich_vu = ?, mo_ta = ? WHERE id = ?";
        update(sql, dichVu.getTenDichVu(), dichVu.getMoTa(), dichVu.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM dich_vu WHERE id = ?";
        update(sql, id);
    }
}
