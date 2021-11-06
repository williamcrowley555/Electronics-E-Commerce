/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal;

import com.ecommerceapp.dto.DsNhanVienDoanDTO;
import com.ecommerceapp.dto.NhanVienDTO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IDsNhanVienDoanDAL extends GenericDAL<DsNhanVienDoanDTO> {
    
    List<DsNhanVienDoanDTO> findAll();
    DsNhanVienDoanDTO findById(Long id);
    DsNhanVienDoanDTO findByIdDoanAndIdNhanVien(Long idDoan, Long idNhanVien);
    DsNhanVienDoanDTO findIdNhanVienDoan(Long idDoan, Long idNhanVien);
    Long save(DsNhanVienDoanDTO dsNhanVienDoan);
    List<Long> getFreeNhanVien(LocalDate date);
    List<Long> findByIdDoan(Long idDoan);
    void update(DsNhanVienDoanDTO dsNhanVienDoan);
    void delete(Long id);
    void deleteByIdDoanAndIdNhanVien(Long idDoan, Long idNhanVien);
    
}
