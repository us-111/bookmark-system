package com.study.bookmark.service;

import com.baomidou.mybatisplus.spring.service.IService;
import com.study.bookmark.pojo.User;

public interface UserService extends IService<User> {
    void register(User user);
    String login(String username,String password);
}
