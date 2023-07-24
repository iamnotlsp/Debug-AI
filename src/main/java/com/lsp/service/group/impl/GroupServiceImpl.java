package com.lsp.service.group.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.lsp.mapper.*;
import com.lsp.pojo.member.response.GroupForumResponse;
import com.lsp.pojo.member.response.subclass.GroupForumInfo;
import com.lsp.pojo.resource.entity.Resource;
import com.lsp.pojo.resource.entity.ResourceComment;
import com.lsp.pojo.score.entity.Score;
import com.lsp.pojo.user.entity.User;
import com.lsp.pojo.user.entity.UserGroup;
import com.lsp.service.group.GroupService;
import com.lsp.utils.MyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


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

    @Autowired
    private ResourceCommentMapper resourceCommentMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public Integer getGroupNums() {
        User user = userMapper.selectById(MyUtil.getLoginId());
        QueryWrapper<Score> wrapper = new QueryWrapper<>();
        wrapper.eq("group_id", user.getGroupId());
        Integer count = Math.toIntExact(scoreMapper.selectCount(wrapper));
        UserGroup group = groupMapper.selectByGroupId(user.getGroupId());
        group.setGroupNums(count);
        UpdateWrapper<UserGroup> wrapper1 = new UpdateWrapper<>();
        wrapper1.eq("group_id", group.getGroupId());
        groupMapper.update(group, wrapper1);
        return count;
    }

    /**
     * 得到论坛
     *
     * @return
     */
    @Override
    public GroupForumResponse getGroupForum(Integer start, Integer pageSize) {
        User user = userMapper.selectById(MyUtil.getLoginId());
        Integer groupId = user.getGroupId();
        //对论坛进行分页查询
        Page<ResourceComment> page = new Page<>(start, pageSize);
        Page<ResourceComment> commentPage = resourceCommentMapper.selectPage(page, new QueryWrapper<ResourceComment>().
                eq("group_id", groupId).orderByDesc("create_time"));
        //写response
        ArrayList<GroupForumInfo> list = new ArrayList<>();
        for (ResourceComment comment : commentPage.getRecords()) {
            //用户信息
            User newUser = userMapper.selectOne(new QueryWrapper<User>()
                    .eq("user_phone", comment.getUserPhone()));
            //资源信息
            Resource resource = resourceMapper.selectById(comment.getResourceId());
            list.add(new GroupForumInfo(newUser.getUserName(), newUser.getHeadPhoto(), comment.getResourceId(),
                    resource.getResourceTitle(), resource.getResourcePhoto(), comment.getComment(),
                    comment.getCommentPhoto(), resource.getResourceLikes(), resource.getResourceComments(),
                    comment.getCreateTime()));
        }

        return new GroupForumResponse(list);
    }


}
