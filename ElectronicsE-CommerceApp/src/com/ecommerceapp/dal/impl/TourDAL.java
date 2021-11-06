/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal.impl;

import com.ecommerceapp.dto.TourDTO;
import com.ecommerceapp.mapper.impl.TourMapper;
import java.util.List;
import com.ecommerceapp.dal.ITourDAL;

/**
 *
 * @author HP
 */
public class TourDAL extends AbstractDAL<TourDTO> implements ITourDAL {

    @Override
    public List<TourDTO> findAll() {
        String sql = "SELECT * FROM tour";
        return query(sql, new TourMapper());
    }

    @Override
    public TourDTO findById(Long id) {
        String sql = "SELECT * FROM tour WHERE id = ?";
        List<TourDTO> tour = query(sql, new TourMapper(), id);
        return tour.isEmpty() ? null : tour.get(0);
    }

    @Override
    public Long save(TourDTO tour) {
        String sql = "INSERT INTO tour(ten_tour, id_loai_du_lich, dac_diem) VALUES(?, ?, ?)";
        return insert(sql, tour.getTenTour(), tour.getIdLoaiDuLich(), tour.getDacDiem());
    }

    @Override
    public void update(TourDTO tour) {
        String sql = "UPDATE tour SET ten_tour = ?, id_loai_du_lich = ?, dac_diem = ? WHERE id = ?";
        update(sql, tour.getTenTour(), tour.getIdLoaiDuLich(), tour.getDacDiem(), tour.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM tour WHERE id = ?";
        update(sql, id);
    }
}
