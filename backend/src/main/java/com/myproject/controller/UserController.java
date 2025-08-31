package com.myproject.controller;

import com.myproject.entity.User;
import com.myproject.service.UserService;
import com.myproject.utils.JwtUtil;
import com.myproject.result.ResponseResult;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseResult<String> register(@RequestBody User user) {
        userService.register(user);
        return ResponseResult.success("注册成功");
    }

    @PostMapping("/login")
    public ResponseResult<String> login(@RequestBody User user) {
        User dbUser = userService.findByUsername(user.getUsername());
        if (dbUser == null || !DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).equals(dbUser.getPassword())) {
            return ResponseResult.fail("用户名或密码错误");
        }

        String token = jwtUtil.generateToken(dbUser.getId(), dbUser.getUsername());
        return ResponseResult.success(token);
    }

    @PostMapping("/forgot-password")
    public ResponseResult<String> forgotPassword(@RequestParam String username, @RequestParam String newPassword) {
        boolean ok = userService.resetPassword(username, newPassword);
        if (!ok) return ResponseResult.fail("重置失败，用户名不存在");
        return ResponseResult.success("密码重置成功");
    }

    @GetMapping("/{username}")
    public ResponseResult<User> getUser(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user == null) return ResponseResult.fail("用户不存在");
        return ResponseResult.success(user);
    }
}
