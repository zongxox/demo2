package com.example.demo.filter;

import com.example.demo.vo.AdminLoginVO;
import com.example.demo.vo.UserLoginVO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//登入過濾器,登入或未登入時做檢查才能訪問會員或管理員頁面或返回登入畫面
//標記當前類是過濾器類,在執行控制器之前會先執行
//要用不同的瀏覽器去測試管理員跟用戶
@WebFilter(filterName = "loginFilter",urlPatterns = {"/admin.html","/user.html"})
public class LoginFilter implements Filter {
    //每一次有符合 urlPatterns 的請求進來時,執行的地方
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws
            IOException, ServletException {
        System.out.println("請求過濾中");
        //FilterChain,當前請求他的一個鏈式,就是前端發起請求,由過濾器攔截下來,攔截下來後先記住他要訪問的是誰,這是一個接口
        //ServletRequest,是請求的接口,對應需要使用實現類HttpServletRequest,該實現類可以拿到請求中的信息,這是一個接口
        //servletResponse,是請求的接口,對應需要使用實現類HttpServletResponse,該實現類可以發送響應的信息,這是一個接口
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();//拿到請求的session對象
        //獲取回傳前端的UserLoginVO對象,AdminLoginVO對象,裡面保存了回應的屬性
        UserLoginVO sessionUser =(UserLoginVO) session.getAttribute("sessionUser");
        AdminLoginVO sessionAdmin =(AdminLoginVO) session.getAttribute("sessionAdmin");
        //判斷當前sessionUser,跟sessionAdmin,拿到的對象裡面有沒有登入的數據
        if(sessionUser == null&&sessionAdmin == null){
            //等於空代表未登入,所以就返回登入畫面
            response.sendRedirect("/login.html");
            return;
        }
        //如果有數據代表已登入,就會放行到Contrller去繼續執行
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("過濾器執行結束了");
    }
}
