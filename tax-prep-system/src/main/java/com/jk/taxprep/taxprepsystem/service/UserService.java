package com.jk.taxprep.taxprepsystem.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jk.taxprep.taxprepsystem.model.User;
import com.jk.taxprep.taxprepsystem.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isPresent()) {
            User userObj = user.get();
            return org.springframework.security.core.userdetails.User.builder()
                .username(userObj.getEmail())
                .password(userObj.getPassword())
                .roles(getRoles(userObj))
                .build();
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }

    private String[] getRoles(User user) {
        if (user.getRole() == null) {
            return new String[]{"USER"};
        }
        return user.getRole().split(",");
    }

    public void register(User user) {
        // Check if user already exists
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRole("USER");

        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User findOrCreateUser(String email) {
        User user = findByEmail(email);
        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode("password"));
            user.setRole("USER");
            userRepository.save(user);
        }
        return user;
    }

    public Long findUserIdByEmail(String email) {
        User user = findByEmail(email);
        if (user == null) {
            return null;
        }
        return user.getUserId();
    }
}
