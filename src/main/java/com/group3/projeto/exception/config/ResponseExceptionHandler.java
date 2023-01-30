package com.group3.projeto.exception.config;

import com.group3.projeto.exception.errors.UserExceptionNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(UserExceptionNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handlerError(UserExceptionNotFound e) {//custom exception beinf called and diplaying the message
        return ResponseError.UserNotFoundException(e.getMessage());
    }
}
