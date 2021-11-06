/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll.impl;

import com.ecommerceapp.bll.IDoanBLL;
import com.ecommerceapp.dal.impl.DoanDAL;
import com.ecommerceapp.dto.DoanDTO;
import java.util.List;
import com.ecommerceapp.dal.IDoanDAL;

/**
 *
 * @author kossp
 */
public class DoanBLL implements IDoanBLL {
    
    private IDoanDAL doanDAO;
    
    public DoanBLL() {
        this.doanDAO = new DoanDAL();
    }    
    
    @Override
    public List<DoanDTO> findAll() {
        return doanDAO.findAll();
    }

   @Override
    public DoanDTO findById(Long id) {
        return doanDAO.findById(id);
    }
    
    @Override
    public Long save(DoanDTO doan) {
        return doanDAO.save(doan);
    }

    @Override
    public void update(DoanDTO doan) {
        doanDAO.update(doan);
    }

    @Override
    public void updateAmount(Long id, Integer amount) {
        doanDAO.updateAmount(id, amount);
    }

    @Override
    public void delete(Long id) {
        doanDAO.delete(id);
    }

    @Override
    public List<DoanDTO> findByIdTour(Long id) {
       return doanDAO.findByIdTour(id);
    }
}
