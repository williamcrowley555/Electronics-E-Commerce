/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll.impl;

import com.tourdulich.bll.IUser_RoleBLL;
import com.tourdulich.dal.IUserDAL;
import com.tourdulich.dal.IUser_RoleDAL;
import com.tourdulich.dal.impl.UserDAL;
import com.tourdulich.dal.impl.User_RoleDAL;
import com.tourdulich.dto.UserDTO;
import com.tourdulich.dto.User_RoleDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Khoa Nguyen
 */
public class User_RoleBLL implements IUser_RoleBLL{
    private IUser_RoleDAL user_RoleDAL;
    private IUserDAL userDAL;

    public User_RoleBLL() {
        this.user_RoleDAL = new User_RoleDAL();
        this.userDAL = new UserDAL();
    }

    
    @Override
    public List<User_RoleDTO> findAll() {
       return user_RoleDAL.findAll();
    }

    @Override
    public List<UserDTO> findByIdRole(Long idRole) {
       List<Long> userIds = user_RoleDAL.findByIdRole(idRole);
        List<UserDTO> userList = new ArrayList<>();
        for(Long userId : userIds)
        {
            userList.add(userDAL.findById(userId));
        }
        return userList;
    }

    @Override
    public User_RoleDTO findById(Long idUser, Long idRole) {
       return user_RoleDAL.findById(idUser, idRole);
    }

    @Override
    public Long save(User_RoleDTO user_role) {
       return user_RoleDAL.save(user_role);
    }

    @Override
    public void update(User_RoleDTO user_role) {
        user_RoleDAL.update(user_role);
    }

    @Override
    public void delete(Long idRole, Long idUser) {
        user_RoleDAL.delete(idRole, idUser);
    }
    
}
