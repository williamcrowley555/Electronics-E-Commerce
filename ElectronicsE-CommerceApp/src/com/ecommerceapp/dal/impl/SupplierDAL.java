/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal.impl;

import com.ecommerceapp.dal.ISupplierDAL;
import com.ecommerceapp.dto.SupplierDTO;
import com.ecommerceapp.mapper.impl.SupplierMapper;
import java.util.List;

/**
 *
 * @author HP
 */
public class SupplierDAL extends AbstractDAL<SupplierDTO> implements ISupplierDAL {
    
    @Override
    public List<SupplierDTO> findAll() {
        String sql = "SELECT * FROM supplier";
        return query(sql, new SupplierMapper());
    }

    @Override
    public SupplierDTO findById(Long id) {
        String sql = "SELECT * FROM supplier WHERE id = ?";
        List<SupplierDTO> supplier = query(sql, new SupplierMapper(), id);
        return supplier.isEmpty() ? null : supplier.get(0);
    }
    
    @Override
    public SupplierDTO findByEmail(String email) {
        String sql = "SELECT * FROM supplier WHERE email = ?";
        List<SupplierDTO> supplier = query(sql, new SupplierMapper(), email);
        return supplier.isEmpty() ? null : supplier.get(0);
    }
    
    @Override
    public SupplierDTO findByPhone(String phone) {
        String sql = "SELECT * FROM supplier WHERE phone = ?";
        List<SupplierDTO> supplier = query(sql, new SupplierMapper(), phone);
        return supplier.isEmpty() ? null : supplier.get(0);
    }
   
    @Override
    public Long save(SupplierDTO supplier) {
        String sql = "INSERT INTO supplier(company_name, contact_name, contact_job_title, address, phone, email, country) VALUES(?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, supplier.getCompanyName(), supplier.getContactName(), supplier.getContactJobTitle(), supplier.getAddress(), supplier.getPhone(), supplier.getEmail(), supplier.getCountry());
    }

    @Override
    public void update(SupplierDTO supplier) {
        String sql = "UPDATE supplier SET company_name = ?, contact_name = ?, contact_job_title = ?, address = ?, phone = ?, email = ?, country = ? WHERE id = ?";
        update(sql, supplier.getCompanyName(), supplier.getContactName(), supplier.getContactJobTitle(), supplier.getAddress(), supplier.getPhone(), supplier.getEmail(), supplier.getCountry(), supplier.getId());

    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM supplier WHERE id = ?";
        update(sql, id);
    }
}
