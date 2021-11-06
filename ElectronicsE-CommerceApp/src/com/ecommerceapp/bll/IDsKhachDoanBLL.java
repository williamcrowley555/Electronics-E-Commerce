/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll;

import com.ecommerceapp.dto.DsKhachDoanDTO;
import com.ecommerceapp.dto.KhachHangDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kossp
 */
public interface IDsKhachDoanBLL {
    List<DsKhachDoanDTO> findAll();
    DsKhachDoanDTO findById(Long id);
    ArrayList<KhachHangDTO> findByIdDoan(Long idDoan);
    List <KhachHangDTO> getFreeKhach(LocalDate date);
    Long save(DsKhachDoanDTO dsKhachDoan);
    void delete(Long id);     
    void deleteByIdDoanAndIdKhachHang(Long idDoan, Long idKhachHang);
}
