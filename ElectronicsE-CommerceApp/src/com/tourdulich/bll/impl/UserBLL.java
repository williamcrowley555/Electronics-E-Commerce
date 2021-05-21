/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll.impl;

import com.tourdulich.bll.IUserBLL;
import com.tourdulich.dal.IUserDAL;
import com.tourdulich.dal.impl.UserDAL;
import com.tourdulich.dto.UserDTO;
import java.util.List;

/**
 *
 * @author Khoa Nguyen
 */
public class UserBLL implements IUserBLL{

    private IUserDAL userDAL;

    public UserBLL() {
        this.userDAL = new UserDAL();
    }
    
    @Override
    public List<UserDTO> findAll() {
        return userDAL.findAll();
    }

    @Override
    public UserDTO findById(Long id) {
        return userDAL.findById(id);
    }

    @Override
    public Long save(UserDTO user) {
        return userDAL.save(user);
    }

    @Override
    public void update(UserDTO user) {
        userDAL.update(user);
    }

    @Override
    public void delete(Long id) {
        userDAL.delete(id);
    }    
}
