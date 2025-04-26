package com.example.demo.controller;

import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.response.JsonResult;
import com.example.demo.response.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserMapper userMapper;

    //http://localhost:8080/login.html
    //登入
    @PostMapping("/login")
    public JsonResult login(@RequestBody Map<String, String> loginData) {
        String account = loginData.get("account");//前端傳遞過來的值
        String password = loginData.get("password");

        // 先查詢管理員
        Admin admin = adminMapper.getAdminByAccountPassword(account, password);
        if (admin != null) {//帳號,密碼,是否是管理員(1) true 代表不等於空值
            // 管理員登入成功
            admin.setPassword(null);//不回傳查到的密碼,用空值回傳給前端
            return JsonResult.ok(admin); // 返回成功訊息
        }

        // 查詢普通用戶
        User user = userMapper.getUserByAccountPassword(account, password);
        if (user != null) {
            // 普通用戶登入成功
            user.setPassword(null);//不回傳查到的密碼,用空值回傳給前端
            return JsonResult.ok(user); // 返回成功訊息
        }

        // 查無此用戶，返回錯誤訊息
        return new JsonResult(StatusCode.ACCOUNT_PASSWORD_ERROR);//帳號密碼錯誤
    }
}
