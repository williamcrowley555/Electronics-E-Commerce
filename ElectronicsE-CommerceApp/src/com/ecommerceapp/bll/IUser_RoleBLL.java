/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll;

import com.ecommerceapp.dto.RoleDTO;
import com.ecommerceapp.dto.UserDTO;
import com.ecommerceapp.dto.User_RoleDTO;
import java.util.List;

/**
 *
 * @author Khoa Nguyen
 */
public interface IUser_RoleBLL {
    List<User_RoleDTO> findAll();
    List<UserDTO> findByIdRole(Long idRole);
    List<RoleDTO> findByIdUser(Long idUser);
    User_RoleDTO findById(Long idUser, Long idRole);
    Long save(Long idUser, Long idRole);
    void update(Long userId, Long roleId, Long oldRoleId);
    void delete(Long idRole, Long idUser);    
}
