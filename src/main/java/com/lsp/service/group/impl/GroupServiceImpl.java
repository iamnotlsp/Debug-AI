package com.lsp.service.group.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lsp.mapper.GroupMapper;
import com.lsp.mapper.ScoreMapper;
import com.lsp.mapper.UserMapper;
import com.lsp.pojo.score.entity.Score;
import com.lsp.pojo.user.entity.User;
import com.lsp.pojo.user.entity.UserGroup;
import com.lsp.service.group.GroupService;
import com.lsp.utils.MyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @Author: LinShanPeng
 */
@Slf4j
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer getGroupNums() {
        User user = userMapper.selectById(MyUtil.getLoginId());
        QueryWrapper<Score> wrapper = new QueryWrapper<>();
        wrapper.eq("group_id",user.getGroupId());
        Integer count = Math.toIntExact(scoreMapper.selectCount(wrapper));
        UserGroup group = groupMapper.selectByGroupId(user.getGroupId());
        group.setGroupNums(count);
        UpdateWrapper<UserGroup> wrapper1 = new UpdateWrapper<>();
        wrapper1.eq("group_id",group.getGroupId());
        groupMapper.update(group,wrapper1);
        return count;
    }
}
