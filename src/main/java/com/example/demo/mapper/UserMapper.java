package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;


//連接資料庫，寫 SQL 查資料
@Mapper
public interface UserMapper {

    int saveUser(User user);//新增用戶

    User getUserByAccountPassword(String account,String password);//登入

    int updateUser(User user);//修改

    User getUserByAccount(String account);//判斷帳號是否重複

    User selectUserByEmail(String email);//忘記密碼(查詢所有的email)
}
