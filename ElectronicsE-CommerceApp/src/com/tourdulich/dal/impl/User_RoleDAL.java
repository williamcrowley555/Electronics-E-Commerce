/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.dal.impl;

import com.tourdulich.dal.IUser_RoleDAL;
import com.tourdulich.dto.User_RoleDTO;
import com.tourdulich.mapper.impl.IdRoleMapper;
import com.tourdulich.mapper.impl.IdUserMapper;
import com.tourdulich.mapper.impl.User_RoleMapper;
import java.util.List;

/**
 *
 * @author Khoa Nguyen
 */
public class User_RoleDAL extends AbstractDAL<User_RoleDTO> implements IUser_RoleDAL{

    @Override
    public List<User_RoleDTO> findAll() {
      String sql = "SELECT * FROM user_role";
        return query(sql, new User_RoleMapper());
    }

    @Override
    public List<Long> findByIdRole(Long idRole) {
         String sql = "SELECT * FROM user_role WHERE role_id = ?";
        return query(sql, new IdUserMapper(), idRole);
    }

    @Override
    public User_RoleDTO findById(Long idUser, Long idRole) {
         String sql = "SELECT * FROM user_role WHERE user_id = ? AND role_id = ?";
        List<User_RoleDTO> user_Role = query(sql, new User_RoleMapper(), idUser, idRole);
        return user_Role.isEmpty() ? null : user_Role.get(0);
    }

    @Override
    public Long save(Long idUser, Long idRole) {
         String sql = "INSERT INTO user_role(user_id, role_id) VALUES(?, ?)";
       return insert(sql, idUser, idRole);
    }

    @Override
    public void update(Long idUser, Long idRole,Long idUserOld,Long idRoleOld) {
       String sql = "UPDATE user_role SET user_id = ?, role_id = ? WHERE idUserOld = ? AND idRoleOld";
       update(sql, idUser, idRole,idUserOld, idRoleOld);
    }

    @Override
    public void delete(Long idRole, Long idUser) {
        String sql = "DELETE FROM user_role WHERE role_id = ? AND user_id = ?";
        update(sql, idRole, idUser);
    }

    @Override
    public List<Long> findByIdUser(Long idUser) {
        String sql = "SELECT * FROM user_role WHERE user_id = ?";
        return query(sql, new IdRoleMapper(), idUser);
    }
    
}
