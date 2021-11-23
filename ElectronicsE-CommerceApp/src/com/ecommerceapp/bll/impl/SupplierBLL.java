/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll.impl;

import com.ecommerceapp.bll.ISupplierBLL;
import com.ecommerceapp.dal.ISupplierDAL;
import com.ecommerceapp.dal.impl.SupplierDAL;
import com.ecommerceapp.dto.SupplierDTO;
import java.util.List;

/**
 *
 * @author HP
 */
public class SupplierBLL implements ISupplierBLL {
    
    private ISupplierDAL supplierDAL;

    public SupplierBLL() {
        supplierDAL = new SupplierDAL();
    }

    @Override
    public List<SupplierDTO> findAll() {
        return supplierDAL.findAll();
    }

    @Override
    public SupplierDTO findById(Long id) {
        return supplierDAL.findById(id);
    }

    @Override
    public SupplierDTO findByEmail(String email) {
        return supplierDAL.findByEmail(email);
    }

    @Override
    public SupplierDTO findByPhone(String phone) {
        return supplierDAL.findByPhone(phone);
    }

    @Override
    public Long save(SupplierDTO supplier) {
        return supplierDAL.save(supplier);
    }

    @Override
    public void update(SupplierDTO supplier) {
        supplierDAL.update(supplier);
    }

    @Override
    public void delete(Long id) {
        supplierDAL.delete(id);
    }
    
}
