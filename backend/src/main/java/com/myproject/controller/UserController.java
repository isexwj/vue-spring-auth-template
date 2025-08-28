package com.myproject.controller;

import com.myproject.result.ResponseResult;
import com.myproject.entity.User;
import com.myproject.service.UserService;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    // 测试注册
    @PostMapping("/register")
    public ResponseResult<String> register(@RequestBody User user) {
        userService.register(user);
        return ResponseResult.success("注册成功");
    }

    // 测试查询
    @GetMapping("/{username}")
    public ResponseResult<User> getUser(@PathVariable String username) {
        User user = userService.findByUsername(username);
        return ResponseResult.success(user);
    }
}

