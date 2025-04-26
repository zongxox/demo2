package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.response.JsonResult;
import com.example.demo.response.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    //http://localhost:8080/reg.html
    //註冊
    @PostMapping("/saveUser")
    public JsonResult saveUser(@RequestBody User user){
        //查詢帳號是否重複
        User existingUser = userMapper.getUserByAccount(user.getAccount());
        if (existingUser != null) {//如果重複
            return new JsonResult(StatusCode.ACCOUNT_ALREADY_EXISTS);
        }
        user.setCreated_time(new Date());//創建時間
        user.setUpdated_time(new Date());//修改時間
        user.setIs_admin(false);//是否是管理員 0=false
        //不重複就,新增
        int rows = userMapper.saveUser(user);
        if(rows>0){
            return JsonResult.ok();
        }else {
            return new JsonResult(StatusCode.OPERATION_FAILED);//操作失敗
        }
    }

//    @PostMapping("/updateUser")
//    public String updateUser(@RequestBody User user){
//        user.setUpdated_time(new Date());
//        int rows = userMapper.updateUser(user);
//        return rows>0?"修改成功":"修改失敗";
//    }


}
