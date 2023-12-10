package com.child.model.response;


import lombok.Data;

import javax.persistence.*;

@Data
public class User {

    private int id;
    private String username;
    private String email;
    private String password;




}
