package com.study.bookmark.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.study.bookmark.exception.BusinessException;
import com.study.bookmark.mapper.UserMapper;
import com.study.bookmark.pojo.User;
import com.study.bookmark.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public void register(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,user.getUsername());
        if (this.count(wrapper)>0) throw new BusinessException("exists");
        String encode = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        user.setDeleted(0);
        this.save(user);
    }

    @Override
    public String login(String username, String password) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        User user = getOne(wrapper);
        if (user == null) throw new BusinessException("not exists");
        if (!bCryptPasswordEncoder.matches(password,user.getPassword())) throw new BusinessException("error");
        return jwtUtils.generateToken(user.getId(),username);
    }
}
