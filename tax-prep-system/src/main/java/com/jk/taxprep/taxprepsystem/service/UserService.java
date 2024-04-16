package com.jk.taxprep.taxprepsystem.service;

import org.springframework.stereotype.Service;

import com.jk.taxprep.taxprepsystem.model.User;
import com.jk.taxprep.taxprepsystem.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
