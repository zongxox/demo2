package com.example.demo.dto;

import lombok.Data;

//用於接收前端送過來的值,封裝到UserRegisterDTO裡面,避免用User去封裝
@Data
public class UserRegisterDTO {
    private String account;
    private String password;
    private String name;
    private String phone;
    private String email;
}