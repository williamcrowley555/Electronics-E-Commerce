/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll;

import com.tourdulich.dto.GiaTourDTO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author kossp
 */
public interface IGiaTourBLL {
    List<GiaTourDTO> findAll();
    List<GiaTourDTO> findByIdTour(Long idTour);
    GiaTourDTO findById(Long id);
    List<GiaTourDTO> findByIdTourAndDatesBetween(Long idTour, LocalDate startDate, LocalDate endDate);
    Long save(GiaTourDTO giaTour);
    void update(GiaTourDTO giaTour);
    void delete(Long id);     
}
