package com.lsp.config;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsp.mapper.ScoreDetailMapper;
import com.lsp.mapper.ScoreMapper;
import com.lsp.mapper.UserMapper;
import com.lsp.pojo.Result;
import com.lsp.pojo.score.entity.Score;
import com.lsp.pojo.score.entity.ScoreDetail;
import com.lsp.pojo.user.entity.User;
import com.lsp.service.member.impl.MemberServiceImpl;
import com.lsp.service.user.impl.UserServiceImpl;
import com.lsp.utils.MyUtil;
import com.lsp.utils.NumberUtil;
import net.sf.jsqlparser.util.validation.metadata.NamedObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

/**
 * @Author: LinShanPeng
 */

public class SaTokenInterceptor implements HandlerInterceptor {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 检查用户是否已登录
        if (!StpUtil.isLogin()) {
            // 使用 Sa-Token 登录方法进行自动登录
            StpUtil.login(1);


        }
        // 继续执行后续操作
        return true;
    }


}
