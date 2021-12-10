package com.it.ynzl.main.security.handler;

import com.it.ynzl.main.common.ResponseUtils;
import com.it.ynzl.main.common.ResultEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 无权限处理类
 * @Author: Mr.mxl
 * @CreateDate: 2021/12/10 12:53
 */
@Component
public class UserAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseUtils.responseJson(response, ResponseUtils.Result(ResultEnum.ACCESS_DENIED));
    }

}