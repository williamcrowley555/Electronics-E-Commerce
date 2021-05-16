package com.william.electronics_ecommerce.service.impl;

import com.william.electronics_ecommerce.model.Role;
import com.william.electronics_ecommerce.repository.RoleRepository;
import com.william.electronics_ecommerce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        Role role = null;
        Optional<Role> optional = roleRepository.findById(id);
        if (optional.isPresent()) {
            role = optional.get();
        } else {
            throw new RuntimeException("Role ID: " + id + " does not exist");
        }
        return role;
    }

    @Override
    public Role getRoleByNormalizedName(String normalizedName) {
        return roleRepository.findByNormalizedName(normalizedName);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }
}
