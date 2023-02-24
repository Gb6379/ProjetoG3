package com.group3.projeto.exception.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseError {
    private String error;
    public static ResponseError NotFoundException(String msg){//message to be displayed on error
        ResponseError response =  new ResponseError();
        response.error = msg;
        return response;
    }

}
