package com.example.aiexam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.aiexam.entity.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService extends IService<Category> {

    List<Category> findCategoryList();

    List<Category> findCategoryTree();
} 