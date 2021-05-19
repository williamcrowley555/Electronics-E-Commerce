/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll.impl;

import com.tourdulich.bll.IKhachHangBLL;
import com.tourdulich.dal.impl.KhachHangDAL;
import com.tourdulich.dto.KhachHangDTO;
import java.util.List;
import com.tourdulich.dal.IKhachHangDAL;

/**
 *
 * @author HP
 */
public class KhachHangBLL implements IKhachHangBLL {
    
    private IKhachHangDAL khachHangDAO;

    public KhachHangBLL() {
        this.khachHangDAO = new KhachHangDAL();
    }

    @Override
    public List<KhachHangDTO> findAll() {
        return khachHangDAO.findAll();
    }

    @Override
    public KhachHangDTO findById(Long id) {
        return khachHangDAO.findById(id);
    }

    @Override
    public KhachHangDTO findBySdt(String sdt) {
        return khachHangDAO.findBySdt(sdt);
    }

    @Override
    public Long save(KhachHangDTO KhachHang) {
        return khachHangDAO.save(KhachHang);
    }

    @Override
    public void update(KhachHangDTO KhachHang) {
        khachHangDAO.update(KhachHang);
    }

    @Override
    public void delete(Long id) {
        khachHangDAO.delete(id);
    }
}
