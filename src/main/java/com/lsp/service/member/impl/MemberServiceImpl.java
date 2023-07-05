package com.lsp.service.member.impl;


import cn.dev33.satoken.stp.StpUtil;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lsp.mapper.user.UserMapper;
import com.lsp.pojo.Result;
import com.lsp.pojo.user.entity.User;
import com.lsp.pojo.user.resquest.UserInfoRequest;
import com.lsp.service.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: LinShanPeng
 */
@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean finishInfo(UserInfoRequest info) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("user_phone", info.getUserPhone());
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            return false;
        } else {
            userMapper.update(info, wrapper);
            return true;
        }

    }

    @Override
    public Result<User> getInfo() {
        User user = userMapper.selectById(Integer.parseInt(String.valueOf(StpUtil.getLoginId())));
        return Result.success("用户信息:", user);
    }
}
