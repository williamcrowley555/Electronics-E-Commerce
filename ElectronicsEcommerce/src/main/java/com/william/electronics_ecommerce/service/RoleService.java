package com.william.electronics_ecommerce.service;

import com.william.electronics_ecommerce.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRole();
    Role getRoleById(Long id);
    Role getRoleByNormalizedName(String normalizedName);
    Role saveRole(Role role);
    void deleteRoleById(Long id);
}
