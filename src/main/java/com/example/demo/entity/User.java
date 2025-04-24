package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String account;
    private String password;
    private String name;
    private String phone;
    private String email;
    private Integer is_member;
    private Date created_time;
    private Date updated_time;

}
