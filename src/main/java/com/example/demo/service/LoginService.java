package com.example.demo.service;

import com.example.demo.response.JsonResult;
import jakarta.servlet.http.HttpSession;

public interface LoginService {
    JsonResult login(String account, String password, HttpSession session);//登入
}