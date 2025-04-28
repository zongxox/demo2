package com.example.demo.controller;

import com.example.demo.dto.AdminRegisterDTO;
import com.example.demo.dto.LoginDTO;
import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.response.JsonResult;
import com.example.demo.response.StatusCode;
import com.example.demo.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    //登出
    @GetMapping("/logout")
    public JsonResult logout(HttpSession session){
        session.invalidate();////清除session及vo包括sessionId
        return JsonResult.ok();
    }
    //顯示管理中心個人資料
    //http://localhost:8080/admin.html 直接訪問會員中心會被過濾器攔截
    @GetMapping("/adminInformation")
    public JsonResult adminByInformation(HttpSession session){
        return adminService.adminByInformation(session);
    }

    //修改管理中心資料
    @PostMapping("/updateAdmin")
    public JsonResult updateAdmin(@RequestBody AdminRegisterDTO adminRegisterDTO,HttpSession session){
        return adminService.updateAdmin(adminRegisterDTO,session);
    }

}
