package com.at.check24.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.Date;

@ControllerAdvice
@ResponseBody
public class ExceptionTranslator {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ServiceExceptionResponse handleValidationExceptions(NotFoundException ex) {
        return ServiceExceptionResponse.builder()
                .timestamp(new Date())
                .details(ex.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ServiceExceptionResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ServiceExceptionResponse.builder()
                .timestamp(new Date())
                .details(ex.getFieldError().getDefaultMessage())
                .build();
    }
}