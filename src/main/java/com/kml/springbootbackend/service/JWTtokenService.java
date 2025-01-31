package com.kml.springbootbackend.service;

import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.kml.springbootbackend.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class JWTtokenService {
    
    private final SecretKey secretKey;
    
    private final String issuer = "kml";

    JWTtokenService() {
        this.secretKey = Jwts.SIG.HS256.key().build();
    }

    // private method that check if the token is signed with the secret key or not
    private JwtParser getValidJwtParserFrom(String token) throws UnsupportedJwtException {
        Claims claims = Jwts.parser().verifyWith(secretKey).build()
            .parseSignedClaims(token).getPayload();

        Instant expDate = claims.get("exp", Date.class).toInstant();

        if (expDate.isBefore(new Date().toInstant()) && claims.get("iss", String.class).equals(issuer)) {
            return Jwts.parser().verifyWith(secretKey).build();
        } else {
            throw new UnsupportedJwtException("Expired token / Invalid token");
        }   
    }

    public String generateToken(User user) throws Exception {
        String jws = Jwts.builder()
            .signWith(secretKey)
            .issuer(issuer)
            .expiration(Date.from(Instant.now().plusSeconds(36000)))
            .issuedAt(new Date())
            .claim("username", user.getUsername())
            .compact();

        return jws;
    }

    public Jws<Claims> getClaimsFromToken(String token) throws JwtException {
        return getValidJwtParserFrom(token).parseSignedClaims(token);
    }

    // return true if token is signed with the secret key and not expired
    public boolean isValid(String token) {
        Claims claims = Jwts.parser().verifyWith(secretKey).build()
        .parseSignedClaims(token).getPayload();

        Instant expDate = claims.get("exp", Date.class).toInstant();

        if (expDate.isBefore(new Date().toInstant()) && claims.get("iss", String.class).equals(issuer)) {
            return true;
        }
        
        return false;
    }
}
