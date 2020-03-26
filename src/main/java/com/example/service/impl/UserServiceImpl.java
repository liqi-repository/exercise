package com.example.service.impl;

import com.example.bean.User;
import com.example.bean.UserExample;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> quertUser() {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIsNotNull();
        List<User> userList = userMapper.selectByExample(userExample);
        return userList;
    }
}
