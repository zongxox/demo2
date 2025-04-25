package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.response.JsonResult;
import com.example.demo.response.StatusCode;
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
    public JsonResult saveUser(@RequestBody User user){
        user.setCreated_time(new Date());//創建時間
        user.setUpdated_time(new Date());//修改時間

        //查詢帳號是否重複
        User existingUser = userMapper.getUserByAccount(user.getAccount());
        if (existingUser != null) {//如果重複
            return new JsonResult(StatusCode.ACCOUNT_ALREADY_EXISTS);
        }
        //不重複就,新增
        int rows = userMapper.saveUser(user);
        if(rows>0){
            return JsonResult.ok();
        }else {
            return new JsonResult(StatusCode.OPERATION_FAILED);//操作失敗
        }
    }

    //http://localhost:8080/login.html
    //登入
    @PostMapping("/getUserByAccountPassword")
    public JsonResult getUserByAccountPassword(@RequestBody User user){
        //接收前端傳過來的帳號密碼,查詢是否有該帳號
        User userByAccountPassword = userMapper.getUserByAccountPassword(user.getAccount(),user.getPassword());
        System.out.println(userByAccountPassword);
        if(userByAccountPassword!=null){//判斷如果數據庫有該帳號及密碼
            return JsonResult.ok();//成功
        }else {//沒有則失敗
            return new JsonResult(StatusCode.ACCOUNT_PASSWORD_ERROR);//帳號密碼錯誤
        }
    }

//    @PostMapping("/updateUser")
//    public String updateUser(@RequestBody User user){
//        user.setUpdated_time(new Date());
//        int rows = userMapper.updateUser(user);
//        return rows>0?"修改成功":"修改失敗";
//    }


}
