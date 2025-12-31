package com.example.aiexam.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfiguration {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().
               info(new Info().title("智能考试系统API文档")
                       .description("智能考试系统后端接口文档\n"+"主要功能模块: \n"+
                               "题目管理\n"+
                               "试卷管理\n"+
                               "轮播图管理\n"+
                               "考试记录\n"+
                               "公告管理")
                       .version("v1.0.0"));
    }

    @Bean
    public GroupedOpenApi userAPI(){

        return GroupedOpenApi.builder().group("用户信息管理").
               pathsToMatch(
                       "/api/user/**"
               ).build();
    }

    @Bean
    public GroupedOpenApi questionAPI(){

        return GroupedOpenApi.builder().group("试卷信息管理").
               pathsToMatch(
                       "/api/categories/**",
                       "/api/questions/**"
               ).build();
    }

    @Bean
    public GroupedOpenApi papersAPI(){

        return GroupedOpenApi.builder().group("考试信息管理").
               pathsToMatch(
                       "/api/papers/**",
                       "/api/exams/**",
                       "/api/exam-records"
               ).build();
    }

    @Bean
    public GroupedOpenApi videosAPI(){

        return GroupedOpenApi.builder().group("视频信息管理").
               pathsToMatch(
                       "/api/admin/videos/**",
                       "/api/videos/**",
                       "/api/video-categories/**"
               ).build();
    }

    @Bean
    public GroupedOpenApi systemAPI(){

        return GroupedOpenApi.builder().group("系统信息管理").
               pathsToMatch(
                       "/api/banners/**",
                       "/api/notices/**"
               ).build();
    }

    @Bean
    public GroupedOpenApi otherAPI(){

        return GroupedOpenApi.builder().group("其他内容管理").
               pathsToMatch(
                       "/api/status/**",
                       "/files/**",
                       "/api/debug/**"
               ).build();
    }
}
