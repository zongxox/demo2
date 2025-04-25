package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    int saveUser(User user);//新增用戶

    User getUserByAccountPassword(String account,String password);//登入

    int updateUser(User user);
}
