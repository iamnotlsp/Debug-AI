package com.lsp.service.member.impl;


import cn.dev33.satoken.stp.StpUtil;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lsp.mapper.ScoreMapper;
import com.lsp.mapper.UserMapper;
import com.lsp.pojo.Result;
import com.lsp.pojo.member.response.CollectionResponse;
import com.lsp.pojo.user.entity.User;
import com.lsp.pojo.user.resquest.UserInfoRequest;
import com.lsp.service.member.MemberService;
import com.lsp.utils.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

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
//        System.out.println(user.getCreateTime().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        System.out.println(NumberUtil.getTodayLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return Result.success("用户信息:", user);
    }

    @Override
    public CollectionResponse getCollections() {
        return null;
    }
}
