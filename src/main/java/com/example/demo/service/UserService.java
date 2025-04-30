package com.example.demo.service;

import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.response.JsonResult;
import jakarta.servlet.http.HttpSession;

public interface UserService {
    JsonResult saveByUser(UserRegisterDTO userRegisterDTO);//註冊

    JsonResult userByInformation(HttpSession session);//顯示會員中心個人資料

    JsonResult updateUser(UserRegisterDTO userRegisterDTO, HttpSession session);//修改會員中心資料

//    JsonResult selectUserByEmail(UserRegisterDTO userRegisterDTO);//忘記密碼(查詢所有的email)

    JsonResult sendResetPasswordEmail(UserRegisterDTO userRegisterDTO);//寄出email

    JsonResult updatePasswordResetToken(UserRegisterDTO userRegisterDTO);//基於reset_token 去修改密碼 要把token跟時間清空
}