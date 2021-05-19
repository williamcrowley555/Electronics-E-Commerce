/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll.impl;

import com.tourdulich.bll.IChiPhiDoanBLL;
import com.tourdulich.dal.impl.ChiPhiDoanDAL;
import com.tourdulich.dto.ChiPhiDoanDTO;
import java.util.List;
import com.tourdulich.dal.IChiPhiDoanDAL;

/**
 *
 * @author kossp
 */
public class ChiPhiDoanBLL implements IChiPhiDoanBLL {
    
    private IChiPhiDoanDAL chiPhiDoanDAO;
    
    public ChiPhiDoanBLL() {
        this.chiPhiDoanDAO = new ChiPhiDoanDAL();
    }
    
    @Override
    public List<ChiPhiDoanDTO> findAll() {
        return chiPhiDoanDAO.findAll();
    }

   @Override
    public ChiPhiDoanDTO findById(Long id) {
        return chiPhiDoanDAO.findById(id);
    }
    
    @Override
    public Long save(ChiPhiDoanDTO chiPhiDoan) {
        return chiPhiDoanDAO.save(chiPhiDoan);
    }

    @Override
    public void update(ChiPhiDoanDTO chiPhiDoan) {
        chiPhiDoanDAO.update(chiPhiDoan);
    }

    @Override
    public void delete(Long id) {
        chiPhiDoanDAO.delete(id);
    }

    @Override
    public List<ChiPhiDoanDTO> findByIdDoan(Long idDoan) {
         return chiPhiDoanDAO.findByIdDoan(idDoan);
    }
}
