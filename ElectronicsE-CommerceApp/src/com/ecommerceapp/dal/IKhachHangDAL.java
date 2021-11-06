/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal;

import com.ecommerceapp.dto.KhachHangDTO;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IKhachHangDAL extends GenericDAL<KhachHangDTO> {
    
    List<KhachHangDTO> findAll();
    KhachHangDTO findById(Long id);
    KhachHangDTO findBySdt(String sdt);
    Long save(KhachHangDTO khachHang);
    void update(KhachHangDTO khachHang);
    void delete(Long id);
}
