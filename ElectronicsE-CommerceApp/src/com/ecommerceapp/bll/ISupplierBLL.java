/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll;

import com.ecommerceapp.dto.SupplierDTO;
import java.util.List;

/**
 *
 * @author HP
 */
public interface ISupplierBLL {
    List<SupplierDTO> findAll();
    SupplierDTO findById(Long id);
    SupplierDTO findByEmail(String email);
    SupplierDTO findByPhone(String phone);
    Long save(SupplierDTO supplier);
    void update(SupplierDTO supplier);
    void delete(Long id);
}
