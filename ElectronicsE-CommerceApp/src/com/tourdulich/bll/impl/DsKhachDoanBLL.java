/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll.impl;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.tourdulich.bll.IDsKhachDoanBLL;
import com.tourdulich.dal.impl.DsKhachDoanDAL;
import com.tourdulich.dal.impl.KhachHangDAL;
import com.tourdulich.dto.DsKhachDoanDTO;
import com.tourdulich.dto.KhachHangDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.tourdulich.dal.IDsKhachDoanDAL;
import com.tourdulich.dal.IKhachHangDAL;

/**
 *
 * @author kossp
 */
public class DsKhachDoanBLL implements IDsKhachDoanBLL {
    private IDsKhachDoanDAL dsKhachDoanDAO;
    private IKhachHangDAL khachHangDAO;
    public DsKhachDoanBLL() {
        this.dsKhachDoanDAO = new DsKhachDoanDAL();
        this.khachHangDAO = new KhachHangDAL();
    }    
    
    @Override
    public List<DsKhachDoanDTO> findAll() {
        return dsKhachDoanDAO.findAll();
    }

   @Override
    public DsKhachDoanDTO findById(Long id) {
        return dsKhachDoanDAO.findById(id);
    }

    @Override
    public List<KhachHangDTO> getFreeKhach(LocalDate date) {
        List<Long> khachHangIds = dsKhachDoanDAO.getFreeKhach(date);
        ArrayList<KhachHangDTO> khachHangList = new ArrayList<KhachHangDTO>();
        for (Long khachHangId : khachHangIds)
        {
           khachHangList.add(khachHangDAO.findById(khachHangId));     
        }
        return khachHangList;
    }
    
    @Override
    public Long save(DsKhachDoanDTO dsKhachDoan) {
        return dsKhachDoanDAO.save(dsKhachDoan);
    }

    @Override
    public void delete(Long id) {
        dsKhachDoanDAO.delete(id);
    }

    @Override
    public ArrayList<KhachHangDTO> findByIdDoan(Long idDoan) {
        List<Long> khachHangIds = dsKhachDoanDAO.findByIdDoan(idDoan);
        ArrayList<KhachHangDTO> khachHangList = new ArrayList<KhachHangDTO>();
        for (Long khachHangId : khachHangIds)
        {
            khachHangList.add(khachHangDAO.findById(khachHangId));
        }
        return khachHangList;
    }

    @Override
    public void deleteByIdDoanAndIdKhachHang(Long idDoan, Long idKhachHang) {
       dsKhachDoanDAO.deleteByIdDoanAndIdKhachHang(idDoan, idKhachHang);
    }
}
