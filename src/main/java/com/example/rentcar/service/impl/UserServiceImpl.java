package com.example.rentcar.service.impl;


import com.example.rentcar.entity.User;
import com.example.rentcar.repository.UserRepository;
import com.example.rentcar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByUsername(email);
    }
}