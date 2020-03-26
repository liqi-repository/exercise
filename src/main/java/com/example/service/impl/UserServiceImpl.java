package com.example.service.impl;

import com.example.bean.User;
import com.example.bean.UserExample;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    private JdbcTemplate jdbcTemplate;

    public UserServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> queryUser() {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIsNotNull();
        List<User> userList = userMapper.selectByExample(userExample);
        return userList;
    }

    @Override
    public User queryUserById(int id) {
        User user = new User();
        String sql = "select * from user where id = ?";
        jdbcTemplate.query(sql, new ResultSetExtractor<User>() {
            @Override
            public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                while(resultSet.next()){
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                }
                return user;
            }
        },id);
        return user;
    }
}
