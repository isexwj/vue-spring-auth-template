package com.myproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.myproject.entity.User;
import com.myproject.mapper.UserMapper;
import com.myproject.service.UserService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username));
    }

    @Override
    public void register(User user) {
        userMapper.insert(user);
    }
}
