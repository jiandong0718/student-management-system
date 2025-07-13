package com.example.student.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一响应结果类
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * 成功
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 成功，带数据
     */
    public static <T> Result<T> success(T data) {
        return success(200, "操作成功", data);
    }

    /**
     * 成功，自定义状态码、消息和数据
     */
    public static <T> Result<T> success(Integer code, String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * 失败
     */
    public static <T> Result<T> error() {
        return error(500, "操作失败");
    }

    /**
     * 失败，自定义消息
     */
    public static <T> Result<T> error(String message) {
        return error(500, message);
    }

    /**
     * 失败，自定义状态码和消息
     */
    public static <T> Result<T> error(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
} 