package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
//對應資料庫
@Data
public class User {
    private Integer id;
    private String account;
    private String password;
    private String name;
    private String phone;
    private String email;
    private Boolean is_admin;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  // 使用 @JsonFormat 來格式化返回的日期
    private Date created_time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  // 使用 @JsonFormat 來格式化返回的日期
    private Date updated_time;

}
