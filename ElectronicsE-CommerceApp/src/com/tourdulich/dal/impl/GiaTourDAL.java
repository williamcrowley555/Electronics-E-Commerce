/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal.impl;

import com.tourdulich.dto.GiaTourDTO;
import com.tourdulich.mapper.impl.GiaTourMapper;
import java.time.LocalDate;
import java.util.List;
import com.tourdulich.dal.IGiaTourDAL;

/**
 *
 * @author kossp
 */
public class GiaTourDAL extends AbstractDAL<GiaTourDTO> implements IGiaTourDAL {
    @Override
    public List<GiaTourDTO> findAll() {
        String sql = "SELECT * FROM gia_tour";
        return query(sql, new GiaTourMapper());
    }

    @Override
    public GiaTourDTO findById(Long id) {
        String sql = "SELECT * FROM gia_tour WHERE id = ?";
        List<GiaTourDTO> giaTour = query(sql, new GiaTourMapper(), id);
        return giaTour.isEmpty() ? null : giaTour.get(0);
    }

    @Override
    public List<GiaTourDTO> findByIdTourAndDatesBetween(Long idTour, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT * FROM gia_tour WHERE id_tour = ? AND ngay_bat_dau <= ? AND ngay_ket_thuc >= ?";
        return query(sql, new GiaTourMapper(), idTour, startDate, endDate);
    }

    @Override
    public Long save(GiaTourDTO giaTour) {
        String sql = "INSERT INTO gia_tour(id_tour, ngay_bat_dau, ngay_ket_thuc, gia_tien) VALUES(?, ?, ?, ?)";
        return insert(sql, giaTour.getIdTour(), giaTour.getNgayBatDau(), giaTour.getNgayKetThuc(), giaTour.getGiaTien());
    }

    @Override
    public void update(GiaTourDTO giaTour) {
        String sql = "UPDATE gia_tour SET id_tour = ?, ngay_bat_dau = ?, ngay_ket_thuc = ?, gia_tien = ? WHERE id = ?";
        update(sql, giaTour.getIdTour(), giaTour.getNgayBatDau(), giaTour.getNgayKetThuc(), giaTour.getGiaTien(), giaTour.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM gia_tour WHERE id = ?";
        update(sql, id);
    }

    @Override
    public List<GiaTourDTO> findByIdTour(Long idTour) {
        String sql = "SELECT * FROM gia_tour WHERE id_tour = ?";
        return query(sql, new GiaTourMapper(), idTour);
    }
}
