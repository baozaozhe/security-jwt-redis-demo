package com.it.ynzl.main.common;

import lombok.Getter;

import java.io.Serializable;

/**
 * @Description: 返回的错误码枚举类
 * @Author: Mr.mxl
 * @CreateDate: 2021/12/9 16:38
 */
@Getter
public enum ResultEnum {

    SUCCESS(200,"成功"),
    FAILURE(400,"失败"),
    USER_NEED_AUTHORITIES(201,"用户未登录"),
    USER_LOGIN_FAILED(202,"用户账号或密码错误"),
    USER_LOGIN_SUCCESS(203,"用户登录成功"),
    USER_NO_ACCESS(204,"用户无权访问"),
    USER_LOGOUT_SUCCESS(205,"用户登出成功"),
    TOKEN_IS_BLACKLIST(206,"此token为黑名单"),
    LOGIN_IS_OVERDUE(207,"登录已失效"),
    NOT_LOGIN(401,"没有登录"),
    ACCESS_DENIED(403,"拒绝访问"),
    NOT_FOUND(404,"无记录"),
    UNAUTHORIZED(505,"令牌过期"),
    PARAMETER_ERROR(7001,"参数错误"),
    SYS_ERROR(7002,"系统错误");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @deprecation:通过code返回枚举
     */
    public static ResultEnum parse(int code){
        ResultEnum[] values = values();
        for (ResultEnum value : values) {
            if(value.getCode() == code){
                return value;
            }
        }
        throw  new RuntimeException("Unknown code of ResultEnum");
    }
}
