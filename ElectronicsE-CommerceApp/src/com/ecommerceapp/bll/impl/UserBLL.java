/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll.impl;

import com.ecommerceapp.bll.IRoleBLL;
import com.ecommerceapp.bll.IUserBLL;
import com.ecommerceapp.bll.IUser_RoleBLL;
import com.ecommerceapp.dal.IUserDAL;
import com.ecommerceapp.dal.impl.UserDAL;
import com.ecommerceapp.dto.RoleDTO;
import com.ecommerceapp.dto.UserDTO;
import com.ecommerceapp.util.BCrypt;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Khoa Nguyen
 */
public class UserBLL implements IUserBLL{

    private IUserDAL userDAL;
    private IRoleBLL roleBLL;
    private IUser_RoleBLL user_RoleBLL;

    public UserBLL() {
        this.userDAL = new UserDAL();
        this.roleBLL = new RoleBLL();
        this.user_RoleBLL = new User_RoleBLL();
    }
    
    @Override
    public List<UserDTO> findAll() {
        return userDAL.findAll();
    }

    @Override
    public List<UserDTO> findByRoleName(String roleName) {
        RoleDTO role = roleBLL.findByNormalizedName(roleName);
        return user_RoleBLL.findByIdRole(role.getId());
    }

    @Override
    public UserDTO findById(Long id) {
        return userDAL.findById(id);
    } 

    @Override
    public UserDTO findByEmail(String email) {
        return userDAL.findByEmail(email);
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
    public void changePassword(UserDTO user, String newPassword) {
        String encryptedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(10));
        user.setPassword(encryptedPassword);
        update(user);
    }

    @Override
    public void delete(Long id) {
        userDAL.delete(id);
    }    

    @Override
    public void disable(UserDTO user) {
        user.setEnabled(false);
        update(user);
    }

}
