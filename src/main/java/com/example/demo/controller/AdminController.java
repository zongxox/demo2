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

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminMapper adminMapper;



    //http://localhost:8080/login.html
    //登入
    @PostMapping("/getAdminByAccountPassword")
    public JsonResult getAdminByAccountPassword(@RequestBody Admin admin){
        //接收前端傳過來的帳號密碼,查詢是否有該帳號
        Admin adminByAccountPassword = adminMapper.getAdminByAccountPassword(admin.getAccount(), admin.getPassword(), admin.is_admin());
        System.out.println(adminByAccountPassword);
        if(adminByAccountPassword!=null){//判斷如果數據庫有該帳號及密碼
            return JsonResult.ok(adminByAccountPassword);//成功
        }else {//沒有則失敗
            return new JsonResult(StatusCode.ACCOUNT_PASSWORD_ERROR);//帳號密碼錯誤
        }
    }

}
