package com.study.bookmark.controller;

import com.study.bookmark.common.Result;
import com.study.bookmark.pojo.User;
import com.study.bookmark.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public Result<Void> register(@Valid @RequestBody User user){
        userService.register(user);
        return Result.success();
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody User user){
        return Result.success(userService.login(user.getUsername(),user.getPassword()));
    }
}
