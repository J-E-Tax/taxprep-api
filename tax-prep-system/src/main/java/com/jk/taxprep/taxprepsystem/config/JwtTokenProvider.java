package com.jk.taxprep.taxprepsystem.config;


import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.jk.taxprep.taxprepsystem.service.UserService;

@Component
public class JwtTokenProvider {
    // This is a key generated from a secret string that is defined in my application.properties file currently.
    private final SecretKey secretKey;
    // This is the expiration time of the JWT in milliseconds.
    private final int jwtExpirationInMs;

    private final UserService userService;

    public JwtTokenProvider(@Value("${security.jwt.secretKey}") String secret,
                            @Value("${security.jwt.expirationInMilliseconds}") int jwtExpirationInMs, UserService userService) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes()); // This is used to convert a string secret into a secret key for cryptographic operations
        this.jwtExpirationInMs = jwtExpirationInMs;
        this.userService = userService;
    }

    public String generateToken(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        String username;

        // If the principal is an instance of UserDetails it gets the username from there. If it's a simple string (Thinking of it as a OAuth2 username), it uses the string directly.
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else if (principal instanceof String) {
            username = (String) principal;
        } else {
            throw new IllegalArgumentException("Principal type is not supported");
        }

        Long userId = userService.findUserIdByEmail(username);

        Date now = new Date();
        // calculator the expiry date of the jwt token
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(username) // Set the sub of the token to the usename
                .claim("userId", userId.toString()) // Add the userId to the token for use at the client side
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey, SignatureAlgorithm.HS256)// Sign the token with the secret key and the SHA-256 algorithm
                .compact(); // This will build and return the compact string represent of the jwt token
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey) // Set the secret key for vlidation the jwt token
                .build()
                .parseClaimsJws(token); // Parse the token and it will throw an exception if it is not a valid token
            return true;
        } catch (Exception e) {
            System.out.println("Invalid or expired JWT token");
        }
        return false;
    }

    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                            .setSigningKey(secretKey)
                            .build()
                            .parseClaimsJws(token)
                            .getBody();

        return claims.getSubject();
    }
}