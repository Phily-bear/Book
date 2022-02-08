package com.example.controller.api;

import com.example.entity.AuthUser;
import com.example.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;

@Controller
@RequestMapping("/api/user")
public class UserApiController {

    @Resource
    BookService service;

    @RequestMapping(value = "/borrow-book",method = RequestMethod.GET)
    public String borrowBook(@RequestParam("id") int id,
                             @SessionAttribute("user")AuthUser user){
        service.borrowBook(user.getId(), id);
        return "redirect:/page/user/book";
    }

    @RequestMapping(value = "/return-book",method = RequestMethod.GET)
    public String returnBook(@RequestParam("id") int bid){
        service.returnBook(bid);
        return "redirect:/page/user/index";
    }
}
