/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal.impl;

import com.tourdulich.dal.IUserDAL;
import com.tourdulich.dto.UserDTO;
import com.tourdulich.mapper.impl.UserMapper;
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
    public Long save(UserDTO user) {
        String sql = "INSERT INTO user(description, image, name, price, status, brand_id, quantity) VALUES(?, ?, ?, ?, ?, ?, ?)";
      //  return insert(sql, user.getDescription(), user.getImage(), user.getName(), user.getPrice(), user.isStatus(), user.getBrandId(), user.getQuantity());
        return null;
    }

    @Override
    public void update(UserDTO user) {
        String sql = "UPDATE user SET description = ?, image = ?, price = ?, status = ?, brand_id = ? quantity = ? WHERE id = ?";
       // update(sql, user.getDescription(), user.getImage(), user.getPrice(), user.isStatus(), user.getBrandId(), user.getId(), user.getQuantity());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM user WHERE id = ?";
        update(sql, id);
    }
}
