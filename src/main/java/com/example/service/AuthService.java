package com.example.service;

import com.example.entity.AuthUser;

import javax.servlet.http.HttpSession;

public interface AuthService {
    boolean register(String username,String sex,String grade,String password);

    AuthUser findUser(HttpSession session);
}
