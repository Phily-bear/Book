package com.example.controller.page;

import com.example.entity.AuthUser;
import com.example.mapper.UserMapper;
import com.example.service.AuthService;
import com.example.service.BookService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page/user")
public class UserPageController {
    @Resource
    UserMapper mapper;

    @Resource
    AuthService service;

    @Resource
    BookService bookService;

    @RequestMapping("/index")
    public String index(HttpSession session, Model model){
        model.addAttribute("user",service.findUser(session));
        model.addAttribute("bookList",bookService.getBookWithoutBorrow());
        return "user/index";
    }

    @RequestMapping("/book")
    public String book(HttpSession session, Model model){
        AuthUser user=service.findUser(session);
        model.addAttribute("user",user);
        model.addAttribute("bookList",bookService.getBorrowedBookById(user.getId()));
        return "user/book";
    }
}
