/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll.impl;

import com.tourdulich.bll.IDsNhanVienDoanBLL;
import com.tourdulich.dal.impl.DsNhanVienDoanDAL;
import com.tourdulich.dal.impl.NhanVienDAL;
import com.tourdulich.dal.impl.VaiTroNhanVienDoanDAL;
import com.tourdulich.dto.DsNhanVienDoanDTO;
import com.tourdulich.dto.KhachHangDTO;
import com.tourdulich.dto.NhanVienDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.tourdulich.dal.IDsNhanVienDoanDAL;
import com.tourdulich.dal.INhanVienDAL;
import com.tourdulich.dal.IVaiTroNhanVienDoanDAL;

/**
 *
 * @author HP
 */
public class DsNhanVienDoanBLL implements IDsNhanVienDoanBLL {
    private INhanVienDAL nhanVienDAO; 
    private IDsNhanVienDoanDAL dsNhanVienDoanDAO;
    private IVaiTroNhanVienDoanDAL vaiTroNhanVienDoanDAO;

    public DsNhanVienDoanBLL() {
        this.nhanVienDAO = new NhanVienDAL();
        this.dsNhanVienDoanDAO = new DsNhanVienDoanDAL();
        this.vaiTroNhanVienDoanDAO = new VaiTroNhanVienDoanDAL();
    }
    
    @Override
    public List<DsNhanVienDoanDTO> findAll() {
        return dsNhanVienDoanDAO.findAll();
    }

    @Override
    public DsNhanVienDoanDTO findById(Long id) {
        return dsNhanVienDoanDAO.findById(id);
    }

    @Override
    public Long save(DsNhanVienDoanDTO NhanVienDoan) {
        return dsNhanVienDoanDAO.save(NhanVienDoan);
    }

    @Override
    public void update(DsNhanVienDoanDTO dsNhanVienDoan) {
        dsNhanVienDoanDAO.update(dsNhanVienDoan);
    }

    @Override
    public void delete(Long id) {
        dsNhanVienDoanDAO.delete(id);
    }

    @Override
    public ArrayList<NhanVienDTO> getFreeNhanVien(LocalDate date) {
        List<Long> nhanVienIds = dsNhanVienDoanDAO.getFreeNhanVien(date);
        ArrayList<NhanVienDTO> nhanVienList = new ArrayList<NhanVienDTO>();
        for (Long nhanVienId : nhanVienIds)
        {
           nhanVienList.add(nhanVienDAO.findById(nhanVienId));     
        }
        return nhanVienList;
    }

    @Override
    public ArrayList<NhanVienDTO> findByIdDoan(Long idDoan) {
        
        List<Long> nhanVienIds = dsNhanVienDoanDAO.findByIdDoan(idDoan);
        ArrayList<NhanVienDTO> nhanVienList = new ArrayList<NhanVienDTO>();
        for (Long nhanVienId : nhanVienIds)
        {
            nhanVienList.add(nhanVienDAO.findById(nhanVienId));
        }
        return nhanVienList;
    }

    @Override
    public DsNhanVienDoanDTO findByIdNhanVienDoan(Long idDoan, Long idNhanVien) {
        return dsNhanVienDoanDAO.findIdNhanVienDoan(idDoan, idNhanVien);
    }

    @Override
    public void deleteByIdDoanAndIdNhanVien(Long idDoan, Long idNhanVien) {
        Long idDsNhanVienDoan = dsNhanVienDoanDAO.findByIdDoanAndIdNhanVien(idDoan, idNhanVien).getId();
        vaiTroNhanVienDoanDAO.deleteByIdDsNhanVienDoan(idDsNhanVienDoan);
        dsNhanVienDoanDAO.deleteByIdDoanAndIdNhanVien(idDoan, idNhanVien);
    }
}
