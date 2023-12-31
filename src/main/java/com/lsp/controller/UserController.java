package com.lsp.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.lsp.pojo.Result;
import com.lsp.pojo.user.request.RegisterRequest;
import com.lsp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: LinShanPeng
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<SaTokenInfo> doLogin(String userPhone, String password) {
        return userService.doLogin(userPhone, password);
    }

    @GetMapping("/isLogin")
    public Result isLogin() {
        return Result.success("是否登录", StpUtil.isLogin());
    }

    @PostMapping("/register")
    public Result<SaTokenInfo> register(@RequestBody RegisterRequest request) {
        if (userService.register(request)) {
            //注册后自动登录
            return doLogin(request.getMobile(), request.getPassword());
        } else {
            return Result.error("注册失败");
        }
    }

    @GetMapping("/isRegister")
    public Result<String> isRegister(String phone) {
        if (userService.isRegister(phone)) {
            return Result.success("该手机号可用");
        } else {
            return Result.error("已存在该手机号");
        }
    }

    @GetMapping("/logout")
    public Result<String> logout() {
        StpUtil.logout();
        return Result.success("退出成功");
    }
}
