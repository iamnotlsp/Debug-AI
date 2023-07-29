package com.lsp.service.search.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsp.mapper.*;
import com.lsp.pojo.home.response.subclass.GridInfo;
import com.lsp.pojo.home.response.subclass.SchemeDetail;
import com.lsp.pojo.home.response.subclass.SchemeSearch;
import com.lsp.pojo.home.response.subclass.SchemeVideo;
import com.lsp.pojo.resource.entity.Resource;
import com.lsp.pojo.resource.entity.ResourceShowMap;
import com.lsp.pojo.search.entity.SearchAssociational;
import com.lsp.pojo.search.entity.SearchContent;
import com.lsp.pojo.search.entity.SearchHistory;
import com.lsp.pojo.search.response.SearchAssociationalResponse;
import com.lsp.pojo.search.response.SearchContentResponse;
import com.lsp.pojo.search.response.SearchMainResponse;
import com.lsp.pojo.search.response.subclass.*;
import com.lsp.pojo.user.entity.User;
import com.lsp.service.search.SearchService;
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
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchMapper searchMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private SearchHistoryMapper historyMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ResourceShowMapMapper resourceShowMapper;

    @Autowired
    private SearchAssociationMapper searchAssociationMapper;

    /**
     * 获得搜索内容
     *
     * @param keyword
     * @return
     */
    @Override
    public SearchContentResponse getSearchContent(String keyword) {
        //得到资讯
        QueryWrapper<Resource> wrapper1 = new QueryWrapper<Resource>()
                .like("resource_title", keyword);
        Page<Resource> page = new Page<>(1, 3);
        Page<Resource> resourcePage = resourceMapper.selectPage(page, wrapper1);
        if (resourcePage.getRecords().size() == 0){
            return new SearchContentResponse();
        }
        ArrayList<GridInfo> gridList = new ArrayList<>();
        for (Resource resource : resourcePage.getRecords()) {
            if (resource.getResourceType() == 1) {
                gridList.add(new GridInfo(resource, new SchemeDetail(resource.getResourceId())));
            } else if (resource.getResourceType() == 3) {
                gridList.add(new GridInfo(resource, new SchemeVideo(resource.getResourceUrl())));
            }
        }


        //得到百科
        QueryWrapper<SearchContent> wrapper = new QueryWrapper<SearchContent>()
                .like("search_type", keyword);
        SearchContent wiki = searchMapper.selectOne(wrapper.eq("search_show", "百科"));
        if (wiki == null) {
            wrapper = new QueryWrapper<SearchContent>()
                    .like("search_type", "人工智能");
            keyword = "人工智能";
            wiki = searchMapper.selectOne(wrapper.eq("search_show", "百科"));
        }
        WikiInfo wikiInfo = new WikiInfo(wiki);



        //得到问题
        wrapper.clear();
        wrapper = new QueryWrapper<SearchContent>()
                .like("search_type", keyword);
        SearchContent question = searchMapper.selectOne(wrapper.eq("search_show", "问题"));
        QuestionInfo questionInfo = new QuestionInfo(question);

        //得到笔记
        wrapper.clear();
        wrapper = new QueryWrapper<SearchContent>()
                .like("search_type", keyword);
        List<SearchContent> searchNotes = searchMapper.selectList(wrapper.eq("search_show", "笔记"));
        ArrayList<NoteInfo> noteList = new ArrayList<>();
        for (SearchContent searchNote : searchNotes) {
            noteList.add(new NoteInfo(searchNote.getSearchPhoto()));
        }

        //记录到历史
        Integer loginId = MyUtil.getLoginId();
        User user = userMapper.selectById(loginId);
        SearchHistory history = new SearchHistory(user.getUserPhone(), keyword);
        historyMapper.insert(history);

        return new SearchContentResponse(wikiInfo, gridList, questionInfo, noteList);
    }

    /**
     * 得到搜索初始页
     *
     * @return
     */
    @Override
    public SearchMainResponse getSearchMain() {
        Integer loginId = MyUtil.getLoginId();
        User user = userMapper.selectById(loginId);

        //得到搜索历史记录
        QueryWrapper<SearchHistory> wrapper = new QueryWrapper<SearchHistory>()
                .eq("user_phone", user.getUserPhone())
                .orderByDesc("create_time");
        Page<SearchHistory> page = new Page<>(1, 8);
        Page<SearchHistory> historyPage = historyMapper.selectPage(page, wrapper);
        ArrayList<HistoryInfo> historyInfos = new ArrayList<>();
        for (SearchHistory history : historyPage.getRecords()) {
            historyInfos.add(new HistoryInfo(history.getKeyword()));
        }

        //得到热门资讯
        QueryWrapper<ResourceShowMap> wrapper1 = new QueryWrapper<ResourceShowMap>()
                .eq("resource_show", 3);
        List<ResourceShowMap> resources = resourceShowMapper.selectList(wrapper1);
        ArrayList<GridInfo> gridInfos = new ArrayList<>();
        for (ResourceShowMap resourceInfo : resources) {
            Resource resource = resourceMapper.selectById(resourceInfo.getResourceId());
            gridInfos.add(new GridInfo(resource, new SchemeDetail(resource.getResourceId())));
        }
        //笔记
        ArrayList<NoteInfo> noteInfos = new ArrayList<>();
        Page<SearchContent> page1 = new Page<>(1, 1);
        QueryWrapper<SearchContent> mathWrapper = new QueryWrapper<SearchContent>()
                .eq("search_show", "笔记")
                .eq("search_type", "数学");
        QueryWrapper<SearchContent> aiWrapper = new QueryWrapper<SearchContent>()
                .eq("search_show", "笔记")
                .eq("search_type", "人工智能");
        Page<SearchContent> mathPage = searchMapper.selectPage(page1, mathWrapper);
        List<SearchContent> mathContent = mathPage.getRecords();
        SearchContent aiContent = searchMapper.selectOne(aiWrapper);
        noteInfos.add(new NoteInfo(mathContent.get(0).getSearchPhoto()));
        noteInfos.add(new NoteInfo(aiContent.getSearchPhoto()));
        HotResourceInfo hotResourceInfo = new HotResourceInfo(gridInfos, noteInfos);

        //得到热门话题
        ArrayList<HotTopicInfo> hotTopicInfos = new ArrayList<>();
        QueryWrapper<Resource> wrapper2 = new QueryWrapper<Resource>()
                .orderByDesc("resource_reads");
        Page<Resource> page2 = new Page<>(1, 12);
        Page<Resource> resourcePage = resourceMapper.selectPage(page2, wrapper2);
        for (Resource resource : resourcePage.getRecords()) {
            hotTopicInfos.add(new HotTopicInfo(resource, new SchemeSearch(resource.getResourceTitle())));
        }


        return new SearchMainResponse(historyInfos, hotResourceInfo, hotTopicInfos);
    }

    /**
     * 关联词
     * @param keyword
     * @return
     */
    @Override
    public SearchAssociationalResponse getAssociationalWord(String keyword) {
//        QueryWrapper<SearchAssociational> wrapper = new QueryWrapper<SearchAssociational>()
//                .like("search_word", keyword);
//        ArrayList<String> associationalWords = new ArrayList<>();
//        List<SearchAssociational> searches = searchAssociationMapper.selectList(wrapper);
//        for (SearchAssociational search : searches) {
//            associationalWords.add(search.getAssociationalWord());
//        }

        ArrayList<String> associationalWords = new ArrayList<>();
        QueryWrapper<Resource> wrapper = new QueryWrapper<Resource>()
                .like("resource_title", keyword);
        Page<Resource> page = new Page<>(1, 10);
        Page<Resource> resourcePage = resourceMapper.selectPage(page, wrapper);
        for (Resource resource : resourcePage.getRecords()) {
            if (!resource.getResourceAssociation().isEmpty()) {
                associationalWords.add(resource.getResourceAssociation());
            }
        }

        return new SearchAssociationalResponse(associationalWords);
    }
}
