/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal.impl;

import com.ecommerceapp.dto.DsNhanVienDoanDTO;
import com.ecommerceapp.dto.NhanVienDTO;
import com.ecommerceapp.mapper.impl.DsNhanVienDoanMapper;
import com.ecommerceapp.mapper.impl.IdKhachDoanMapper;
import com.ecommerceapp.mapper.impl.IdMapper;
import com.ecommerceapp.mapper.impl.IdNhanVienDoanMapper;
import com.ecommerceapp.mapper.impl.IdNhanVienMapper;
import java.time.LocalDate;
import java.util.List;
import com.ecommerceapp.dal.IDsNhanVienDoanDAL;

/**
 *
 * @author HP
 */
public class DsNhanVienDoanDAL extends AbstractDAL<DsNhanVienDoanDTO> implements IDsNhanVienDoanDAL {

    @Override
    public List<DsNhanVienDoanDTO> findAll() {
        String sql = "SELECT * FROM ds_nhan_vien_doan";
        return query(sql, new DsNhanVienDoanMapper());
    }

    @Override
    public DsNhanVienDoanDTO findById(Long id) {
        String sql = "SELECT * FROM ds_nhan_vien_doan WHERE id = ?";
        List<DsNhanVienDoanDTO> dsNhanVienDoan = query(sql, new DsNhanVienDoanMapper(), id);
        return dsNhanVienDoan.isEmpty() ? null : dsNhanVienDoan.get(0);
    }

    @Override
    public DsNhanVienDoanDTO findByIdDoanAndIdNhanVien(Long idDoan, Long idNhanVien) {
        String sql = "SELECT * FROM ds_nhan_vien_doan WHERE id_doan = ? AND id_nhan_vien = ?";
        List<DsNhanVienDoanDTO> dsNhanVienDoan = query(sql, new DsNhanVienDoanMapper(), idDoan, idNhanVien);
        return dsNhanVienDoan.isEmpty() ? null : dsNhanVienDoan.get(0);
    }

    @Override
    public Long save(DsNhanVienDoanDTO dsNhanVienDoan) {
        String sql = "INSERT INTO ds_nhan_vien_doan(id_doan, id_nhan_vien) VALUES(?,?)";
        return insert(sql, dsNhanVienDoan.getIdDoan(), dsNhanVienDoan.getIdNhanVien());
    }

    @Override
    public void update(DsNhanVienDoanDTO dsNhanVienDoan) {
        String sql = "UPDATE ds_nhan_vien_doan SET id_doan = ?, id_nhan_vien = ? WHERE id = ?";
        update(sql, dsNhanVienDoan.getIdDoan(), dsNhanVienDoan.getIdNhanVien(), dsNhanVienDoan.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM ds_nhan_vien_doan WHERE id = ?";
        update(sql, id);
    }

    @Override
    public List<Long> getFreeNhanVien(LocalDate date) {
        String sql = "SELECT nhan_vien.id FROM nhan_vien " +
                    "EXCEPT (SELECT DISTINCT nhan_vien.id " +
                    "FROM doan LEFT JOIN ds_nhan_vien_doan ON ds_nhan_vien_doan.id_doan = doan.id " +
                    "RIGHT JOIN nhan_vien ON ds_nhan_vien_doan.id_nhan_vien = nhan_vien.id " +
                    "WHERE ? BETWEEN doan.ngay_khoi_hanh AND doan.ngay_ket_thuc)";
       return query(sql, new IdMapper(), date);
    }

    @Override
    public List<Long> findByIdDoan(Long idDoan) {
       String sql = "SELECT * FROM ds_nhan_vien_doan WHERE id_doan = ?";
       return query(sql, new IdNhanVienDoanMapper(), idDoan);
    }

    @Override
    public void deleteByIdDoanAndIdNhanVien(Long idDoan, Long idNhanVien) {
        String sql = "DELETE FROM ds_nhan_vien_doan WHERE id_doan = ? AND id_nhan_vien = ?";
        update(sql, idDoan, idNhanVien);
    }

    @Override
    public DsNhanVienDoanDTO findIdNhanVienDoan(Long idDoan, Long idNhanVien) {
        String sql = "SELECT * FROM ds_nhan_vien_doan WHERE id_doan = ? AND id_nhan_vien = ?";
        List<DsNhanVienDoanDTO> dsNhanVienDoan = query(sql, new DsNhanVienDoanMapper(), idDoan, idNhanVien);
        return dsNhanVienDoan.isEmpty() ? null : dsNhanVienDoan.get(0);
    }
}
