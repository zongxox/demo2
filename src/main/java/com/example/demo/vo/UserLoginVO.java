package com.example.demo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

//會員登入回傳資料,用於封裝會員屬性,在回傳給前端,其他不想回傳的值就不要封裝到這裡面
@Data
public class UserLoginVO {
    private Integer id;
    private String account;
    private String name;
    private String email;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  // 使用 @JsonFormat 來格式化返回的日期
    private Date created_time;
    private Boolean is_admin;
}