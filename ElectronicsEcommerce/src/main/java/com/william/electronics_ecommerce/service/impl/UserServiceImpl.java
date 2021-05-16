package com.william.electronics_ecommerce.service.impl;

import com.william.electronics_ecommerce.model.Role;
import com.william.electronics_ecommerce.model.User;
import com.william.electronics_ecommerce.repository.UserRepository;
import com.william.electronics_ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllEnableUser() {
        return userRepository.findAll().stream().filter(item -> item.isEnabled()).collect(Collectors.toList());
    }

    @Override
    public User getUserById(Long id) {
        User user = null;
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException("User ID: " + id + " does not exist");
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        Optional<User> optional = userRepository.getUserByEmail(email);

        if (optional.isPresent()) {
            user = optional.get();
        }

        return user;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User saveUserRegistration(User user) {
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRoles(Set.of(new Role(1L)));
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = null;
        Optional<User> optional = userRepository.findById(id);

        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException("User ID: " + id + " does not exist");
        }

        user.setEnabled(false);
        userRepository.save(user);
    }
}
