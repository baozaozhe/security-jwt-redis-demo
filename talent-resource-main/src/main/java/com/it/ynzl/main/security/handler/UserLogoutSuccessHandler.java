package com.it.ynzl.main.security.handler;

import com.it.ynzl.main.common.ResponseUtils;
import com.it.ynzl.main.security.config.JWTConfig;
import com.it.ynzl.main.security.utils.JWTTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 登出成功处理
 * @Author: Mr.mxl
 * @CreateDate: 2021/12/10 12:56
 */
@Slf4j
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) {
        // 添加到黑名单
        String token = request.getHeader(JWTConfig.tokenHeader);
        JWTTokenUtils.addBlackList(token);

        log.info("用户{}登出成功，Token信息已保存到Redis的黑名单中", JWTTokenUtils.getUserNameByToken(token));

        SecurityContextHolder.clearContext();
        ResponseUtils.responseJson(response, ResponseUtils.Success("登出成功"));
    }
}
