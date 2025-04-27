package com.example.demo.controller;

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
        session.removeAttribute("sessionAdmin");//清除sessionAdmin
        return JsonResult.ok();
    }

}
