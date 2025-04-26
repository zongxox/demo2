package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.response.JsonResult;
import com.example.demo.response.StatusCode;
import com.example.demo.vo.AdminLoginVO;
import com.example.demo.vo.UserLoginVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AdminMapper adminMapper;

    public JsonResult login(String account, String password) {//接收Controller傳遞過來的參數
        // 查管理員
        //將參數控制器封裝過來的loginDTO.getAccount(),loginDTO.getPassword()屬性
        //拿去做方法參數,做數據庫做查詢,在封裝到admin裡面
        Admin admin = adminMapper.getAdminByAccountPassword(account, password);
        if (admin != null) {//查詢出來是空值就是false 是true就執行
            AdminLoginVO vo = new AdminLoginVO();//創建vo對象用於回傳給前端的封裝資料
            //BeanUtils.copyProperties(source, target)：自動把名字相同的屬性值名稱複製過去
            //把查詢到的admin資料庫轉存到vo,對應到vo裡面的屬興去封裝,在傳遞到前端,裡面已經設定好要傳遞什麼屬性給年端
            BeanUtils.copyProperties(admin, vo);
            return JsonResult.ok(vo);
        }

        // 查普通用戶
        User user = userMapper.getUserByAccountPassword(account, password);//將參數拿去user數據庫做查詢
        if (user != null) {//查詢出來是空值就是false 是true就執行
            UserLoginVO vo = new UserLoginVO();
            BeanUtils.copyProperties(user, vo);
            return JsonResult.ok(vo);
        }

        //兩者都查詢不到返回帳號密碼錯誤
        return new JsonResult(StatusCode.ACCOUNT_PASSWORD_ERROR);
    }
}
