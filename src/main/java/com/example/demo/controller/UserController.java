package com.example.demo.controller;

import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.response.JsonResult;
import com.example.demo.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//Controller 接收資料前端 ➔ 丟給Service ➔ 傳回前端資料
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    //http://localhost:8080/reg.html
    //註冊
    @PostMapping("/saveUser")
    public JsonResult saveUser(@RequestBody UserRegisterDTO userRegisterDTO){
        return userServiceImpl.saveByUser(userRegisterDTO);
    }

    //登出
    @GetMapping("/logout")
    public JsonResult logout(HttpSession session){
        session.invalidate();//清除session及vo包括sessionId
        return JsonResult.ok();
    }

    //顯示會員中心個人資料
    //從登入狀態session取得封裝的UserLoginVO的session對象,在傳遞到後端userService
    //將獲取到的session,在強轉到UserLoginVO,在傳遞到前端
    //http://localhost:8080/user.html 直接訪問會員中心會被過濾器攔截
    @GetMapping("/userInformation")
    public JsonResult userInformation(HttpSession session){
        return userServiceImpl.userByInformation(session);
    }

    //修改會員中心資料
    @PostMapping("/updateUser")
    public JsonResult updateUser(@RequestBody UserRegisterDTO userRegisterDTO,HttpSession session){
        return userServiceImpl.updateUser(userRegisterDTO,session);
    }

//    //忘記密碼(查詢email)
//    @PostMapping("/selectUserByEmail")
//    public JsonResult selectUserByEmail(@RequestBody UserRegisterDTO userRegisterDTO){
//        return userServiceImpl.selectUserByEmail(userRegisterDTO);
//    }
    //寄出email
    @PostMapping("/sendResetPasswordEmail")
    public JsonResult sendResetPasswordEmail(@RequestBody UserRegisterDTO userRegisterDTO) {
        return userServiceImpl.sendResetPasswordEmail(userRegisterDTO);
    }

    //基於reset_token 去修改密碼 要把token跟時間清空
    @PostMapping("/resetPassword")
    public JsonResult updatePasswordResetToken(@RequestBody UserRegisterDTO userRegisterDTO){
        return userServiceImpl.updatePasswordResetToken(userRegisterDTO);
    }

}
