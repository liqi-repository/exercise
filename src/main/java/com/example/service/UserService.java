package com.example.service;

import com.example.bean.User;

import java.util.List;

public interface UserService {
    List<User> queryUser();

    User queryUserById(int id);
}
