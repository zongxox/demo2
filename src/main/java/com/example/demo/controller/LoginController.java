package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.response.JsonResult;
import com.example.demo.service.impl.LoginServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginServiceImpl loginServiceImpl;

    //http://localhost:8080/login.html
    //登入
    @PostMapping("/login")
    public JsonResult login(@RequestBody LoginDTO loginDTO, HttpSession session) {
        //把接收到的值帳號密碼封裝到loginDTO
        //在傳遞到loginService的方法做處理,loginService會回傳一個JsonResult的結果,這邊再JsonResult返回給前端
        //後端自動獲取session並傳給service做保存
        return loginServiceImpl.login(loginDTO.getAccount(),loginDTO.getPassword(),session);
    }


}
