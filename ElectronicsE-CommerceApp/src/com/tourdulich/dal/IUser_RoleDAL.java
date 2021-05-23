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
    List<Long> findByIdUser(Long idUser);
    User_RoleDTO findById(Long idUser, Long idRole);
    Long save(Long idUser, Long idRole);
    void update(Long idUser, Long idRole,Long idUserOld,Long idRoleOld);
    void delete(Long idRole, Long idUser);    
}
