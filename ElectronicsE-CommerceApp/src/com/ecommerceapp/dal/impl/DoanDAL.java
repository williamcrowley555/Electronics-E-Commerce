/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal.impl;

import com.ecommerceapp.dto.DoanDTO;
import com.ecommerceapp.mapper.impl.DoanMapper;
import java.util.List;
import com.ecommerceapp.dal.IDoanDAL;

/**
 *
 * @author kossp
 */
public class DoanDAL extends AbstractDAL<DoanDTO> implements IDoanDAL {
    @Override
    public List<DoanDTO> findAll() {
        String sql = "SELECT * FROM doan";
        return query(sql, new DoanMapper());
    }

    @Override
    public DoanDTO findById(Long id) {
        String sql = "SELECT * FROM doan WHERE id = ?";
        List<DoanDTO> doan = query(sql, new DoanMapper(), id);
        return doan.isEmpty() ? null : doan.get(0);
    }

    @Override
    public List<DoanDTO> findByIdTour(Long id) {
        String sql = "SELECT * FROM doan WHERE id_tour = ?";
        return query(sql, new DoanMapper(), id);
    }

    @Override
    public Long save(DoanDTO doan) {
        String sql = "INSERT INTO doan(ten_doan, ngay_khoi_hanh, ngay_ket_thuc, id_tour, gia_tien) VALUES(?, ?, ?, ?, ?)";
        return insert(sql, doan.getTenDoan(), doan.getNgayKhoiHanh(), doan.getNgayKetThuc(), doan.getIdTour(), doan.getGiaTien());
    }

    @Override
    public void update(DoanDTO doan) {
        String sql = "UPDATE doan SET ten_doan = ?, ngay_khoi_hanh = ?, ngay_ket_thuc = ?, id_tour = ?, gia_tien = ? WHERE id = ?";
        update(sql, doan.getTenDoan(), doan.getNgayKhoiHanh(), doan.getNgayKetThuc(), doan.getIdTour(), doan.getGiaTien(), doan.getId());
    }

    @Override
    public void updateAmount(Long id, Integer amount) {
        String sql = "UPDATE doan SET so_luong = ? WHERE id = ?";
        update(sql, amount, id);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM doan WHERE id = ?";
        update(sql, id);
    }
}
