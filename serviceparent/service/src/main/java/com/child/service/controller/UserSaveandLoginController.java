package com.child.service.controller;

import com.child.model.response.CredentialPayload;
import com.child.model.response.Tokens;
import com.child.model.response.User;
import com.child.service.entity.UserEntity;
import com.child.service.service.LoginService;
import com.child.service.service.UserSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/user")
public class UserSaveandLoginController {

    @Autowired
    private UserSaveService userSaveService;
    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/saveuser")
    public ResponseEntity<String> login(@RequestBody User user) {
        userSaveService.saveUser(user);
        return ResponseEntity.ok().build();

    }

    @GetMapping(value = "/finduser")
    public UserEntity findUser(@RequestParam String email) {
        return userSaveService.findUserByEmail(email);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Tokens> login(@RequestBody CredentialPayload cp) {
        Tokens token = loginService.loginUser(cp);
        return ResponseEntity.ok().body(token);
    }

}



