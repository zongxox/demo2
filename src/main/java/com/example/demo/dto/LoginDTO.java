package com.example.demo.dto;

import lombok.Data;

//專門用來接收前端傳遞過來的登入資料
@Data
public class LoginDTO {
    private String account;
    private String password;
}