package com.jk.taxprep.taxprepsystem.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.jk.taxprep.taxprepsystem.config.JwtTokenProvider;
import com.jk.taxprep.taxprepsystem.dto.LoginRequestDTO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/admin/home")
    public String login() {
        return "admin_home";
    }

    @GetMapping("/user/home")
    public String userHome() {
        return "user_home";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginDto, HttpServletResponse response) {
        try {
            // This is to authenticate the user (by authenticate method in AuthenticationManager)
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken( // This creates a token with the credentials and passes it to the AuthenticationManager.
                    loginDto.getEmail(),
                    loginDto.getPassword()
                )
            );
            // This sets the authentication object in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtTokenProvider.generateToken(authentication);

            // This add the jwt token to the cookie
            Cookie cookie = new Cookie("token", jwt);
            cookie.setHttpOnly(false);
            cookie.setPath("/");
            response.addCookie(cookie);

            return ResponseEntity.ok().body(jwt);

        } catch (AuthenticationException e) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }

    @GetMapping("/logout")
    public void logout(HttpServletResponse response) {

        Cookie cookie = new Cookie("token", null);
        cookie.setHttpOnly(false);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

    }

}

