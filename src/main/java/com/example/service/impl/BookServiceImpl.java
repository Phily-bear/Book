package com.example.service.impl;

import com.example.entity.Book;
import com.example.entity.Borrow;
import com.example.mapper.BookMapper;
import com.example.mapper.UserMapper;
import com.example.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Resource
    BookMapper mapper;

    @Resource
    UserMapper userMapper;

    @Override
    public List<Book> getBookList() {
        return mapper.getBookList();
    }

    @Override
    public List<Book> getBookWithoutBorrow() {
        List<Book> books = mapper.getBookList();
        List<Integer> bids = mapper.getBorrowList().stream()
                .map(Borrow::getBid)
                .collect(Collectors.toList());

        return books.stream().filter(book -> !bids.contains(book.getBid())).collect(Collectors.toList());
    }

    @Override
    public List<Book> getBorrowedBookById(int id) {
        Integer sid = userMapper.getSidByUid(id);
        if (sid==null)   return Collections.emptyList();
        return mapper.getBorrowListBySid(sid)
                .stream()
                .map(borrow -> mapper.getBookByBid(borrow.getBid()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBookById(int id) {
        mapper.deleteBookById(id);
    }

    @Override
    public void addBook(String title, String desc, double price) {
        mapper.addBook(title, desc, price);
    }

    @Override
    public void borrowBook(int id, int bid) {
        Integer sid = userMapper.getSidByUid(id);
        if (sid==null)   return ;
        mapper.borrowBook(sid, bid);
    }

    @Override
    public void returnBook(int bid) {
        mapper.returnBook(bid);
    }


}
