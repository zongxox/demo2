package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Admin {
    private Integer id;
    private String account;
    private String password;
    private String name;
    private String email;
    private String phone;
    private Boolean is_admin;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // 使用 @JsonFormat 來格式化返回的日期
    private Date created_time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // 使用 @JsonFormat 來格式化返回的日期
    private Date updated_time;

}
