package com.it.ynzl.main.security.handler;

import com.it.ynzl.main.common.ResponseUtils;
import com.it.ynzl.main.common.ResultEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 登陆失败处理类
 * @Author: Mr.mxl
 * @CreateDate: 2021/12/10 12:54
 */
@Component
public class UserLoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) {
        ResponseUtils.responseJson(response, ResponseUtils.Result(ResultEnum.FAILURE));
    }
}
