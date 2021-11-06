/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerceapp.bll;

import com.ecommerceapp.dto.RoleDTO;
import java.util.List;

/**
 *
 * @author Khoa Nguyen
 */
public interface IRoleBLL {
    List<RoleDTO> findAll();
    RoleDTO findById(Long id);
    Long save(RoleDTO role);
    void update(RoleDTO role);
    void delete(Long id);     
}
