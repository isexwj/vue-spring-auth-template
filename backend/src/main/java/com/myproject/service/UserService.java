package com.myproject.service;

import com.myproject.entity.User;

public interface UserService {
    void register(User user);
    boolean resetPassword(String username, String newPassword);
    User findByUsername(String username);
}