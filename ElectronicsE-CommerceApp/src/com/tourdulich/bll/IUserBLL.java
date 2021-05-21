/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.bll;

import com.tourdulich.dto.UserDTO;
import java.util.List;

/**
 *
 * @author Khoa Nguyen
 */
public interface IUserBLL {
    List<UserDTO> findAll();
    UserDTO findById(Long id);
    Long save(UserDTO user);
    void update(UserDTO user);
    void delete(Long id);
}
