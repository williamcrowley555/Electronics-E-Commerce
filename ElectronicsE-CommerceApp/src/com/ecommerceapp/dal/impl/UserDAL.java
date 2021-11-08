/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal.impl;

import com.ecommerceapp.dal.IUserDAL;
import com.ecommerceapp.dto.UserDTO;
import com.ecommerceapp.mapper.impl.UserMapper;
import java.util.List;

/**
 *
 * @author Khoa Nguyen
 */
public class UserDAL extends AbstractDAL<UserDTO> implements IUserDAL{
    
    @Override
    public List<UserDTO> findAll() {
        String sql = "SELECT * FROM user";
        return query(sql, new UserMapper());
    }

    @Override
    public UserDTO findById(Long id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        List<UserDTO> user = query(sql, new UserMapper(), id);
        return user.isEmpty() ? null : user.get(0);
    }
    
    @Override
    public UserDTO findByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        List<UserDTO> user = query(sql, new UserMapper(), email);
        return user.isEmpty() ? null : user.get(0);
    }
    
    @Override
    public Long save(UserDTO user) {
        String sql = "INSERT INTO user(address, dob, email, enabled, first_name, gender, last_name, password, phone) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, user.getAddress(), user.getDobFormat(), user.getEmail(), user.isEnabled(), user.getFirstName(), user.getGender(), user.getLastName(), user.getPassword(), user.getPhone());
    }

    @Override
    public void update(UserDTO user) {
        String sql = "UPDATE user SET address = ?, dob = ?, email = ?, enabled = ?, first_name = ?, gender = ?, last_name = ?, password = ?, phone = ? WHERE id = ?";
        update(sql, user.getAddress(), user.getDobFormat(), user.getEmail(), user.isEnabled(), user.getFirstName(), user.getGender(), user.getLastName(), user.getPassword(), user.getPhone(), user.getId());

    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM user WHERE id = ?";
        update(sql, id);
    }

    
}
