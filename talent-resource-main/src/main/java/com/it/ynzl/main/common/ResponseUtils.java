package com.it.ynzl.main.common;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * @description: 返回数据对象
 * @author: Mr.Muxl
 * @create: 2021-06-28 13:23
 **/
@Slf4j
@Data
public class ResponseUtils<T>
{
    private Integer code;
    private String message;
    private  T     data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseUtils() {
    }

    public ResponseUtils(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseUtils(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * 自定义返回  code + msg
     * @param em
     * @param <T>
     * @return
     */
    public  static <T> ResponseUtils Result(ResultEnum em){
        ResponseUtils<Object> result = new ResponseUtils<Object>();
        result.setCode(em.getCode());
        result.setMessage(em.getMessage());
        return  result;
    }

    /**
     * 自定义返回  code + msg +data
     * @param em
     * @param <T>
     * @return
     */
    public  static <T> ResponseUtils Result(ResultEnum em, Object data){
        ResponseUtils<Object> result = new ResponseUtils<Object>();
        result.setCode(em.getCode());
        result.setMessage(em.getMessage());
        result.setData(data);
        return  result;
    }

    /**
     * 返回执行成功 +Void
     * @return
     */
    public static <T> ResponseUtils Success() {
        ResponseUtils<Object> result = new ResponseUtils<Object>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMessage());
        return result;
    }

    /**
     * 返回执行成功 +Void
     * @return
     */
    public static <T> ResponseUtils Success(String msg) {
        ResponseUtils<Object> result = new ResponseUtils<Object>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(msg);
        return result;
    }

    /**
     * 返回执行成功 +data
     * @return
     */
    public static <T> ResponseUtils Success(Object data) {
        ResponseUtils<Object> result = new ResponseUtils<Object>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }
    /**
     * 返回执行失败
     * @return
     */
    public static <T> ResponseUtils Failed() {
        ResponseUtils<Object> result = new ResponseUtils<>();
        result.setCode(ResultEnum.FAILURE.getCode());
        result.setMessage(ResultEnum.FAILURE.getMessage());
        return result;
    }

    /**
     * 返回执行失败
     * @return
     */
    public static <T> ResponseUtils Failed(String msg) {
        ResponseUtils<Object> result = new ResponseUtils<>();
        result.setCode(ResultEnum.FAILURE.getCode());
        result.setMessage(msg);
        return result;
    }

    /**
     * 返回执行失败
     * @return
     */
    public static <T> ResponseUtils Failed(Object data) {
        ResponseUtils<Object> result = new ResponseUtils<>();
        result.setCode(ResultEnum.FAILURE.getCode());
        result.setMessage(ResultEnum.FAILURE.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * Response输出Json格式
     *
     * @param response
     * @param data     返回数据
     */
    public static void responseJson(ServletResponse response, Object data) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(JSON.toJSONString(data));
            out.flush();
        } catch (Exception e) {
            log.error("Response输出Json异常：" + e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
