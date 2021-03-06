/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.dal;

import com.ecommerceapp.dto.UserDTO;
import java.util.List;

/**
 *
 * @author Khoa Nguyen
 */
public interface IUserDAL {
    List<UserDTO> findAll();
    UserDTO findById(Long id);
    UserDTO findByEmail(String email);
    Long save(UserDTO user);
    void update(UserDTO user);
    void delete(Long id);      
}
