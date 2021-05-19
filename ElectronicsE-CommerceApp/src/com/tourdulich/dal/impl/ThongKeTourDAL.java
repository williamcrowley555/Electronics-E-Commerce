/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal.impl;

import com.tourdulich.dto.ThongKeTourDTO;
import com.tourdulich.mapper.impl.ThongKeTourMapper;
import java.util.List;
import com.tourdulich.dal.IThongKeTourDAL;

/**
 *
 * @author kossp
 */
public class ThongKeTourDAL extends AbstractDAL<ThongKeTourDTO> implements IThongKeTourDAL{
    @Override
    public List<ThongKeTourDTO> findAll() {
        String sql = "SELECT DISTINCT doanh_thu_doan.tour_id, doanh_thu_doan.ten_tour, COUNT(DISTINCT doanh_thu_doan.doan_id) AS tong_so_doan,\n" +
        "SUM(doanh_thu_doan.doanh_thu) AS tong_doanh_thu,\n" +
        "SUM(doanh_thu_doan.tong_chi_phi) AS tong_chi_phi,\n" +
        "SUM(doanh_thu_doan. lai) AS lai\n" +
        "FROM (SELECT DISTINCT d.id AS doan_id, d.ten_doan, tour.id AS tour_id, tour.ten_tour, COUNT(DISTINCT dskd.id_khach) AS so_khach, d.gia_tien,\n" +
        "(COUNT(DISTINCT dskd.id_khach) * d.gia_tien) AS doanh_thu,\n" +
        "SUM(DISTINCT cpd.chi_phi) AS tong_chi_phi, ((COUNT(DISTINCT dskd.id_khach) * d.gia_tien) - SUM(DISTINCT cpd.chi_phi)) AS lai\n" +
        "FROM (ds_khach_doan AS dskd RIGHT JOIN doan AS d ON dskd.id_doan = d.id)\n" +
        "LEFT JOIN chi_phi_doan AS cpd ON d.id = cpd.id_doan\n" +
        "RIGHT JOIN tour ON d.id_tour = tour.id\n" +
        "GROUP BY d.id, d.ten_doan, d.gia_tien, tour.id, tour.ten_tour) AS doanh_thu_doan\n" +
        "GROUP BY doanh_thu_doan.tour_id, doanh_thu_doan.ten_tour\n" +
        "ORDER BY doanh_thu_doan.tour_id";
        return query(sql, new ThongKeTourMapper());
    }
}
