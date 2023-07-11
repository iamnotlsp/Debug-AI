package com.lsp.service.resource.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsp.mapper.HistoryMapper;
import com.lsp.mapper.ResourceMapper;
import com.lsp.mapper.UserMapper;
import com.lsp.pojo.member.entity.UserHistory;
import com.lsp.pojo.resource.entity.Resource;
import com.lsp.pojo.user.entity.User;
import com.lsp.service.resource.ResourceService;
import com.lsp.utils.MyUtil;
import com.lsp.utils.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: LinShanPeng
 */
@Slf4j
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private HistoryMapper historyMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Resource getResourceById(Integer resourceId) {
        //得到资源
        QueryWrapper<Resource> wrapper = new QueryWrapper<>();
        wrapper.eq("resource_id", resourceId);
        Resource resource = resourceMapper.selectOne(wrapper);

        //添加浏览记录
        Integer loginId = MyUtil.getLoginId();
        User user = userMapper.selectById(loginId);
        QueryWrapper<UserHistory> wrapper1 = new QueryWrapper<UserHistory>().eq("user_phone", user.getUserPhone())
                .eq("resource_id", resourceId)
                .ge("create_time", NumberUtil.getTodayLocalDateTime());
        System.out.println(historyMapper.selectOne(wrapper1));
        if (historyMapper.selectOne(wrapper1) == null) {
            UserHistory userHistory = new UserHistory();
            userHistory.setUserPhone(user.getUserPhone());
            userHistory.setResourceId(resourceId);
            historyMapper.insert(userHistory);
        }

        return resource;
    }
}
