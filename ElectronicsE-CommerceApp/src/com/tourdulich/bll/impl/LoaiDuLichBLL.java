/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll.impl;

import com.tourdulich.bll.ILoaiDuLichBLL;
import com.tourdulich.dal.impl.LoaiDuLichDAL;
import com.tourdulich.dto.LoaiDuLichDTO;
import java.util.List;
import com.tourdulich.dal.ILoaiDuLichDAL;

/**
 *
 * @author HP
 */
public class LoaiDuLichBLL implements ILoaiDuLichBLL {

    private ILoaiDuLichDAL loaiDuLichDAO;

    public LoaiDuLichBLL() {
        this.loaiDuLichDAO = new LoaiDuLichDAL();
    }
    
    @Override
    public List<LoaiDuLichDTO> findAll() {
        return loaiDuLichDAO.findAll();
    }

    @Override
    public LoaiDuLichDTO findById(Long id) {
        return loaiDuLichDAO.findById(id);
    }

    @Override
    public Long save(LoaiDuLichDTO LoaiDuLich) {
        return loaiDuLichDAO.save(LoaiDuLich);
    }

    @Override
    public void update(LoaiDuLichDTO LoaiDuLich) {
        loaiDuLichDAO.update(LoaiDuLich);
    }

    @Override
    public void delete(Long id) {
        loaiDuLichDAO.delete(id);
    }
}
