package com.jk.taxprep.taxprepsystem.config;

import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import jakarta.servlet.ServletException;

//https://www.youtube.com/watch?v=9J-b6OlPy24&t=886s
// Not really in use right now, but it's a good example
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler { // This overrides the onAuthenticationSuccess method to provide custom logic
    // This is used for post-authentication redirect behavior based on user roles
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin) {
            setDefaultTargetUrl("/admin/home");
        } else {
            setDefaultTargetUrl("/user/home");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
