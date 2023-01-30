package com.group3.projeto.exception.errors;

public class UserExceptionNotFound extends RuntimeException {
    public UserExceptionNotFound(String message){
        super(message);
    }

    //any other exception handling for new entities need to be created in this folder and configurated on config folder
}
