package com.kml.springbootbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kml.springbootbackend.model.User;
import com.kml.springbootbackend.model.UserMapper;

@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;

    public User getUserById(Long id) {
        if (id > 0L) {
            return userMapper.getById(id);
        }
        return null;
    }

    public boolean updateUser(User user) {
        if (user != null) {
            return userMapper.updateByUser(user) > 0;
        }
        return false;
    }

    public boolean increaseFailedAttempt(User user) {
        if (user != null) {
            user.setFailedAttempt(user.getFailedAttempt() + 1);
            return userMapper.updateByUser(user) > 0;
        }
        return false;
    }
}
