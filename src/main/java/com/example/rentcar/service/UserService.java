package com.example.rentcar.service;

import com.example.rentcar.entity.User;

import java.util.Optional;

public interface UserService {

    void save(User user);

    Optional<User> findByEmail(String email);
}