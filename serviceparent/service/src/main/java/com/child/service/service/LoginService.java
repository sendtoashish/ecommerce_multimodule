package com.child.service.service;


import com.child.model.response.CredentialPayload;
import com.child.model.response.Tokens;
import com.child.model.response.User;
import com.child.service.DAO.UserDAO;
import com.child.service.Utility.JwtUtils;
import com.child.service.Utility.PasswordUtils;
import com.child.service.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserDAO userDAO;


    public Tokens loginUser(CredentialPayload cp){
        UserEntity user = userDAO.findUserWithUsername(cp.getUsername());
        String payloadPassword = cp.getPassword();
//        String encodedPassword = passwordEncoder.encode(payloadPassword);
        String dbPassword = PasswordUtils.decrypt(user.getPassword());
        if(payloadPassword.equals(dbPassword)) {

            return JwtUtils.generateToken(cp);
        }
        return null;
    }
}
