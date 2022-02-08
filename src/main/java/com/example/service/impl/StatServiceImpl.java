package com.example.service.impl;

import com.example.entity.GlobalState;
import com.example.mapper.BookMapper;
import com.example.mapper.UserMapper;
import com.example.service.StatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StatServiceImpl implements StatService {
    @Resource
    UserMapper userMapper;

    @Resource
    BookMapper bookMapper;

    @Override
    public GlobalState globalState() {
        return new GlobalState(userMapper.getUserCount(), bookMapper.getBookCount(), bookMapper.getBorrowCount());
    }
}
