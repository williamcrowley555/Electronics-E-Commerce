/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll;

import com.tourdulich.dto.DsNhanVienDoanDTO;
import com.tourdulich.dto.KhachHangDTO;
import com.tourdulich.dto.NhanVienDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IDsNhanVienDoanBLL {
    List<DsNhanVienDoanDTO> findAll();
    DsNhanVienDoanDTO findById(Long id);
    DsNhanVienDoanDTO findByIdNhanVienDoan(Long idDoan,Long idNhanVien);
    List <NhanVienDTO> getFreeNhanVien(LocalDate date);
    ArrayList<NhanVienDTO> findByIdDoan(Long idDoan);
    Long save(DsNhanVienDoanDTO NhanVienDoan);
    void update(DsNhanVienDoanDTO dsNhanVienDoan);
    void delete(Long id);
    void deleteByIdDoanAndIdNhanVien(Long idDoan, Long idNhanVien);
}
