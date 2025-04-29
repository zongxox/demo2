package com.example.demo.mapper;

import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    Admin getAdminByAccountPassword(String account, String password);//登入

    int updateAdmin(Admin admin);//修改管理中心資料

    Admin selectAdminById(int id);//查詢adminId

    List<User> selectUserById(Integer id);//查詢用戶
}
