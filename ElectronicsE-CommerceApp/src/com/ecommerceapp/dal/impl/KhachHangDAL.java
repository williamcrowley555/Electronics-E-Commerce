/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal.impl;

import com.ecommerceapp.dto.KhachHangDTO;
import com.ecommerceapp.mapper.impl.KhachHangMapper;
import java.util.List;
import com.ecommerceapp.dal.IKhachHangDAL;

/**
 *
 * @author HP
 */
public class KhachHangDAL extends AbstractDAL<KhachHangDTO> implements IKhachHangDAL {

    @Override
    public List<KhachHangDTO> findAll() {
        String sql = "SELECT * FROM khach_hang";
        return query(sql, new KhachHangMapper());
    }

    @Override
    public KhachHangDTO findById(Long id) {
        String sql = "SELECT * FROM khach_hang WHERE id = ?";
        List<KhachHangDTO> khachHang = query(sql, new KhachHangMapper(), id);
        return khachHang.isEmpty() ? null : khachHang.get(0);
                
    }

    @Override
    public KhachHangDTO findBySdt(String sdt) {
        String sql = "SELECT * FROM khach_hang WHERE sdt = ?";
        List<KhachHangDTO> khachHang = query(sql, new KhachHangMapper(), sdt);
        return khachHang.isEmpty() ? null : khachHang.get(0);
    }

    @Override
    public Long save(KhachHangDTO khachHang) {
        String sql = "INSERT INTO khach_hang(ho, ten, gioi_tinh, cmnd, sdt, dia_chi, hinh_anh, ngay_sinh) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, khachHang.getHo(), khachHang.getTen(), khachHang.getGioiTinh(), khachHang.getCmnd(), khachHang.getSdt(),
                khachHang.getDiaChi(), khachHang.getHinhAnh(), khachHang.getNgaySinh());
    }

    @Override
    public void update(KhachHangDTO khachHang) {
        String sql = "UPDATE khach_hang SET ho = ?, ten = ?, gioi_tinh = ?, cmnd = ?, sdt = ?, dia_chi = ?, "
                + "hinh_anh = ?, ngay_sinh = ? WHERE id = ?";
        update(sql, khachHang.getHo(), khachHang.getTen(), khachHang.getGioiTinh(), khachHang.getCmnd(), khachHang.getSdt(),
                khachHang.getDiaChi(), khachHang.getHinhAnh(), khachHang.getNgaySinh(), khachHang.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM khach_hang WHERE id = ?";
        update(sql, id);
    }
}
