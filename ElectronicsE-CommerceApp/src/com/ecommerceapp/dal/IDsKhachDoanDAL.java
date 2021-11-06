/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal;

import com.ecommerceapp.dto.DsKhachDoanDTO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author kossp
 */
public interface IDsKhachDoanDAL {
    List<DsKhachDoanDTO> findAll();
    DsKhachDoanDTO findById(Long id);
    List<Long> findByIdDoan(Long idDoan);
    List<Long> getFreeKhach(LocalDate date);
    Long save(DsKhachDoanDTO dsKhachDoan);
    void delete(Long id); 
    void deleteByIdDoanAndIdKhachHang(Long idDoan, Long idKhachHang);
    
}
