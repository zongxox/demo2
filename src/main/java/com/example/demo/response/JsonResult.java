package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor//全參構造器註解
//JsonResult類 統一返回值結果
public class JsonResult {
    private Integer code; //回應狀態碼
    private String msg; //回應狀態信息結果
    private Object data;//回應數據,不確定回傳的是什麼數據,所以就用Object,任何數據都可以回傳

    //只包含狀態碼和回應信息的構造器
    public JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //通過枚舉類進行初始化
    public JsonResult(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }

    public JsonResult(StatusCode statusCode, Object data) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = data;
    }

    //初始化 成功的回應SUCCESS
    public JsonResult(Object data) {
        this.code = StatusCode.SUCCESS.getCode();
        this.msg = StatusCode.SUCCESS.getMsg();
        this.data = data;
    }
    private JsonResult(){
        this.code = StatusCode.SUCCESS.getCode();
        this.msg = StatusCode.SUCCESS.getMsg();
    }

    public static JsonResult ok(){
        return new JsonResult();
    }

    public static JsonResult ok(Object data){
        return new JsonResult(data);
    }
}
