package com.example.service.impl;

import com.example.entity.Book;
import com.example.mapper.BookMapper;
import com.example.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Resource
    BookMapper mapper;

    @Override
    public List<Book> getBookList() {
        return mapper.getBookList();
    }

    @Override
    public void deleteBookById(int id) {
        mapper.deleteBookById(id);
    }

    @Override
    public void addBook(String title, String desc, double price) {
        mapper.addBook(title, desc, price);
    }
}
