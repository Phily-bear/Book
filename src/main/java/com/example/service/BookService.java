package com.example.service;

import com.example.entity.Book;
import com.example.entity.Borrow;
import com.example.entity.BorrowDetails;

import java.util.List;

public interface BookService {
    List<Book> getBookList();
    List<Book> getBookWithoutBorrow();
    List<Book> getBorrowedBookById(int id);
    void deleteBookById(int id);
    void addBook(String title,String desc,double price);
    void borrowBook(int id,int bid);
    void returnBook(int bid);
    List<BorrowDetails> getBorrowDetails();

}
