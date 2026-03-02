package com.crediapp.auth.dto;


import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String dni;
    private String email;
    private String password;
}