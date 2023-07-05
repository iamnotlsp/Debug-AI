package com.lsp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LinShanPeng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;//响应码，0 代表成功; 1 代表失败
    private String msg;  //响应信息 描述字符串
    private T data; //返回的数据

    //增删改 成功响应
    public static <T> Result<T> success(String msg) {
        return new Result<T>(0, msg, null);
    }

    //查询 成功响应
    public static <T> Result<T> success(String msg ,T data) {
        return new Result<T>(0, msg, data);
    }

    //失败响应
    public static <T> Result<T> error(String msg) {
        return new Result<T>(1, msg, null);
    }
}