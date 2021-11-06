/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll;

import com.ecommerceapp.dto.DichVuDTO;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IDichVuBLL {
    
    List<DichVuDTO> findAll();
    DichVuDTO findById(Long id);
    Long save(DichVuDTO dichVu);
    void update(DichVuDTO dichVu);
    void delete(Long id);
}
