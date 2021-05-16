package com.william.electronics_ecommerce.repository;

import com.william.electronics_ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> getUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.id NOT IN (:ids)")
    List<User> findAllNotIn(@Param("ids") List<Long> ids);
}
