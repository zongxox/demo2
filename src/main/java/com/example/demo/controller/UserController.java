package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    //http://localhost:8080/reg.html
    @PostMapping("/saveUser")
    public String saveUser(@RequestBody User user){
        user.setIs_member(1);//修改為1 是會員
        user.setCreated_time(new Date());//創建時間
        user.setUpdated_time(new Date());//修改時間
        int rows = userMapper.saveUser(user);
        return rows>0?"註冊成功":"註冊失敗";
    }
    //http://localhost:8080/login.html
    @PostMapping("/getUserByAccountPassword")
    public String getUserByAccountPassword(@RequestBody User user){
        User userByAccountPassword = userMapper.getUserByAccountPassword(user.getAccount(),user.getPassword());
        if(userByAccountPassword!=null){
            return "登入成功";

        }else {
            return "登入失敗";
        }

    }

}
