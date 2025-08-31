package com.xieyv.crud.exception;

import com.xieyv.crud.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandle {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<?> handle(Exception e){
        log.error(e.getMessage());
        return Result.fail();
    }
}
