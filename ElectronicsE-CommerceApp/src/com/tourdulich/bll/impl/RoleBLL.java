/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll.impl;

import com.tourdulich.bll.IRoleBLL;
import com.tourdulich.dal.IRoleDAL;
import com.tourdulich.dal.impl.RoleDAL;
import com.tourdulich.dto.RoleDTO;
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
