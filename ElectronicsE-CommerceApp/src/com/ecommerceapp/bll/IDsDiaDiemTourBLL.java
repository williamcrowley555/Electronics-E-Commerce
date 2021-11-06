/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll;

import com.ecommerceapp.dto.DiaDiemDTO;
import com.ecommerceapp.dto.DsDiaDiemTourDTO;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IDsDiaDiemTourBLL {
    
    List<DsDiaDiemTourDTO> findAll();
    DsDiaDiemTourDTO findById(Long idTour);
    List<DiaDiemDTO> findByIdTour(Long idTour);
    Long save(DsDiaDiemTourDTO dsDiaDiemTour);
    void deleteByIdTour(Long idTour);
}
