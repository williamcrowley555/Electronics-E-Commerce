/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.mapper.impl;

import com.tourdulich.dto.UserDTO;
import com.tourdulich.mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Khoa Nguyen
 */
public class UserMapper implements RowMapper<UserDTO> {

    @Override
    public UserDTO mapRow(ResultSet rs) {
        try {
            UserDTO user = new UserDTO();
            user.setId(rs.getLong("id"));
            user.setAddress(rs.getString("address"));
            user.setDob(rs.getDate("dob").toLocalDate());
            user.setEmail(rs.getString("email"));
            user.setEnabled(rs.getBoolean("enabled"));
            user.setFirstName(rs.getString("first_name"));
            user.setGender(rs.getInt("gender"));
            user.setLastName(rs.getString("last_name"));
            user.setPassword(rs.getString("password"));
            user.setPhone(rs.getString("phone"));
            return user;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
        
}
