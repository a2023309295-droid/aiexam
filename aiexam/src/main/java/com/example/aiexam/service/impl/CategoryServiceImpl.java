package com.example.aiexam.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.aiexam.entity.Category;
import com.example.aiexam.mapper.CategoryMapper;
import com.example.aiexam.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {


} 