package com.jk.taxprep.taxprepsystem.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jk.taxprep.taxprepsystem.model.User;
import com.jk.taxprep.taxprepsystem.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user-private-info")
    public String userPrivateInfo(Principal principal, Authentication authentication) {
        return "This is private info for user: " + principal.getName();
    }

    // @PostMapping("/register")
    // public User createUser(@RequestBody User user) {
    //     user.setPassword(passwordEncoder.encode(user.getPassword()));
    //     return userRepository.save(user);
    // }

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody User user) {

        userService.register(user);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }


}
