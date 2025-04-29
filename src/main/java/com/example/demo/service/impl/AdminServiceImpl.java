package com.example.demo.service.impl;



import com.example.demo.dto.AdminRegisterDTO;
import com.example.demo.dto.UserRegisterDTO;
import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.response.JsonResult;
import com.example.demo.response.StatusCode;
import com.example.demo.service.AdminService;
import com.example.demo.vo.AdminLoginVO;
import com.example.demo.vo.UserLoginVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    //顯示管理中心個人資料
    @Override
    public JsonResult adminByInformation(HttpSession session){
        AdminLoginVO sessionAdmin =(AdminLoginVO) session.getAttribute("sessionAdmin");
        return JsonResult.ok(sessionAdmin);
    }

    //修改管理中心資料
    @Override
    public JsonResult updateAdmin(AdminRegisterDTO adminRegisterDTO,HttpSession session){
        Admin admin = new Admin();//創建管理員對象
        BeanUtils.copyProperties(adminRegisterDTO,admin);//把前端獲取到的值封裝到admin
        int rows = adminMapper.updateAdmin(admin);//在執行數據庫修改操作
        if(rows>0){//判斷是否有修改一條數據
            Admin adminById = adminMapper.selectAdminById(admin.getId());//查詢修改後的admin
            AdminLoginVO vo = new AdminLoginVO();//創建新的前端回應對象
            BeanUtils.copyProperties(adminById,vo);//把修改過的admin屬性給vo
            session.setAttribute("sessionAdmin",vo);//再把新的session封裝到vo
            return JsonResult.ok(vo);//在用vo回應前端
        }
        return new JsonResult(StatusCode.OPERATION_FAILED);
    }

    //查詢用戶
    @Override
    public JsonResult selectUserByAccount(UserRegisterDTO userRegisterDTO) {
        String account = userRegisterDTO.getAccount();//獲取account值
        List<User> user = adminMapper.selectUserByAccount(account);//把查詢到的account紀錄 存到 user 集合
        List<UserLoginVO> voList  = new ArrayList<>();// List 用來存放封裝好的 UserLoginVO 物件
        for (User u : user) {//循環每一個 user裡面的值
            UserLoginVO vo = new UserLoginVO();//創建後端回應對象
            BeanUtils.copyProperties(u,vo);//把user的結果屬性複製到vo對象的屬性
            voList.add(vo);//再把每一筆的屬性資料添加給voList集合
        }
        return JsonResult.ok(voList);//將添加好的每一筆回應給前端
    }

}
