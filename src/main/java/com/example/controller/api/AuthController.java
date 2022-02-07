package com.example.controller.api;

import com.example.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class AuthController {
    @Resource
    AuthService authService;

    @RequestMapping(value = "/doRegister",method = RequestMethod.POST)
    public String doRegister(@RequestParam("username") String username,
                             @RequestParam("sex") String sex,
                             @RequestParam("grade") String grade,
                             @RequestParam("password") String password){

        authService.register(username, sex, grade, password);
        return "login";
    }
}
