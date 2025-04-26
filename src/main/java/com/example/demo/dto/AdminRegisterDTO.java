package com.example.demo.dto;

import lombok.Data;

//修改
//用於接收前端送過來的值,AdminRegisterDTO,避免用Admin去封裝
@Data
public class AdminRegisterDTO {
    private String account;
    private String password;
    private String name;
    private String phone;
    private String email;
}