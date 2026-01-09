package com.example.aiexam.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.aiexam.entity.Category;
import com.example.aiexam.mapper.CategoryMapper;
import com.example.aiexam.mapper.QuestionMapper;
import com.example.aiexam.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private final QuestionMapper questionMapper;

    public CategoryServiceImpl(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }
    @Override
    public List<Category> findCategoryList() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSort);
        queryWrapper.eq(Category::getIsDeleted, 0);
        List<Category> categoryList = list(queryWrapper);
        List<Map<Long, Long>> categoryQuestionCount = questionMapper.selectCategoryQuestionCount();
        Map<Long,Long> countMap = categoryQuestionCount.stream()
                .collect(Collectors.toMap(map ->map.get("categoryId"), map -> map.get("count")));
        for (Category category : categoryList) {
            Long id = category.getId();
            category.setCount(countMap.getOrDefault(id,0L));
        }
        return categoryList;
    }

    @Override
    public List<Category> findCategoryTree() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSort);
        queryWrapper.eq(Category::getIsDeleted, 0);
        List<Category> categoryList = list(queryWrapper);
        List<Map<Long,Long>> categoryQuestionCount = questionMapper.selectCategoryQuestionCount();
        Map<Long,Long> countMap = categoryQuestionCount.stream().collect(Collectors.toMap(map ->map.get("categoryId"),map ->map.get("count")));
        for(Category category: categoryList){
            Long id = category.getId();
            category.setCount(countMap.getOrDefault(id,0L));
        }
        Map<Long,List<Category>> longlistMap = categoryList.stream().collect(Collectors.groupingBy(Category::getParentId));
        List<Category> parentCategories = categoryList.stream().filter(category -> category.getParentId() == 0).toList();
        for(Category category: parentCategories){
            List<Category> children = longlistMap.getOrDefault(category.getId(),new ArrayList<>());
            category.setChildren(children);
            Long sumlong = children.stream().mapToLong(Category::getCount).sum();
            category.setCount(category.getCount()+sumlong);
        }
        return parentCategories;
    }
}