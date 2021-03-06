package com.william.electronics_ecommerce.service;

import com.william.electronics_ecommerce.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    List<User> getAllEnableUser();
    User getUserById(Long id);
    User getUserByEmail(String email);
    User getUserByPhone(String phone);
    User saveUser(User user);
    User saveUserRegistration(User user);
    void deleteUserById(Long id);
}
