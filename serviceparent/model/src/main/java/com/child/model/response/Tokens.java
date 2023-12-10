package com.child.model.response;


import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Tokens {


    private String token_type = JWTConstant.JWT.name();

    private String access_token;

    public Tokens(String access_token) {
        this.access_token = access_token;
    }
}
