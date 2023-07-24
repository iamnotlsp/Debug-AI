package com.lsp.config;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: LinShanPeng
 */

public class SaTokenInterceptor implements HandlerInterceptor {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 检查用户是否已登录
        if (!StpUtil.isLogin()) {
            // 使用 Sa-Token 登录方法进行自动登录
            StpUtil.login(0);


        }
        // 继续执行后续操作
        return true;
    }


}
