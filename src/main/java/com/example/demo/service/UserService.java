package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.response.JsonResult;
import com.example.demo.response.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//Service 做判斷、做查詢、做邏輯
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    //註冊
    public JsonResult saveByUser(User user){
        //查詢帳號是否重複
        User existingUser = userMapper.getUserByAccount(user.getAccount());
        if (existingUser != null) {//如果帳號重複
            return new JsonResult(StatusCode.ACCOUNT_ALREADY_EXISTS);
        }
        user.setIs_admin(false);//是否是管理員 0=false
        int rows = userMapper.saveUser(user);
        //執行新增
        if (rows > 0) {
            return JsonResult.ok();  // 保存成功，返回成功的 JsonResult
        } else {
            return new JsonResult(StatusCode.OPERATION_FAILED);  // 保存失敗，返回操作失敗的錯誤信息
        }
    }

    //查詢Email
    public User selectByEmail(String email) {
        return userMapper.selectUserByEmail(email);
    }


}
