package com.william.electronics_ecommerce.repository;

import com.william.electronics_ecommerce.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByNormalizedName(String normalizedName);
}
