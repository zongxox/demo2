package com.example.demo.vo;

import lombok.Data;

//管理員登入回傳資料,用於封裝管理員屬性,在回傳給前端,其他不想回傳的值就不要封裝到這裡面
@Data
public class AdminLoginVO {
    private Integer id;
    private String account;
    private String name;
    private String email;
    private Boolean is_admin;
}