/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll.impl;

import com.ecommerceapp.bll.IDichVuBLL;
import com.ecommerceapp.dal.impl.DichVuDAL;
import com.ecommerceapp.dto.DichVuDTO;
import java.util.List;
import com.ecommerceapp.dal.IDichVuDAL;

/**
 *
 * @author HP
 */
public class DichVuBLL implements IDichVuBLL {

    private IDichVuDAL dichVuDAO;

    public DichVuBLL() {
        this.dichVuDAO = new DichVuDAL();
    }
    
    @Override
    public List<DichVuDTO> findAll() {
        return dichVuDAO.findAll();
    }

    @Override
    public DichVuDTO findById(Long id) {
        return dichVuDAO.findById(id);
    }

    @Override
    public Long save(DichVuDTO dichVu) {
        return dichVuDAO.save(dichVu);
    }

    @Override
    public void update(DichVuDTO dichVu) {
        dichVuDAO.update(dichVu);
    }

    @Override
    public void delete(Long id) {
        dichVuDAO.delete(id);
    }
}
