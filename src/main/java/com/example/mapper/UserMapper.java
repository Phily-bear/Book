package com.example.mapper;

import com.example.entity.AuthUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from users where username = #{username}")
    AuthUser getPasswordByUsername(String username);

    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    @Insert("insert into users(username,password,role) values(#{username},#{password},#{role})")
    int registerUser(AuthUser user);

    @Insert("insert into student(uid,name,sex,grade) values(#{uid},#{name},#{sex},#{grade})")
    int addStudent(@Param("uid") int uid,@Param("name") String name,@Param("sex") String sex,@Param("grade") String grade);

    @Select("select sid from student where uid=#{uid}")
    Integer getSidByUid(int uid);

    @Select("select count(*) from users")
    Integer getUserCount();

}