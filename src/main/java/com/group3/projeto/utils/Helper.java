package com.group3.projeto.utils;

import com.group3.projeto.services.JwtService;

public class Helper {

    public String tokenSubString(String token){
        return token.substring(7);
    }
}
