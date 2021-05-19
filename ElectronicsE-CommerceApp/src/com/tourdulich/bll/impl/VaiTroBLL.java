/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll.impl;

import com.tourdulich.bll.IVaiTroBLL;
import com.tourdulich.dal.impl.VaiTroDAL;
import com.tourdulich.dto.VaiTroDTO;
import java.util.List;
import com.tourdulich.dal.IVaiTroDAL;

/**
 *
 * @author HP
 */
public class VaiTroBLL implements IVaiTroBLL {

    private IVaiTroDAL vaiTroDAO;

    public VaiTroBLL() {
        this.vaiTroDAO = new VaiTroDAL();
    }
    
    @Override
    public List<VaiTroDTO> findAll() {
        return vaiTroDAO.findAll();
    }

    @Override
    public VaiTroDTO findById(Long id) {
        return vaiTroDAO.findById(id);
    }

    @Override
    public Long save(VaiTroDTO vaiTro) {
        return vaiTroDAO.save(vaiTro);
    }

    @Override
    public void update(VaiTroDTO vaiTro) {
        vaiTroDAO.update(vaiTro);
    }

    @Override
    public void delete(Long id) {
        vaiTroDAO.delete(id);
    }
}
