package com.lsp.service.home.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsp.mapper.*;
import com.lsp.pojo.MyPage;
import com.lsp.pojo.home.entity.AiHistory;
import com.lsp.pojo.home.entity.Expert;
import com.lsp.pojo.home.response.*;
import com.lsp.pojo.home.response.subclass.*;
import com.lsp.pojo.member.entity.UserHistory;
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

    @Autowired
    private ExpertMapper expertMapper;

    @Autowired
    private HistoryMapper historyMapper;

    /**
     * 历史更多内容
     *
     * @return
     */
    @Override
    public AllAiHistoryInfo getMoreHistory() {
        ArrayList<AiHistoryInfo> list = new ArrayList<>();
        List<AiHistory> histories = aiHistoryMapper.selectList(null);
        for (AiHistory history : histories) {
            list.add(new AiHistoryInfo(history));
        }

        return new AllAiHistoryInfo(BaseHomeType.HISTORY, list);
    }

    /**
     * 专家更多内容
     *
     * @return
     */
    @Override
    public AllExpertInfo getMoreExpert(Integer start, Integer pageSize) {
        ArrayList<ExpertInfo> list = new ArrayList<>();
        Page<Expert> page = new Page<>(start, pageSize);
        Page<Expert> expertPage = expertMapper.selectPage(page, null);
        for (Expert expert : expertPage.getRecords()) {
            list.add(new ExpertInfo(expert, new SchemeDetail(expert.getResourceId())));
        }
        return new AllExpertInfo(BaseHomeType.EXPERT, list, new MyPage(expertPage));
    }

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
        list.add(getLastBrowse());
        list.add(getVoteInfo());
        list.add(getAiHistory());
        list.add(getExpert());
        list.add(getNews());
        list.add(getLaw());
        list.add(getGrid4());
        list.add(getFlashInfo());
        list.add(getGridChange());
        list.add(getFallInfo(1,6));
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
            SchemeH5 h5 = new SchemeH5(resource.getResourceUrl());
            list.add(new CarouselInfo(resource.getResourcePhoto(), resource.getResourceTitle(), h5));
        }

        return new AllCarouselInfo(BaseHomeType.Carousel, list);
    }

    /**
     * 上次浏览
     */
    public AllLastBrowseInfo getLastBrowse() {
        //得到用户信息
        Integer loginId = MyUtil.getLoginId();
        User user = userMapper.selectById(loginId);
        //得到上次浏览记录资源id
        Page<UserHistory> page = new Page<>(1, 1);
        Page<UserHistory> historyPage = historyMapper.selectPage(page, new QueryWrapper<UserHistory>()
                .eq("user_phone", user.getUserPhone())
                .orderByDesc("create_time"));
        //得到资源
        UserHistory history = historyPage.getRecords().get(0);
        Resource resource = resourceMapper.selectById(history.getResourceId());
        LastBrowseInfo info = new LastBrowseInfo(resource.getResourceTitle(),
                new SchemeDetail(resource.getResourceId()));
        return new AllLastBrowseInfo(BaseHomeType.BROWSE, info);
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
            likeInfo.setScheme(new SchemeDetail(resource.getResourceId()));
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
            hotTopicInfo.setScheme(new SchemeDetail(resource.getResourceId()));
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
    public AllAiHistoryInfo getAiHistory() {
        ArrayList<AiHistoryInfo> list = new ArrayList<>();
        Page<AiHistory> page = new Page<>(1, 3);
        Page<AiHistory> historyPage = aiHistoryMapper.selectPage(page, null);
        for (AiHistory history : historyPage.getRecords()) {
            list.add(new AiHistoryInfo(history));
        }

        return new AllAiHistoryInfo(BaseHomeType.HISTORY, list, new SchemeApi("/home/history/get"));
    }

    /**
     * 名人专家
     */
    public AllExpertInfo getExpert() {
        ArrayList<ExpertInfo> list = new ArrayList<>();
        Page<Expert> page = new Page<>(1, 3);
        Page<Expert> expertPage = expertMapper.selectPage(page, null);
        for (Expert expert : expertPage.getRecords()) {
            list.add(new ExpertInfo(expert, new SchemeDetail(expert.getResourceId())));
        }
        return new AllExpertInfo(BaseHomeType.EXPERT, list);
    }

    /**
     * 新闻头条
     */
    public AllNewsInfo getNews() {
        ArrayList<NewInfo> list = new ArrayList<>();
        Page<Resource> page = new Page<>(1, 4);
        Page<Resource> resourcePage = resourceMapper.selectPage(page, new QueryWrapper<Resource>()
                .eq("resource_show", "新闻"));
        for (Resource resource : resourcePage.getRecords()) {
            list.add(new NewInfo(resource, new SchemeDetail(resource.getResourceId())));
        }

        return new AllNewsInfo(BaseHomeType.NEW, list);
    }

    /**
     * 法律
     */
    public AllLawInfo getLaw() {
        Resource resource = resourceMapper.selectOne(new QueryWrapper<Resource>()
                .eq("resource_show", "法律"));
        LawInfo info = new LawInfo(resource);
        return new AllLawInfo(BaseHomeType.LAW, info);
    }

    /**
     * 四宫格
     */
    public AllGrid4Info getGrid4() {
        List<Resource> resources = resourceMapper.selectList(new QueryWrapper<Resource>()
                .eq("resource_show", "四宫格"));
        ArrayList<GridInfo> list = new ArrayList<>();
        for (Resource resource : resources) {
            list.add(new GridInfo(resource, new SchemeDetail(resource.getResourceId())));
        }
        return new AllGrid4Info(BaseHomeType.GRID4, list);
    }

    /**
     * 闪屏宫格
     */
    public AllFlashGridInfo getFlashInfo() {
        List<Resource> resources = resourceMapper.selectList(new QueryWrapper<Resource>()
                .eq("resource_show", "闪烁宫格"));
        ArrayList<FlashGridInfo> list = new ArrayList<>();
        for (Resource resource : resources) {
            list.add(new FlashGridInfo(resource, new SchemeVideo(resource.getResourceUrl())));
        }
        return new AllFlashGridInfo(BaseHomeType.Flash, list);
    }

    /**
     * 可变宫格
     */
    public AllGrid4Info getGridChange() {
        List<Resource> resources = resourceMapper.selectList(new QueryWrapper<Resource>()
                .eq("resource_show", "可变宫格"));
        ArrayList<GridInfo> list = new ArrayList<>();
        for (Resource resource : resources) {
            list.add(new GridInfo(resource, new SchemeDetail(resource.getResourceId())));
        }
        return new AllGrid4Info(BaseHomeType.GRID, list);
    }

    /**
     * 瀑布流
     */
    public AllFallInfo getFallInfo(Integer start,Integer pageSize) {
        Page<Resource> page = new Page<>(start, pageSize);
        Page<Resource> resourcePage = resourceMapper.selectPage(page, new QueryWrapper<Resource>()
                .eq("resource_label", "最新资讯")
                .or()
//                .eq("resource_label", "专业知识")
                .orderByDesc("id"));
        ArrayList<GridInfo> list = new ArrayList<>();
        for (Resource resource : resourcePage.getRecords()) {
//            System.out.println(resource);
            list.add(new GridInfo(resource, new SchemeDetail(resource.getResourceId())));
        }
        return new AllFallInfo(BaseHomeType.Fall, list,new MyPage(resourcePage));
    }
}
