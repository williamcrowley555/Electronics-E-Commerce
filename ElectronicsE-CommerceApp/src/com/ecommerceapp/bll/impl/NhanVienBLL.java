/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll.impl;

import com.ecommerceapp.bll.INhanVienBLL;
import com.ecommerceapp.dal.impl.NhanVienDAL;
import com.ecommerceapp.dto.NhanVienDTO;
import java.util.List;
import com.ecommerceapp.dal.INhanVienDAL;

/**
 *
 * @author HP
 */
public class NhanVienBLL implements INhanVienBLL {
    
    private INhanVienDAL nhanVienDAO;

    public NhanVienBLL() {
        this.nhanVienDAO = new NhanVienDAL();
    }

    @Override
    public List<NhanVienDTO> findAll() {
        return nhanVienDAO.findAll();
    }

    @Override
    public List<NhanVienDTO> findByStatus(boolean status) {
        return nhanVienDAO.findByStatus(status);
    }

    @Override
    public NhanVienDTO findById(Long id) {
        return nhanVienDAO.findById(id);
    }

    @Override
    public NhanVienDTO findBySdt(String sdt) {
        return nhanVienDAO.findBySdt(sdt);
    }

    @Override
    public Long save(NhanVienDTO nhanVien) {
        return nhanVienDAO.save(nhanVien);
    }

    @Override
    public void update(NhanVienDTO nhanVien) {
        nhanVienDAO.update(nhanVien);
    }

    @Override
    public void updateStatus(boolean status, Long id) {
        nhanVienDAO.updateStatus(status, id);
    }

    @Override
    public void delete(Long id) {
        nhanVienDAO.delete(id);
    }
}
