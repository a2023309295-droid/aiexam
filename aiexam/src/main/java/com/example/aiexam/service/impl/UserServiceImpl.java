package com.example.aiexam.service.impl;

import com.example.aiexam.entity.User;
import com.example.aiexam.mapper.UserMapper;
import com.example.aiexam.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

/**
 * 用户Service实现类
 * 实现用户相关的业务逻辑
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

} 