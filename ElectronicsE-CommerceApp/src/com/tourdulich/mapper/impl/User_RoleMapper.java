/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.mapper.impl;

import com.tourdulich.dto.User_RoleDTO;
import com.tourdulich.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Khoa Nguyen
 */
public class User_RoleMapper implements RowMapper<User_RoleDTO>{

    @Override
    public User_RoleDTO mapRow(ResultSet rs) {
        try {
            User_RoleDTO user_role = new User_RoleDTO();
            user_role.setUser_id(rs.getLong("user_id"));
            user_role.setRole_id(rs.getLong("role_id"));   
            return user_role;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }    
}
