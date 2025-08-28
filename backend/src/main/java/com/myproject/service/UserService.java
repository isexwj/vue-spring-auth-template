package com.myproject.service;

import com.myproject.entity.User;

public interface UserService {
    User findByUsername(String username);

    void register(User user);
}