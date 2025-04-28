package com.example.demo.mapper;

import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {

    Admin getAdminByAccountPassword(String account, String password);//登入

    int updateAdmin(Admin admin);//修改管理中心資料

    Admin selectAdminById(int id);//查詢adminId
}
