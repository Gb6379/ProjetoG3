package com.group3.projeto.exception.errors;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(String message){
        super(message);
    }
}
