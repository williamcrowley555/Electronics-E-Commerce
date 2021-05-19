/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal.impl;

import com.tourdulich.dto.DsKhachDoanDTO;
import com.tourdulich.mapper.impl.DsKhachDoanMapper;
import com.tourdulich.mapper.impl.IdKhachDoanMapper;
import com.tourdulich.mapper.impl.IdMapper;
import java.time.LocalDate;
import java.util.List;
import com.tourdulich.dal.IDsKhachDoanDAL;

/**
 *
 * @author kossp
 */
public class DsKhachDoanDAL extends AbstractDAL<DsKhachDoanDTO> implements IDsKhachDoanDAL{
    @Override
    public List<DsKhachDoanDTO> findAll() {
        String sql = "SELECT * FROM ds_khach_doan";
        return query(sql, new DsKhachDoanMapper());
    }

    @Override
    public DsKhachDoanDTO findById(Long id) {
        String sql = "SELECT * FROM ds_khach_doan WHERE id = ?";
        List<DsKhachDoanDTO> dsKhachDoan = query(sql, new DsKhachDoanMapper(), id);
        return dsKhachDoan.isEmpty() ? null : dsKhachDoan.get(0);
    }

    @Override
    public List<Long> getFreeKhach(LocalDate date) {
        String sql = "SELECT khach_hang.id FROM khach_hang " +
                    "EXCEPT (SELECT DISTINCT khach_hang.id " +
                    "FROM doan LEFT JOIN ds_khach_doan ON ds_khach_doan.id_doan = doan.id " +
                    "RIGHT JOIN khach_hang ON ds_khach_doan.id_khach = khach_hang.id " +
                    "WHERE ? BETWEEN doan.ngay_khoi_hanh AND doan.ngay_ket_thuc)";
       return query(sql, new IdMapper(), date);
    }

    @Override
    public Long save(DsKhachDoanDTO dsKhachDoan) {
        String sql = "INSERT INTO ds_khach_doan(id_doan, id_khach) VALUES(?, ?)";
        return insert(sql, dsKhachDoan.getIdDoan(), dsKhachDoan.getIdKhach());
    }


    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM ds_khach_doan WHERE id = ?";
        update(sql, id);
    }        

    @Override
    public List<Long> findByIdDoan(Long idDoan) {
        String sql = "SELECT * FROM ds_khach_doan WHERE id_doan = ?";
        return query(sql, new IdKhachDoanMapper(), idDoan);
    }

    @Override
    public void deleteByIdDoanAndIdKhachHang(Long idDoan, Long idKhachHang) {
        String sql = "DELETE FROM ds_khach_doan WHERE id_doan = ? AND id_khach = ?";
        update(sql, idDoan, idKhachHang);
    }
}
