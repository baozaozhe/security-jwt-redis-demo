package com.it.ynzl.main.security.handler;

import com.it.ynzl.main.common.ResponseUtils;
import com.it.ynzl.main.common.ResultEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: Mr.mxl
 * @CreateDate: 2021/12/10 12:57
 */
@Component
public class UserNotLoginHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        ResponseUtils.responseJson(response, ResponseUtils.Result(ResultEnum.NOT_LOGIN));
    }
}