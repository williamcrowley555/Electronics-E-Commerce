/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll.impl;

import com.ecommerceapp.bll.IVaiTroNhanVienDoanBLL;
import com.ecommerceapp.dal.impl.VaiTroDAL;
import com.ecommerceapp.dal.impl.VaiTroNhanVienDoanDAL;
import com.ecommerceapp.dto.DiaDiemDTO;
import com.ecommerceapp.dto.VaiTroDTO;
import com.ecommerceapp.dto.VaiTroNhanVienDoanDTO;
import java.util.ArrayList;
import java.util.List;
import com.ecommerceapp.dal.IVaiTroDAL;
import com.ecommerceapp.dal.IVaiTroNhanVienDoanDAL;

/**
 *
 * @author kossp
 */
public class VaiTroNhanVienDoanBLL implements IVaiTroNhanVienDoanBLL {
    private IVaiTroNhanVienDoanDAL vaiTroNhanVienDoanDAO;
    private IVaiTroDAL vaiTroDAO;
    public VaiTroNhanVienDoanBLL() {
        this.vaiTroNhanVienDoanDAO = new VaiTroNhanVienDoanDAL();
        this.vaiTroDAO = new VaiTroDAL();
    }    
    
    @Override
    public List<VaiTroNhanVienDoanDTO> findAll() {
        return vaiTroNhanVienDoanDAO.findAll();
    }

   @Override
    public VaiTroNhanVienDoanDTO findById(Long id) {
        return vaiTroNhanVienDoanDAO.findById(id);
    }         
    
    @Override
    public Long save(VaiTroNhanVienDoanDTO vaiTroNhanVienDoan) {
        return vaiTroNhanVienDoanDAO.save(vaiTroNhanVienDoan);
    }

    @Override
    public List<Long> findByIdNhanVien(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VaiTroDTO> findByIdVaiTroNhanVienDoan(Long idVaiTroNhanVienDoan) {
        List<Long> vaiTroIds = vaiTroNhanVienDoanDAO.findByIdDsNhanVienDoan(idVaiTroNhanVienDoan);
        List<VaiTroDTO> vaiTroList = new ArrayList<>();
        for (Long vaiTroId : vaiTroIds)
        {   
            vaiTroList.add(vaiTroDAO.findById(vaiTroId));      
        }
        return vaiTroList;
    }

    @Override
    public void deleteByIdDsNhanVienDoan(Long idDsNhanVienDoan) {
         vaiTroNhanVienDoanDAO.deleteByIdDsNhanVienDoan(idDsNhanVienDoan);
    }
}
