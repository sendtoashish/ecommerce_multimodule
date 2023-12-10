package com.child.client.impl;

import com.child.client.utility.JsonUtility;
import com.child.model.response.CredentialPayload;
import com.child.model.response.Tokens;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;



public class TokenAuthenticationClient implements com.child.client.api.TokenAuthenticationClient {

    @Override
    public Tokens getToken(String username ,String password) {

        Map<String,Object> param = new HashMap<>();
            param.put("username",username);
            param.put("password",password);
        JSONObject cp = new JSONObject(param);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(cp, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> token =restTemplate.postForEntity("http://localhost:8081/user/login",requestEntity,String.class);
        String tokenBody = token.getBody();
        Tokens userToken =JsonUtility.convertJsonToObject(tokenBody,Tokens.class);
        return userToken;


    }


}
