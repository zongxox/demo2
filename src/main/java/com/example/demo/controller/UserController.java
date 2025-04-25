package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    //http://localhost:8080/reg.html
    //註冊
    @PostMapping("/saveUser")
    public String saveUser(@RequestBody User user){
        user.setCreated_time(new Date());//創建時間
        user.setUpdated_time(new Date());//修改時間
        int rows = userMapper.saveUser(user);
        return rows>0?"註冊成功":"註冊失敗";
    }
    //http://localhost:8080/login.html
    //登入
    @PostMapping("/getUserByAccountPassword")
    public Map<String, Object> getUserByAccountPassword(@RequestBody User user){
        //接收前端傳過來的帳號密碼,查詢是否有該帳號
        User userByAccountPassword = userMapper.getUserByAccountPassword(user.getAccount(),user.getPassword());
        Map<String,Object> response  = new HashMap<>();
        if(userByAccountPassword!=null){
            response.put("message","登入成功");
            Map<String,Object> userDate = new HashMap<>();
            userDate.put("id",userByAccountPassword.getId());
            userDate.put("name",userByAccountPassword.getName());
            userDate.put("email",userByAccountPassword.getEmail());
            userDate.put("phone",userByAccountPassword.getPhone());
            response.put("user",userDate);
        }else {
            response.put("message","登入失敗");
        }
        return response;
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestBody User user){
        user.setUpdated_time(new Date());
        int rows = userMapper.updateUser(user);
        return rows>0?"修改成功":"修改失敗";
    }


}
