package com.study.bookmark.exception;

import com.study.bookmark.common.Result;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Result<String> hanBusiness(BusinessException e){
        return Result.error(400,e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handNotValid(MethodArgumentNotValidException e){
        FieldError error = e.getBindingResult().getFieldError();
        String message = "参数校验错误";
        if (error != null) message = error.getDefaultMessage();
        return Result.error(400,message);
    }


    @ExceptionHandler(Exception.class)
    public Result<String> handException(Exception e){
        e.printStackTrace();
        return Result.error(500,e.getMessage());
    }
}
