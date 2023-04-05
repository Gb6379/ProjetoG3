package com.group3.projeto.exception.errors;

public class ProductExceptionNotFound extends RuntimeException{

    public ProductExceptionNotFound(String message){
        super(message);
    }
}
