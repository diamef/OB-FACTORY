package com.attijariwafabank.obfactory.model.dto;

import javax.persistence.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CreateUserRequest {
    private Long id;

    private String email;

    private String firstname;

    private String lastname;

    private String password;

    
}
