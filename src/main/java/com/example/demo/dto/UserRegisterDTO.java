package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

//註冊或修改
// 用於接收前端送過來的值,封裝到UserRegisterDTO裡面,避免用User去封裝
@Data
public class UserRegisterDTO {
    private Integer id;
    private String account;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String reset_token; // 忘記密碼驗證 token
    private String email_verify_token; //註冊驗證用 token
}