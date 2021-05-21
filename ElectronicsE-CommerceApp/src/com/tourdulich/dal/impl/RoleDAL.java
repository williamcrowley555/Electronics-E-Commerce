/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal.impl;

import com.tourdulich.dal.IRoleDAL;
import com.tourdulich.dto.RoleDTO;
import com.tourdulich.mapper.impl.RoleMapper;
import java.util.List;

/**
 *
 * @author Khoa Nguyen
 */
public class RoleDAL extends AbstractDAL<RoleDTO> implements IRoleDAL {
    @Override
    public List<RoleDTO> findAll() {
        String sql = "SELECT * FROM role";
        return query(sql, new RoleMapper());
    }

    @Override
    public RoleDTO findById(Long id) {
        String sql = "SELECT * FROM role WHERE id = ?";
        List<RoleDTO> role = query(sql, new RoleMapper(), id);
        return role.isEmpty() ? null : role.get(0);
    }    
    
    @Override
    public Long save(RoleDTO role) {
        String sql = "INSERT INTO role(name, normalized_name) VALUES(?, ?)";
        return insert(sql, role.getName(), role.getNormalizedName());
    }

    @Override
    public void update(RoleDTO role) {
        String sql = "UPDATE role SET name = ?, normalized_name = ? WHERE id = ?";
        update(sql, role.getName(), role.getNormalizedName(), role.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM role WHERE id = ?";
        update(sql, id);
}
}
