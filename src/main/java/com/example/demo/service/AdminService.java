package com.example.demo.service;

import com.example.demo.dto.AdminRegisterDTO;
import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.response.JsonResult;
import jakarta.servlet.http.HttpSession;

public interface AdminService {
    JsonResult adminByInformation(HttpSession session);//顯示管理中心個人資料

    JsonResult updateAdmin(AdminRegisterDTO adminRegisterDTO, HttpSession session);//修改管理中心資料

    JsonResult selectUserById(UserRegisterDTO userRegisterDTO);//查詢用戶
}