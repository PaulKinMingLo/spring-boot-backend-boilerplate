package com.kml.springbootbackend.security;

import java.io.IOException;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kml.springbootbackend.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwe;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Get authorization header and validate
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || header.isEmpty() || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            System.out.println("Empty header");
            return;
        }

        // Get jwt token and validate
        final String token = header.split(" ")[1].trim();
        // TODO: validate the jwt token, if not then doFilter
        // Jwe<Claims> jwe = Jwts.parser().build().parse(token).accept(Jwe.CLAIMS);
        // String username = (String)jwe.getPayload().get("username");
        // System.out.println(username);
        filterChain.doFilter(request, response);
        return;

        // Get user identity and set it on the spring security context
        // TODO: get the username from token and find user by username

        // TODO: 

        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'doFilterInternal'");
    }
    
}
