package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
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
    private Boolean is_admin; //一般會員=0 管理員=1
    private String reset_token; // 忘記密碼驗證 token

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reset_token_expire; // 忘記密碼驗證 token 的過期時間

    private String email_verify_token; //email註冊驗證用 token

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime email_verify_token_expire; //email驗證 token 過期時間

    private Boolean email_verified; //email是否已驗證 0=未驗證, 1=已驗證

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // 使用 @JsonFormat 來格式化返回的日期
    private LocalDateTime created_time;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // 使用 @JsonFormat 來格式化返回的日期
    private LocalDateTime updated_time;


}
