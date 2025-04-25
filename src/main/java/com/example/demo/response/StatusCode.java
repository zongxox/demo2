package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//枚舉類 定義回應的長量信息 狀態碼,回應信息
@AllArgsConstructor //全參建構子
@NoArgsConstructor //無參建構子
@Getter
public enum StatusCode {
    //定義枚舉實例
    SUCCESS(1, "操作成功"),
    NOT_LOGIN(1001, "尚未登入"),
    ACCOUNT_PASSWORD_ERROR(1002, "帳號或密碼錯誤"),
    ACCOUNT_ALREADY_EXISTS(1003, "帳號已存在"),
    OPERATION_FAILED(1004, "操作失敗"),
    PARAM_ERROR(1005, "參數錯誤"),
    NO_PERMISSION(1006, "沒有權限"),
    SYSTEM_ERROR(1007, "系統錯誤，請稍後再試");
    private Integer code; //回應狀態碼
    private String msg; //回應狀態信息結果

}
