/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll.impl;

import com.ecommerceapp.bll.IKhachHangBLL;
import com.ecommerceapp.dal.impl.KhachHangDAL;
import com.ecommerceapp.dto.KhachHangDTO;
import java.util.List;
import com.ecommerceapp.dal.IKhachHangDAL;

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
