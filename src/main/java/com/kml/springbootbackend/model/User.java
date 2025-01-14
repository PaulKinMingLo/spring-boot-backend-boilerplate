package com.kml.springbootbackend.model;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User extends AbstractModel {
    
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String password;
    private Boolean resetFlag;
    private Instant resetDate;
    private int failedAttempt;
}
