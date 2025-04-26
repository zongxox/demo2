package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.response.JsonResult;
import com.example.demo.response.StatusCode;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//Controller 接收資料前端 ➔ 丟給Service ➔ 傳回前端資料
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //http://localhost:8080/reg.html
    //註冊
    @PostMapping("/saveUser")
    public JsonResult saveUser(@RequestBody User user){
        return userService.saveByUser(user);
    }


    //查詢Email
    @PostMapping("/selectUserByEmail")
    public JsonResult selectUserByEmail(@RequestBody User user){
        User userEmail = userService.selectByEmail(user.getEmail());
        if(userEmail != null){
            return JsonResult.ok(userEmail);
        }
        return new JsonResult(StatusCode.OPERATION_FAILED);
    }

//    @PostMapping("/updateUser")
//    public String updateUser(@RequestBody User user){
//        user.setUpdated_time(new Date());
//        int rows = userMapper.updateUser(user);
//        return rows>0?"修改成功":"修改失敗";
//    }


}
