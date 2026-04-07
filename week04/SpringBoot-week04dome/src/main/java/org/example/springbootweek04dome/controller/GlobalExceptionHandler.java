package org.example.springbootweek04dome.controller;

import org.example.springbootweek04dome.commom.BusinessException;
import org.example.springbootweek04dome.commom.Result;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.StringJoiner;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理方法参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
       StringJoiner sj = new StringJoiner(";");
      for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
        sj.add(fieldError.getDefaultMessage());
      }
      return Result.error(400, sj.toString());
    }

    /**
     * 处理自定义业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public Result<?> handleBusinessException(BusinessException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 处理通用异常
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        e.printStackTrace();
        return Result.error(500, "服务器内部错误，请稍后重试");
    }
}