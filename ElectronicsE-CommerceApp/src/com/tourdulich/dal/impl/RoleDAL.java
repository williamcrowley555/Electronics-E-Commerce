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
        String sql = "INSERT INTO role(description, image, name, price, status, brand_id, quantity) VALUES(?, ?, ?, ?, ?, ?, ?)";
       // return insert(sql, role.getDescription(), role.getImage(), role.getName(), role.getPrice(), role.isStatus(), role.getBrandId(), role.getQuantity());
        return null;
    }

    @Override
    public void update(RoleDTO role) {
        String sql = "UPDATE role SET description = ?, image = ?, price = ?, status = ?, brand_id = ? quantity = ? WHERE id = ?";
      //  update(sql, role.getDescription(), role.getImage(), role.getPrice(), role.isStatus(), role.getBrandId(), role.getId(), role.getQuantity());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM role WHERE id = ?";
        update(sql, id);
}
}
