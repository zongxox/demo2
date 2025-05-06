package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;


//連接資料庫，寫 SQL 查資料
@Mapper
public interface UserMapper {

    int saveUser(User user);//新增用戶

    User getUserByAccountPassword(String account,String password);//登入

    int updateUser(User user);//修改會員中心資料

    User getUserByAccount(String account);//判斷帳號是否重複

    User selectUserByEmail(String email);//忘記密碼(查詢所有的email)

    User selectUserById(Integer id);//查詢userId

    int sendResetPasswordEmail(String email, String reset_token, LocalDateTime reset_token_expire);//寄出email

    int updatePasswordResetToken(String reset_token,String password);//基於reset_token 去修改密碼 要把token跟時間清空

    User selectTokenTime(String reset_token);//基於token查詢數據庫是否過期

    User verifyEmail(String email_verify_token);//註冊時email驗證

    int updateEmailToken(String email_verify_token);//清空註冊時token跟時間

}
