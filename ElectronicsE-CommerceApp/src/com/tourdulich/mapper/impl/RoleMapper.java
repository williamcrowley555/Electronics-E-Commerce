/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.mapper.impl;

import com.tourdulich.dto.RoleDTO;
import com.tourdulich.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Khoa Nguyen
 */
public class RoleMapper implements RowMapper<RoleDTO>{

    @Override
    public RoleDTO mapRow(ResultSet rs) {
        try {
            RoleDTO role = new RoleDTO();
            role.setId(rs.getLong("id"));
            role.setName(rs.getString("name"));
            role.setNormalizedName(rs.getString("normalized_name"));
            return role;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}    

