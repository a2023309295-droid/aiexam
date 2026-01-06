package com.example.aiexam.exceptionHandlers;

import com.example.aiexam.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlers {

    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        e.printStackTrace();
        log.error("出现异常：{}", e.getMessage());
        return Result.error(e.getMessage());
    }
}
