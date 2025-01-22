package com.kml.springbootbackend.controller;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kml.springbootbackend.model.User;
import com.kml.springbootbackend.service.JWTtokenService;
import com.kml.springbootbackend.service.UserService;

@RestController
@RequestMapping(path = "api/public")
public class AuthController {
    
    // private final AuthenticationManager authManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JWTtokenService tokenService;

    @PostMapping("login")
    public ResponseEntity<Object> login(@RequestParam("username") final String username
        , @RequestParam("password") final String password
        , @RequestParam("deviceId") final String deviceId
        , @RequestParam("ipAddress") final String ipAddress) {

        Optional<User> userEntity = userService.findByUsername(username);
        
        if (userEntity.isPresent()) {
            // do jwt authentication
            if (password.equals(userEntity.get().getPassword())) {
                try {
                    String token = tokenService.generateToken(userEntity.get());
                    return ResponseEntity.ok(Collections.singletonMap("token", token));
                } catch (Exception e) {
                    return ResponseEntity.internalServerError().build();
                }
            }
        }

        // return ResponseEntity.badRequest().body(null).notFound().build();
        return ResponseEntity.notFound().build();
    }

    
}
