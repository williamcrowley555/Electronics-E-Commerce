/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal;

import com.tourdulich.dto.User_RoleDTO;
import java.util.List;

/**
 *
 * @author Khoa Nguyen
 */
public interface IUser_RoleDAL extends GenericDAL<User_RoleDTO>{
    List<User_RoleDTO> findAll();
    List<Long> findByIdRole(Long idRole);
    
    User_RoleDTO findById(Long idUser, Long idRole);
    Long save(User_RoleDTO user_role);
    void update(User_RoleDTO user_role);
    void delete(Long idRole, Long idUser);    
}
