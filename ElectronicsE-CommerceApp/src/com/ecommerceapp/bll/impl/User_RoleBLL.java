/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll.impl;

import com.ecommerceapp.bll.IUser_RoleBLL;
import com.ecommerceapp.dal.IRoleDAL;
import com.ecommerceapp.dal.IUserDAL;
import com.ecommerceapp.dal.IUser_RoleDAL;
import com.ecommerceapp.dal.impl.RoleDAL;
import com.ecommerceapp.dal.impl.UserDAL;
import com.ecommerceapp.dal.impl.User_RoleDAL;
import com.ecommerceapp.dto.RoleDTO;
import com.ecommerceapp.dto.UserDTO;
import com.ecommerceapp.dto.User_RoleDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Khoa Nguyen
 */
public class User_RoleBLL implements IUser_RoleBLL{
    private IUser_RoleDAL user_RoleDAL;
    private IUserDAL userDAL;
    private IRoleDAL roleDAL;

    public User_RoleBLL() {
        this.user_RoleDAL = new User_RoleDAL();
        this.userDAL = new UserDAL();
        this.roleDAL = new RoleDAL();
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
    public Long save(Long idUser, Long idRole) {
       return user_RoleDAL.save(idUser, idRole);
    }

    @Override
    public void update(Long userId, Long roleId, Long oldRoleId) {
        delete(oldRoleId, userId);
        save(userId, roleId);
    }

    @Override
    public void delete(Long idRole, Long idUser) {
        user_RoleDAL.delete(idRole, idUser);
    }

    @Override
    public List<RoleDTO> findByIdUser(Long idUser) {
        List<Long> roleIds = user_RoleDAL.findByIdUser(idUser);
        List<RoleDTO> roleList = new ArrayList<>();
        for(Long roleId : roleIds)
        {   
            roleList.add(roleDAL.findById(roleId));
        }
        return roleList;
    }
    
}
