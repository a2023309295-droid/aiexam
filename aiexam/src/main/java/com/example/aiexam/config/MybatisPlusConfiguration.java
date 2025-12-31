package com.example.aiexam.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.aiexam.mapper")
public class MybatisPlusConfiguration {
}
