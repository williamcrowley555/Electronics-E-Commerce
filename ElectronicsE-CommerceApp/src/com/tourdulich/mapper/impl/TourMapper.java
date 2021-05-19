/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.mapper.impl;

import com.tourdulich.dto.TourDTO;
import com.tourdulich.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class TourMapper implements RowMapper<TourDTO>{

    @Override
    public TourDTO mapRow(ResultSet rs) {
        try {
            TourDTO tour = new TourDTO();
            tour.setId(rs.getLong("id"));
            tour.setTenTour(rs.getString("ten_tour"));
            tour.setIdLoaiDuLich(rs.getLong("id_loai_du_lich"));
            tour.setDacDiem(rs.getString("dac_diem"));

            return tour;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}
