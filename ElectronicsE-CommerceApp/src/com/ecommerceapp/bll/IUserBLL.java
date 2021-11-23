/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll;

import com.ecommerceapp.dto.UserDTO;
import java.util.List;

/**
 *
 * @author Khoa Nguyen
 */
public interface IUserBLL {
    List<UserDTO> findAll();
    List<UserDTO> findByRoleName(String roleName);
    UserDTO findById(Long id);
    UserDTO findByEmail(String email);
    Long save(UserDTO user);
    void update(UserDTO user);
    void changePassword(UserDTO user, String newPassword);
    void disable(UserDTO user);
    void delete(Long id);
}
