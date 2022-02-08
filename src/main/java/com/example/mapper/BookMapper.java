package com.example.mapper;

import com.example.entity.Book;
import com.example.entity.Borrow;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    @Select("select * from book")
    List<Book> getBookList();

    @Select("select * from book where bid=#{bid}")
    Book getBookByBid(int bid);

    @Delete("delete from book where bid=#{id}")
    void deleteBookById(int id);

    @Insert("insert into book(title,`desc`,price) values(#{title},#{desc},#{price})")
    void addBook(@Param("title") String title,@Param("desc") String desc,@Param("price") double price);

    @Insert("insert into borrow(sid,bid,`time`) values(#{sid},#{bid},NOW())")
    void borrowBook(@Param("sid") int sid,@Param("bid") int bid);

    @Select("select * from borrow")
    List<Borrow> getBorrowList();

    @Select("select * from borrow where sid=#{sid}")
    List<Borrow> getBorrowListBySid(int sid);

    @Delete("delete from borrow where bid=#{bid}")
    void returnBook(int bid);
}
