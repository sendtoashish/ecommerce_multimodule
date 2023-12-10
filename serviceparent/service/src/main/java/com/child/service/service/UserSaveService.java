package com.child.service.service;


import com.child.model.response.User;
import com.child.service.DAO.UserDAO;
import com.child.service.Utility.PasswordUtils;
import com.child.service.entity.UserEntity;
import com.child.service.exception.ApplicationException;
import com.child.service.exception.ErrorStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserSaveService {

    @Autowired
    private UserDAO userDAO;



    public void saveUser(User user){

        List<UserEntity> listU = userDAO.findAll();
        if(!listU.isEmpty()) {
            UserEntity userCheck = listU.stream().filter(u -> u.getUsername().equals(user.getUsername()))
                    .findFirst().orElse(null);
            if (userCheck != null) {
                throw new ApplicationException(ErrorStatus.DUPLICATE_RECORD, "user already exists");
            }
        }
        String EncryptedPassword = PasswordUtils.encrypt(user.getPassword());
        UserEntity userEntity = UserEntity.builder()
                        .email(user.getEmail()).username(user.getUsername()).password(EncryptedPassword).build();
        userDAO.save(userEntity);
    }

   public UserEntity findUserByEmail(String email){

        return userDAO.findUserWithEmail(email);
   }

   public UserEntity findByUsername(String username){

        return userDAO.findUserWithUsername(username);
   }
}
