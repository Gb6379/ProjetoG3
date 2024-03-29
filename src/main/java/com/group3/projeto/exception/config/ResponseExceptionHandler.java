package com.group3.projeto.exception.config;

import com.group3.projeto.exception.errors.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(UserExceptionNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handlerError(UserExceptionNotFound e) {//custom exception beinf called and diplaying the message
        return ResponseError.NotFoundException(e.getMessage());
    }

    @ExceptionHandler(AddressExceptionNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handleError(AddressExceptionNotFound e){
        return ResponseError.NotFoundException(e.getMessage());
    }

    @ExceptionHandler(CategoryExceptionNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handleError(CategoryExceptionNotFound e){
        return ResponseError.NotFoundException(e.getMessage());
    }

    @ExceptionHandler(CartNotFoundExcption.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handleError(CartNotFoundExcption e){
        return ResponseError.NotFoundException(e.getMessage());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handleError(OrderNotFoundException e){
        return ResponseError.NotFoundException(e.getMessage());
    }

    @ExceptionHandler(ProductExceptionNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handleError(ProductExceptionNotFound e){
        return ResponseError.NotFoundException(e.getMessage());
    }
}
