package com.lsp.service.home.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsp.mapper.ResourceMapper;
import com.lsp.mapper.ResourceShowMapMapper;
import com.lsp.mapper.UserMapper;
import com.lsp.pojo.home.response.*;
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

    @Override
    public HomeAllResponse getAll() {
        ArrayList<BaseHomeType> list = new ArrayList<>();


        list.add(getCarousel());
        list.add(getLikes());
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
            SchemeH5 h5 = new SchemeH5(1, "H5", resource.getResourceUrl());
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
            list.add(likeInfo);
        }

        return new AllLikeInfo(BaseHomeType.Like, list);
    }
    /**
     *    热门话题
     */
}
