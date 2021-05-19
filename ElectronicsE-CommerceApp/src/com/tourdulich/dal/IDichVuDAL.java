/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal;

import com.tourdulich.dto.DichVuDTO;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IDichVuDAL extends GenericDAL<DichVuDTO> {
    
    List<DichVuDTO> findAll();
    DichVuDTO findById(Long id);
    Long save(DichVuDTO dichVu);
    void update(DichVuDTO dichVu);
    void delete(Long id);
}
