package com.example.demo.service;

import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.response.JsonResult;
import com.example.demo.response.StatusCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//Service 做判斷、做查詢、做邏輯
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    //註冊
    public JsonResult saveByUser(UserRegisterDTO userRegisterDTO){
        // 查詢帳號是否重複
        User existingUser = userMapper.getUserByAccount(userRegisterDTO.getAccount());
        if (existingUser != null) { // 如果帳號重複
            return new JsonResult(StatusCode.ACCOUNT_ALREADY_EXISTS);
        }

        // DTO 轉存到 Entity
        //前端傳過來的DTO屬性資料,轉存到Entity(user)屬性,然後再存到數據庫,所以不需要用到VO去回應
        //註冊就是收資料 ➔ 存資料，不是拿資料給前端，所以只用 DTO 和 Entity，不用 VO。
        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO, user);
        user.setIs_admin(false); // 註冊的使用者預設不是管理員

        int rows = userMapper.saveUser(user);

        if (rows > 0) {
            return JsonResult.ok();  // 新增成功
        } else {
            return new JsonResult(StatusCode.OPERATION_FAILED);  // 操作失敗
        }
    }

//    //查詢Email
//    public JsonResult selectByEmail(String email) {
//        User userEmail = userMapper.selectUserByEmail(email);
//        if(userEmail != null){
//            return JsonResult.ok(userEmail);
//        }
//        return new JsonResult(StatusCode.OPERATION_FAILED);
//    }


}
