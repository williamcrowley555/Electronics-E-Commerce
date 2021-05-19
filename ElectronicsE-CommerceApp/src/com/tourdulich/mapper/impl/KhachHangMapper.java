/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.mapper.impl;

import com.tourdulich.dto.KhachHangDTO;
import com.tourdulich.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author HP
 */
public class KhachHangMapper implements RowMapper<KhachHangDTO>{

    @Override
    public KhachHangDTO mapRow(ResultSet rs) {
        try {
            KhachHangDTO khachHang = new KhachHangDTO();
            khachHang.setId(rs.getLong("id"));
            khachHang.setHo(rs.getString("ho"));
            khachHang.setTen(rs.getString("ten"));
            khachHang.setGioiTinh(rs.getBoolean("gioi_tinh"));
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//            KhachHang.setNgaySinh(df.format(rs.getDate("ngay_sinh")));
            khachHang.setCmnd(rs.getString("cmnd"));
            khachHang.setSdt(rs.getString("sdt"));
            khachHang.setDiaChi(rs.getString("dia_chi"));
            khachHang.setHinhAnh(rs.getBytes("hinh_anh"));
            khachHang.setNgaySinh(rs.getDate("ngay_sinh").toLocalDate());
            
            return khachHang;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
