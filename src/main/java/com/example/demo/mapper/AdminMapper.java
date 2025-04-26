package com.example.demo.mapper;

import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {

    Admin getAdminByAccountPassword(String account, String password, boolean is_admin);//登入
}
