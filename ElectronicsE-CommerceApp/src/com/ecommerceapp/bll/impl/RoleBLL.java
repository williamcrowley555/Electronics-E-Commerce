/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll.impl;

import com.ecommerceapp.bll.IRoleBLL;
import com.ecommerceapp.dal.IRoleDAL;
import com.ecommerceapp.dal.impl.RoleDAL;
import com.ecommerceapp.dto.RoleDTO;
import java.util.List;

/**
 *
 * @author Khoa Nguyen
 */
public class RoleBLL implements IRoleBLL{
    private IRoleDAL roleDAL;

    public RoleBLL() {
        this.roleDAL = new RoleDAL();
    }
    
    @Override
    public List<RoleDTO> findAll() {
        return roleDAL.findAll();
    }

    @Override
    public RoleDTO findById(Long id) {
        return roleDAL.findById(id);
    }  

    @Override
    public RoleDTO findByNormalizedName(String name) {
        return roleDAL.findByNormalizedName(name);
    }

    @Override
    public Long save(RoleDTO role) {
        return roleDAL.save(role);
    }

    @Override
    public void update(RoleDTO role) {
        roleDAL.update(role);
    }

    @Override
    public void delete(Long id) {
        roleDAL.delete(id);
    }  
}
