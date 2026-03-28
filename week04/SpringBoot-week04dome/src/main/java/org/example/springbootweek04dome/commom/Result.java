package org.example.springbootweek04dome.commom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    // 成功响应（带数据）
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }

    // 成功响应（不带数据）
    public static <T> Result<T> success() {
        return new Result<>(200, "success", null);
    }

    // 失败响应
    public static <T> Result<T> error(String message) {
        return new Result<>(500, message, null);
    }

    // 失败响应（自定义错误码）
    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message, null);
    }
}
