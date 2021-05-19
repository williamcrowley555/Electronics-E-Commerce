/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal.impl;

import com.tourdulich.dto.ChiPhiDoanDTO;
import com.tourdulich.mapper.impl.ChiPhiDoanMapper;
import java.util.List;
import com.tourdulich.dal.IChiPhiDoanDAL;

/**
 *
 * @author kossp
 */
public class ChiPhiDoanDAL extends AbstractDAL<ChiPhiDoanDTO> implements IChiPhiDoanDAL{
    @Override
    public List<ChiPhiDoanDTO> findAll() {
        String sql = "SELECT * FROM chi_phi_doan";
        return query(sql, new ChiPhiDoanMapper());
    }

    @Override
    public ChiPhiDoanDTO findById(Long id) {
        String sql = "SELECT * FROM chi_phi_doan WHERE id = ?";
        List<ChiPhiDoanDTO> chiPhiDoan = query(sql, new ChiPhiDoanMapper(), id);
        return chiPhiDoan.isEmpty() ? null : chiPhiDoan.get(0);
    }

    @Override
    public Long save(ChiPhiDoanDTO chiPhiDoan) {
        String sql = "INSERT INTO chi_phi_doan(id_doan, id_dich_vu, chi_phi, hoa_don, ngay_hoa_don) VALUES(?, ?, ?, ?, ?)";
        return insert(sql, chiPhiDoan.getIdDoan(), chiPhiDoan.getIdDichVu(), chiPhiDoan.getChiPhi(), chiPhiDoan.getHoaDon(), chiPhiDoan.getNgayHoaDon());
    }

    @Override
    public void update(ChiPhiDoanDTO chiPhiDoan) {
        String sql = "UPDATE chi_phi_doan SET id_doan = ?, id_dich_vu = ?, chi_phi = ?, hoa_don = ?, ngay_hoa_don = ? WHERE id = ?";
        update(sql, chiPhiDoan.getIdDoan(), chiPhiDoan.getIdDichVu(), chiPhiDoan.getChiPhi(), chiPhiDoan.getHoaDon(), chiPhiDoan.getNgayHoaDon(), chiPhiDoan.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM chi_phi_doan WHERE id = ?";
        update(sql, id);
    }

    @Override
    public List<ChiPhiDoanDTO> findByIdDoan(Long idDoan) {
        String sql = "SELECT * FROM chi_phi_doan WHERE id_doan = ?";
        return query(sql, new ChiPhiDoanMapper(), idDoan);
    }
}
