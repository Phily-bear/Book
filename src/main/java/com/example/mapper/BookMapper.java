package com.example.mapper;

import com.example.entity.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    @Select("select * from book")
    List<Book> getBookList();

    @Delete("delete from book where bid=#{id}")
    void deleteBookById(int id);

    @Insert("insert into book(title,`desc`,price) values(#{title},#{desc},#{price})")
    void addBook(@Param("title") String title,@Param("desc") String desc,@Param("price") double price);
}
