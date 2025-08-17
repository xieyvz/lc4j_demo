package com.xieyv.lc4j.entity;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;

    private String msg;

    private T data;

    private static <T> Result<T> build(Integer code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    private static <T> Result<T> build(T data) {
        Result<T> result = build(200, "成功");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> ok() {
        return build(200, "成功");
    }

    public static <T> Result<T> ok(T data) {
        return build(data);
    }

    public static <T> Result<T> fail() {
        return build(500, "错误");
    }

    public static <T> Result<T> fail(Integer code, String msg) {
        return build(code, msg);
    }
}
