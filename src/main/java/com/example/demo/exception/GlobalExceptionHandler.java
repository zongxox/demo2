package com.example.demo.exception;


import com.example.demo.response.JsonResult;
import com.example.demo.response.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


//全局異常處裡類,負責所有Controller的異常處理
//@ControllerAdvice 標記當前的類 是捕獲所有Controller類的異常
//@RestControllerAdvice 複合註解:標記當前類 是捕獲所有Controller類的異常+@ResponseBody註解讓方法回傳的資料直接變成 JSON 或其他格式
//會自動捕獲異常 Controller類的異常
@RestControllerAdvice
@Slf4j  //lombok中提供的注解 ,可以用来打印日志信息
public class GlobalExceptionHandler {
    //該方法捕獲的是如果控制器中出現了IllegalArgumentException 異常時
    //當前方法則會被執行 並返回狀態碼和異常對象的信息
    //當控制器的方法接收到的參數不對時會出現的異常!!!!!!!!!!!
    //@ExceptionHandler() 裡面指定的類型，表示你要處理哪一種異常
    @ExceptionHandler(IllegalArgumentException.class)
    public JsonResult doHandlerIllegalArgumentException(IllegalArgumentException e) {
        log.debug("發生了IllegalArgumentException異常:{}", e.getMessage());//日誌輸出,控制台查看錯誤
        //包含了JsonResult對象,包含狀態碼和異常信息,這是給用戶看的
        return new JsonResult(StatusCode.OPERATION_FAILED, e.getMessage());
    }

//    @ExceptionHandler(RuntimeException.class)
//    public JsonResult doHandlerRuntimeException(RuntimeException e){
//        return new JsonResult(StatusCode.OPERATION_FAILED,e.getMessage());
//    }

  /*  //Throwable 是所有異常的父類！ 一般來講 開發階段使用的目的是為了防止服務氣終止
    @ExceptionHandler(Throwable.class)
    public JsonResult doHandlerThrowable(Throwable t){
        return new JsonResult(StatusCode.OPERATION_FAILED,"代碼執行過程中出現錯誤Throwable,信息為：" +t.getMessage());
    }*/
}
