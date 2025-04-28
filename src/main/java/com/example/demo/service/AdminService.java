package com.example.demo.service;

import com.example.demo.dto.AdminRegisterDTO;
import com.example.demo.response.JsonResult;
import jakarta.servlet.http.HttpSession;

public interface AdminService {
    JsonResult adminByInformation(HttpSession session);

    JsonResult updateAdmin(AdminRegisterDTO adminRegisterDTO, HttpSession session);
}