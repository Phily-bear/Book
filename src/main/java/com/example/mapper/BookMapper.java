package com.example.mapper;

import com.example.entity.Book;
import com.example.entity.Borrow;
import com.example.entity.BorrowDetails;
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


    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "title", property = "book_title"),
            @Result(column = "name", property = "user_name"),
            @Result(column = "time", property = "time")
    })
    @Select("SELECT * FROM borrow LEFT JOIN book on book.bid = borrow.bid " +
            "LEFT JOIN student ON borrow.sid = student.sid")
    List<BorrowDetails> getBorrowDetailsList();

    @Select("select count(*) from book")
    Integer getBookCount();

    @Select("select count(*) from borrow")
    Integer getBorrowCount();
}
