package com.example.entity;

import lombok.Data;

@Data
public class AuthUser {
    int id;
    String username;
    String password;
    String role;
}