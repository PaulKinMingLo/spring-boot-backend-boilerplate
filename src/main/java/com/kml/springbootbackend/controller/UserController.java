package com.kml.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kml.springbootbackend.model.User;
import com.kml.springbootbackend.service.UserService;

@RestController
@RequestMapping("rest/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping(value="/{id}", produces="application/json")
    public ResponseEntity<String> getUser(@PathVariable("id") Long id) {
        if (id <= 0L) {
            return ResponseEntity.ok("Invalid id");
        }
        
        User user = userService.getUserById(id);

        return ResponseEntity.ok(user.toString());
    }
}
