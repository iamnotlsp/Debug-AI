package com.lsp.service.user;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.lsp.pojo.Result;
import com.lsp.pojo.user.resquest.RegisterRequest;


/**
 * @Author: LinShanPeng
 */
public interface UserService {
    Result<SaTokenInfo> doLogin(String userPhone, String password);

    boolean register(RegisterRequest request);

    boolean isRegister(String phone);
}
