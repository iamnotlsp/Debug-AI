package com.lsp.utils;

import cn.dev33.satoken.stp.StpUtil;
import com.lsp.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: LinShanPeng
 */
public class MyUtil {

    public static Integer getLoginId() {
        Integer id = Integer.valueOf(String.valueOf(StpUtil.getLoginId()));
        return id;
    }
}
