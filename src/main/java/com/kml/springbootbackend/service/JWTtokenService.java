package com.kml.springbootbackend.service;

import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.kml.springbootbackend.model.User;

import io.jsonwebtoken.Jwts;

@Service
public class JWTtokenService {
    
    private final SecretKey secretKey;

    JWTtokenService() {
        this.secretKey = Jwts.SIG.HS256.key().build();
    }

    public String generateToken(User user) throws Exception {
        String jws = Jwts.builder()
            .signWith(secretKey)
            .issuer("kml")
            .expiration(Date.from(Instant.now().plusSeconds(36000)))
            .issuedAt(new Date())
            .claim("username", user.getUsername()).compact();

        return jws;
    }
}
