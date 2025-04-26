package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.response.JsonResult;
import com.example.demo.response.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AdminMapper adminMapper;

    public JsonResult login(String account, String password) {//接收Controller傳遞過來的參數
        // 查管理員
        Admin admin = adminMapper.getAdminByAccountPassword(account, password);//將參數admin拿去數據庫做查詢
        if (admin != null) {//查詢出來是空值就是false 是true就執行
            admin.setPassword(null); //密碼不要回傳
            return JsonResult.ok(admin);
        }

        // 查普通用戶
        User user = userMapper.getUserByAccountPassword(account, password);//將參數拿去user數據庫做查詢
        if (user != null) {//查詢出來是空值就是false 是true就執行
            user.setPassword(null);//密碼不要回傳
            return JsonResult.ok(user);
        }

        //兩者都查詢不到返回帳號密碼錯誤
        return new JsonResult(StatusCode.ACCOUNT_PASSWORD_ERROR);
    }
}
