package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//枚舉類 定義回應的長量信息 狀態碼,回應信息
@AllArgsConstructor //全參構造
@NoArgsConstructor //無參構造
@Getter
public enum StatusCode {
    //定義枚舉實例
    SUCCESS(1,"OK"),
    NOT_LOGIN(1000,"登入失敗"),
    LOGIN_SUCCESS(1001, "未成功"),
    PASSWORD_ERROR(1002, "密碼錯誤"),
    ACCOUTNT_PASSWORD_ERROR(1003,"帳號與密碼錯誤"),
    ACCOUTNT_ALREADY_EXISTS(1004, "帳號已存在"),
    FORBIDDEN_ERROR(1005, "無權訪問"),
    OPERATION_SUCCESS(2001, "操作成功"),
    OPERATION_FAILED(2002, "操作失敗"),
    VALIDATE_ERROR(3002, "參數校驗失敗");
    private Integer code; //回應狀態碼
    private String msg; //回應狀態信息結果
}
