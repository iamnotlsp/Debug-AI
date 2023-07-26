package com.lsp.service.resource.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsp.mapper.*;
import com.lsp.pojo.MyPage;
import com.lsp.pojo.home.response.subclass.GridInfo;
import com.lsp.pojo.home.response.subclass.SchemeDetail;
import com.lsp.pojo.member.entity.UserCollection;
import com.lsp.pojo.member.entity.UserHistory;
import com.lsp.pojo.resource.entity.Resource;
import com.lsp.pojo.resource.entity.ResourceComment;
import com.lsp.pojo.resource.entity.ResourceImage;
import com.lsp.pojo.resource.entity.UserLike;
import com.lsp.pojo.resource.request.CommentRequest;
import com.lsp.pojo.resource.response.Comment;
import com.lsp.pojo.resource.response.CommentResponse;
import com.lsp.pojo.resource.response.subclass.MyState;
import com.lsp.pojo.user.entity.User;
import com.lsp.service.resource.ResourceService;
import com.lsp.utils.MyUtil;
import com.lsp.utils.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @Autowired
    private ResourceImageMapper resourceImageMapper;

    @Autowired
    private ResourceCommentMapper resourceCommentMapper;

    @Autowired
    private LikeMapper likeMapper;

    @Autowired
    private CollectionsMapper collectionsMapper;

    /**
     * 得到资源
     *
     * @param resourceId
     * @return
     */
    @Override
    public Resource getResourceById(Integer resourceId) {
        QueryWrapper<Resource> wrapper = new QueryWrapper<>();
        wrapper.eq("resource_id", resourceId);
        return resourceMapper.selectOne(wrapper);
    }

    /**
     * 添加浏览记录
     *
     * @param resourceId
     */
    @Override
    public void insertHistory(Integer resourceId) {
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
    }

    /**
     * 添加阅读次数
     *
     * @param resourceId
     */
    @Override
    public void addReads(Integer resourceId) {

        Resource resource = resourceMapper.selectById(resourceId);
        resource.setResourceReads(resource.getResourceReads() + 1);
        resourceMapper.updateById(resource);
    }

    /**
     * 得到图片
     *
     * @param resourceId
     * @return
     */
    @Override
    public List<String> getPhotoUrl(Integer resourceId) {
        List<ResourceImage> photos = resourceImageMapper.selectList(new QueryWrapper<ResourceImage>()
                .eq("resource_id", resourceId));
        ArrayList<String> list = new ArrayList<>();
        for (ResourceImage photo : photos) {
            list.add(photo.getPhotoUrl());
        }
        return list;
    }

    /**
     * 得到评论
     */
    @Override
    public CommentResponse getComments(Integer resourceId, Integer start, Integer pageSize) {
        //得到评论
        Page<ResourceComment> page = new Page<>(start, pageSize);
        Page<ResourceComment> commentPage = resourceCommentMapper.selectPage(page, new QueryWrapper<ResourceComment>()
                .eq("resource_id", resourceId));
        List<ResourceComment> comments = commentPage.getRecords();
        ArrayList<Comment> list = new ArrayList<>();
        for (ResourceComment comment : comments) {
            Comment comment1 = new Comment(comment);
            //得到用户名
            User user = userMapper.selectOne(new QueryWrapper<User>()
                    .eq("user_phone", comment.getUserPhone()));
            comment1.setUserName(user.getUserName());
            list.add(comment1);
        }

        //得到page信息
        MyPage myPage = new MyPage(page);

        return new CommentResponse(myPage, list);
    }

    /**
     * 新增评论
     */

    @Override
    public CommentResponse postComment(Integer resourceId, CommentRequest request) {
        //得到用户信息
        Integer loginId = MyUtil.getLoginId();
        User user = userMapper.selectById(loginId);

        //添加评论
        String comment = request.getComment();
        String commentPhoto = request.getCommentPhoto();
        ResourceComment resourceComment = new ResourceComment(comment, commentPhoto);
        resourceComment.setResourceId(resourceId);
        resourceComment.setUserPhone(user.getUserPhone());
        resourceComment.setGroup_id(user.getGroupId());
        resourceCommentMapper.insert(resourceComment);

        return getComments(resourceId, 1, 3);
    }

    /**
     * 得到我的状态（点赞收藏情况）
     */
    @Override
    public MyState getMyState(Integer resourceId) {
        Integer id = MyUtil.getLoginId();
        User user = userMapper.selectById(id);
        UserLike like = likeMapper.selectOne(new QueryWrapper<UserLike>()
                .eq("user_phone", user.getUserPhone())
                .eq("resource_id", resourceId));
        UserCollection collection = collectionsMapper.selectOne(new QueryWrapper<UserCollection>()
                .eq("user_phone", user.getUserPhone())
                .eq("resource_id", resourceId));
        int likeState = 0;
        int collectState = 0;
        if (like != null) {
            likeState = 1;
        }
        if (collection != null) {
            collectState = 1;
        }
        return new MyState(likeState, collectState);
    }

    /**
     * 取消点赞
     */
    @Override
    public boolean cancelLike(Integer resourceId) {
        Integer id = MyUtil.getLoginId();
        User user = userMapper.selectById(id);
        UserLike like = likeMapper.selectOne(new QueryWrapper<UserLike>()
                .eq("user_phone", user.getUserPhone())
                .eq("resource_id", resourceId));

        Resource resource = resourceMapper.selectById(resourceId);
        resource.setResourceLikes(resource.getResourceLikes() - 1);
        resourceMapper.updateById(resource);

        if (like != null) {
            likeMapper.deleteById(like);
            return true;
        }
        return false;
    }


    /**
     * 设置点赞
     */
    @Override
    public boolean addLike(Integer resourceId) {
        Integer id = MyUtil.getLoginId();
        User user = userMapper.selectById(id);
        UserLike like = likeMapper.selectOne(new QueryWrapper<UserLike>()
                .eq("user_phone", user.getUserPhone())
                .eq("resource_id", resourceId));

        Resource resource = resourceMapper.selectById(resourceId);
        resource.setResourceLikes(resource.getResourceLikes() + 1);
        resourceMapper.updateById(resource);

        if (like == null) {
            likeMapper.insert(new UserLike(user.getUserPhone(), resourceId));
            return true;
        }

        return false;
    }

    /**
     * 取消收藏
     */
    @Override
    public boolean cancelCollection(Integer resourceId) {
        Integer id = MyUtil.getLoginId();
        User user = userMapper.selectById(id);
        UserCollection collection = collectionsMapper.selectOne(new QueryWrapper<UserCollection>()
                .eq("user_phone", user.getUserPhone())
                .eq("resource_id", resourceId));

        if (collection != null) {
            collectionsMapper.deleteById(collection);
            return true;
        }
        return false;
    }

    /**
     * 添加收藏
     */

    @Override
    public boolean addCollection(Integer resourceId) {
        Integer id = MyUtil.getLoginId();
        User user = userMapper.selectById(id);
        UserCollection collection = collectionsMapper.selectOne(new QueryWrapper<UserCollection>()
                .eq("user_phone", user.getUserPhone())
                .eq("resource_id", resourceId));

        if (collection == null) {
            collectionsMapper.insert(new UserCollection(user.getUserPhone(), resourceId));
            return true;
        }
        return false;
    }

    /**
     * 根据label得到资源
     */

    public List<GridInfo> getInfoByLabel(String label) {
        int start = new Random().nextInt(3) + 1;
        Page<Resource> page = new Page<>(start, 2);
        Page<Resource> resourcePage = resourceMapper.selectPage(page, new QueryWrapper<Resource>()
                .eq("resource_label", label)
                .orderByDesc("id"));

        ArrayList<GridInfo> list = new ArrayList<>();
        for (Resource resource : resourcePage.getRecords()) {
            list.add(new GridInfo(resource, new SchemeDetail(resource.getResourceId())));
        }
        return list;
    }


}
