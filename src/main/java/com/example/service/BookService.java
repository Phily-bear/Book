package com.example.service;

import com.example.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getBookList();
    void deleteBookById(int id);
    void addBook(String title,String desc,double price);
}
