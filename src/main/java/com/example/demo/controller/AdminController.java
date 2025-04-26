package com.example.demo.controller;

import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.response.JsonResult;
import com.example.demo.response.StatusCode;
import com.example.demo.service.AdminService;
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
    private AdminService adminService;

    //http://localhost:8080/login.html
    //登入
    @PostMapping("/login")
    public JsonResult login(@RequestBody Map<String, String> loginData) {
        String account = loginData.get("account");//接收前端的值
        String password = loginData.get("password");

        //傳遞到adminService的方法做處理,adminService會回傳一個JsonResult的結果,這邊再JsonResult返回給前端
        return adminService.login(account, password);
    }
}
