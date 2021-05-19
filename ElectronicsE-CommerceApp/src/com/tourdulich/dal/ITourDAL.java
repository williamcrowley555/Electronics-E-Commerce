/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal;

import com.tourdulich.dto.TourDTO;
import java.util.List;

/**
 *
 * @author HP
 */
public interface ITourDAL extends GenericDAL<TourDTO> {
    
    List<TourDTO> findAll();
    TourDTO findById(Long id);
    Long save(TourDTO tour);
    void update(TourDTO tour);
    void delete(Long id);
}
