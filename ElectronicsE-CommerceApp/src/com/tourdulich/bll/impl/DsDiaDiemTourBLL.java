/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll.impl;

import com.tourdulich.bll.IDsDiaDiemTourBLL;
import com.tourdulich.dal.impl.DiaDiemDAL;
import com.tourdulich.dal.impl.DsDiaDiemTourDAL;
import com.tourdulich.dto.DiaDiemDTO;
import com.tourdulich.dto.DsDiaDiemTourDTO;
import java.util.ArrayList;
import java.util.List;
import com.tourdulich.dal.IDiaDiemDAL;
import com.tourdulich.dal.IDsDiaDiemTourDAL;

/**
 *
 * @author HP
 */
public class DsDiaDiemTourBLL implements IDsDiaDiemTourBLL {

    private IDsDiaDiemTourDAL dsDiaDiemTourDAO;
    private IDiaDiemDAL diaDiemDAO;
    public DsDiaDiemTourBLL() {
        this.dsDiaDiemTourDAO = new DsDiaDiemTourDAL();
        this.diaDiemDAO = new DiaDiemDAL();
    }
    
    @Override
    public List<DsDiaDiemTourDTO> findAll() {
        return dsDiaDiemTourDAO.findAll();
    }

    @Override
    public DsDiaDiemTourDTO findById(Long idTour) {
        return dsDiaDiemTourDAO.findById(idTour);
    }

    @Override
    public Long save(DsDiaDiemTourDTO dsDiaDiemTour) {
        return dsDiaDiemTourDAO.save(dsDiaDiemTour);
    }

    @Override
    public List<DiaDiemDTO> findByIdTour(Long idTour) {   
        List<Long> diaDiemIds = dsDiaDiemTourDAO.findByIdTour(idTour);
        List<DiaDiemDTO> diaDiemList = new ArrayList<>();
        for (Long diaDiemId : diaDiemIds)
        {   
            diaDiemList.add(diaDiemDAO.findById(diaDiemId));      
        }
        return diaDiemList;
    }

    @Override
    public void deleteByIdTour(Long idTour) {
          dsDiaDiemTourDAO.deleteByIdTour(idTour);
    }
}
