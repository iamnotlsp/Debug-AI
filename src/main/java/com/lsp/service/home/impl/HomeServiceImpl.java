package com.lsp.service.home.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsp.mapper.AiHistoryMapper;
import com.lsp.mapper.ResourceMapper;
import com.lsp.mapper.ResourceShowMapMapper;
import com.lsp.mapper.UserMapper;
import com.lsp.pojo.home.entity.AiHistory;
import com.lsp.pojo.home.response.*;
import com.lsp.pojo.home.response.subclass.*;
import com.lsp.pojo.resource.entity.Resource;
import com.lsp.pojo.resource.entity.ResourceShowMap;
import com.lsp.pojo.user.entity.User;
import com.lsp.service.home.HomeService;
import com.lsp.utils.MyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LinShanPeng
 */
@Slf4j
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private ResourceShowMapMapper resourceShowMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AiHistoryMapper aiHistoryMapper;

    /**
     * 获得首页全部内容
     *
     * @return
     */
    @Override
    public HomeAllResponse getAll() {
        ArrayList<BaseHomeType> list = new ArrayList<>();


        list.add(getCarousel());
        list.add(getLikes());
        list.add(getHotTopic());
        list.add(getVoteInfo());
        list.add(getAiHistory());
        return new HomeAllResponse(list);
    }

    /**
     * 轮播图
     */
    public AllCarouselInfo getCarousel() {
        ArrayList<CarouselInfo> list = new ArrayList<>();
        QueryWrapper<ResourceShowMap> wrapper = new QueryWrapper<ResourceShowMap>()
                .eq("resource_show", 1);
        for (ResourceShowMap show : resourceShowMapper.selectList(wrapper)) {
            Integer resourceId = show.getResourceId();
            Resource resource = resourceMapper.selectById(resourceId);
            SchemeH5 h5 = new SchemeH5(1, "跳转链接", resource.getResourceUrl());
            list.add(new CarouselInfo(resource.getResourcePhoto(), resource.getResourceTitle(), h5));
        }

        return new AllCarouselInfo(BaseHomeType.Carousel, list);
    }

    /**
     * 标签推送
     */
    public AllLikeInfo getLikes() {
        //得到用户标签
        Integer id = MyUtil.getLoginId();
        User user = userMapper.selectById(id);
        String likes = user.getUserLikes();
        //判断likes内容,以及是否有这个标签
        ArrayList<LikeInfo> list = new ArrayList<>();
        if (likes == null) {
            likes = "答疑";
        }
        QueryWrapper<Resource> wrapper = new QueryWrapper<Resource>().eq("resource_label", likes);
        if (resourceMapper.selectCount(wrapper) == 0) {
            likes = "答疑";
        }

        wrapper = new QueryWrapper<Resource>().eq("resource_label", likes);
        List<Resource> resources = resourceMapper.selectList(wrapper);
        for (Resource resource : resources) {
            LikeInfo likeInfo = new LikeInfo(resource);
            likeInfo.setScheme(new SchemeDetail(3, "详细资源", resource.getResourceId()));
            list.add(likeInfo);
        }

        return new AllLikeInfo(BaseHomeType.Like, list);
    }

    /**
     * 热门话题
     */
    public AllHotTopicInfo getHotTopic() {
        ArrayList<HotTopicInfo> list = new ArrayList<>();
        //limit 倒序查5个点赞数最高资源
        Page<Resource> page = new Page<>(1, 5);
        QueryWrapper<Resource> wrapper = new QueryWrapper<Resource>().orderByDesc("resource_likes");
        Page<Resource> resourcePage = resourceMapper.selectPage(page, wrapper);

        //进行排名
        int ranking = 1;
        List<Resource> resources = resourcePage.getRecords();
        for (Resource resource : resources) {
            HotTopicInfo hotTopicInfo = new HotTopicInfo(resource);
            hotTopicInfo.setRankings(ranking++);
            hotTopicInfo.setScheme(new SchemeDetail(3, "详细资源", resource.getResourceId()));
            list.add(hotTopicInfo);
        }

        return new AllHotTopicInfo(BaseHomeType.HOT, list);
    }

    /**
     * 投票
     */
    public AllVoteInfo getVoteInfo() {
        ArrayList<VoteInfo> list = new ArrayList<>();
        VoteInfo info = new VoteInfo("人工智能能否取代人类", 22, 98);
        list.add(info);
        return new AllVoteInfo(BaseHomeType.VOTE, list);
    }

    /**
     * 百年历程
     */
    public AllAiHistoryInfo getAiHistory(){
        ArrayList<AiHistoryInfo> list = new ArrayList<>();
        Page<AiHistory> page = new Page<>(1, 3);
        Page<AiHistory> historyPage = aiHistoryMapper.selectPage(page, null);
        for (AiHistory history : historyPage.getRecords()) {
            list.add(new AiHistoryInfo(history));
        }

        return new AllAiHistoryInfo(BaseHomeType.HISTORY,list);
    }
}
