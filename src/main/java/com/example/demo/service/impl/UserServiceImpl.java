package com.example.demo.service.impl;

import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.response.JsonResult;
import com.example.demo.response.StatusCode;
import com.example.demo.service.UserService;
import com.example.demo.vo.UserLoginVO;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.regex.Pattern;


//Service 做判斷、做查詢、做邏輯
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JavaMailSender javaMailSender;

    //註冊
    @Override
    public JsonResult saveByUser(UserRegisterDTO userRegisterDTO) {
        String email = userRegisterDTO.getEmail();

        // Email 空值與格式驗證
        if (email == null || email.trim().isEmpty()) {
            return new JsonResult(StatusCode.PARAM_ERROR, "Email 不可為空");
        }

        // 不允許符號 ._-+%，且網域與帳號皆僅限英數
        String emailRegex = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$";
        if (!Pattern.matches(emailRegex, email)) {
            return new JsonResult(StatusCode.PARAM_ERROR, "Email 格式不正確");
        }


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

    //顯示會員中心個人資料
    @Override
    public JsonResult userByInformation(HttpSession session) {
        UserLoginVO sessionUser = (UserLoginVO) session.getAttribute("sessionUser");
        return JsonResult.ok(sessionUser);
    }

    //修改會員中心資料
    @Override
    public JsonResult updateUser(UserRegisterDTO userRegisterDTO, HttpSession session) {
        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO, user);
        int rows = userMapper.updateUser(user);
        if (rows > 0) {
            User userById = userMapper.selectUserById(user.getId());
            UserLoginVO vo = new UserLoginVO();
            BeanUtils.copyProperties(userById, vo);
            session.setAttribute("sessionUser", vo);
            return JsonResult.ok(vo);
        }
        return new JsonResult(StatusCode.OPERATION_FAILED);
    }


//    //忘記密碼(查詢所有的email)
//    public JsonResult selectUserByEmail(UserRegisterDTO userRegisterDTO) {
//        String email = userRegisterDTO.getEmail();//從DTO 取得前端傳來的Email
//
//        if(email == null || email.trim().isEmpty()){//判斷是否有空白
//            return new JsonResult(StatusCode.PARAM_ERROR, "Email 不可為空");
//        }
//
//        // Email 格式驗證
//        String emailRegex = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$";
//        if (!Pattern.matches(emailRegex, email)) {
//            return new JsonResult(StatusCode.PARAM_ERROR, "Email 格式不正確"); // 格式錯誤也回傳參數錯誤
//        }
//
//        User user = userMapper.selectUserByEmail(email);//用Email查詢資料庫中的使用者
//        if (user != null) {
//            UserLoginVO vo = new UserLoginVO();//建立要回傳給前端的 VO
//            BeanUtils.copyProperties(user, vo);//user的資料複製到 VO，避免暴露敏感資訊
//            return JsonResult.ok(vo);//查到資料就回傳成功
//        }
//        return new JsonResult(StatusCode.EMAIL_NOT_FOUND);//查不到時回傳錯誤狀態
//    }

    //寄出email
    @Override
    public JsonResult sendResetPasswordEmail(UserRegisterDTO userRegisterDTO) {
        String email = userRegisterDTO.getEmail(); // 從 DTO 取得前端傳來的 email

        if (email == null || email.trim().isEmpty()) { // 檢查 email 是否為空或只有空白
            return new JsonResult(StatusCode.PARAM_ERROR, "Email 不可為空"); // 返回參數錯誤
        }

        // Email 格式驗證
        String emailRegex = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$";
        if (!Pattern.matches(emailRegex, email)) {
            return new JsonResult(StatusCode.PARAM_ERROR, "Email 格式不正確"); // 格式錯誤也回傳參數錯誤
        }

        User user = userMapper.selectUserByEmail(email); // 用 email 查詢使用者資料
        if (user == null) { // 如果找不到該 email
            return new JsonResult(StatusCode.EMAIL_NOT_FOUND, "查無此 Email"); // 回傳查無此 Email
        }

        String reset_token = UUID.randomUUID().toString(); // 產生唯一重設密碼的 token（防止被猜）
        LocalDateTime reset_token_expire = LocalDateTime.now().plusMinutes(30); // 設定 token 過期時間為 30 分鐘後
        userMapper.sendResetPasswordEmail(email, reset_token, reset_token_expire); // 更新資料庫，寫入 token 與過期時間

        SimpleMailMessage message = new SimpleMailMessage(); // 建立 email 訊息物件
        message.setTo(email); // 設定收件人為查詢到的 email
        message.setSubject("您的密碼重設連結"); // 設定信件主旨
        message.setText("請點擊以下連結重設密碼（30 分鐘內有效）：\n" +
                "http://localhost:8080/reset-password.html?token=" + reset_token); // 設定信件內容（帶上 token 的連結）
        javaMailSender.send(message); // 寄出 email

        return JsonResult.ok("密碼重設連結已寄出，請查收 Email"); // 回傳成功訊息
    }

    //基於reset_token 去修改密碼 要把token跟時間清空
    @Override
    public JsonResult updatePasswordResetToken(UserRegisterDTO userRegisterDTO) {
        String reset_token = userRegisterDTO.getReset_token(); // 從 DTO 取得前端傳來的 token
        String password = userRegisterDTO.getPassword(); // 取得新密碼

        int rows = userMapper.updatePasswordResetToken(reset_token,password); // 執行資料庫更新（更新密碼、清除 token）
        if (rows > 0) {
            return JsonResult.ok("密碼已更新成功"); // 回傳成功訊息
        }
        return new JsonResult(StatusCode.OPERATION_FAILED, "密碼更新失敗"); // 若失敗，回傳錯誤狀態
    }
}
