/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal;

import com.ecommerceapp.dto.LoaiDuLichDTO;
import java.util.List;

/**
 *
 * @author HP
 */
public interface ILoaiDuLichDAL extends GenericDAL<LoaiDuLichDTO> {
    
    List<LoaiDuLichDTO> findAll();
    LoaiDuLichDTO findById(Long id);
    Long save(LoaiDuLichDTO loaiDuLich);
    void update(LoaiDuLichDTO loaiDuLich);
    void delete(Long id);
}
