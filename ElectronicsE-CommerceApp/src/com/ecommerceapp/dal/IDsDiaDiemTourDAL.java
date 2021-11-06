/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal;

import com.ecommerceapp.dto.DsDiaDiemTourDTO;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IDsDiaDiemTourDAL extends GenericDAL<DsDiaDiemTourDTO> {
    
    List<DsDiaDiemTourDTO> findAll();
    DsDiaDiemTourDTO findById(Long id);
    List<Long> findByIdTour(Long idTour);
    Long save(DsDiaDiemTourDTO dsDiaDiemTour);
    void deleteByIdTour(Long idTour);
}
