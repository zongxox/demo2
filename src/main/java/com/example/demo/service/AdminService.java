package com.example.demo.service;



import com.example.demo.dto.AdminRegisterDTO;
import com.example.demo.entity.Admin;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.response.JsonResult;
import com.example.demo.response.StatusCode;
import com.example.demo.vo.AdminLoginVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    //顯示管理中心個人資料
    public JsonResult adminByInformation(HttpSession session){
        AdminLoginVO sessionAdmin =(AdminLoginVO) session.getAttribute("sessionAdmin");
        return JsonResult.ok(sessionAdmin);
    }

    //修改管理中心資料
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
}
