/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal;

import com.tourdulich.dto.NhanVienDTO;
import java.util.List;

/**
 *
 * @author HP
 */
public interface INhanVienDAL extends GenericDAL<NhanVienDTO> {
    
    List<NhanVienDTO> findAll();
    List<NhanVienDTO> findByStatus(boolean status);
    NhanVienDTO findById(Long id);
    NhanVienDTO findBySdt(String sdt);
    Long save(NhanVienDTO nhanVien);
    void update(NhanVienDTO nhanVien);
    void updateStatus(boolean status, Long id);
    void delete(Long id);
}
