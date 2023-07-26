package com.lsp.service.search.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsp.mapper.ResourceMapper;
import com.lsp.mapper.SearchHistoryMapper;
import com.lsp.mapper.SearchMapper;
import com.lsp.mapper.UserMapper;
import com.lsp.pojo.home.response.subclass.GridInfo;
import com.lsp.pojo.home.response.subclass.SchemeDetail;
import com.lsp.pojo.home.response.subclass.SchemeVideo;
import com.lsp.pojo.resource.entity.Resource;
import com.lsp.pojo.search.entity.SearchContent;
import com.lsp.pojo.search.entity.SearchHistory;
import com.lsp.pojo.search.response.SearchContentResponse;
import com.lsp.pojo.search.response.subclass.NoteInfo;
import com.lsp.pojo.search.response.subclass.QuestionInfo;
import com.lsp.pojo.search.response.subclass.WikiInfo;
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

    @Override
    public SearchContentResponse getSearchContent(String keyword) {
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

        //得到资讯
        QueryWrapper<Resource> wrapper1 = new QueryWrapper<Resource>()
                .like("resource_title", keyword);
        Page<Resource> page = new Page<>(1, 3);
        Page<Resource> resourcePage = resourceMapper.selectPage(page, wrapper1);
        ArrayList<GridInfo> gridList = new ArrayList<>();
        for (Resource resource : resourcePage.getRecords()) {
            if (resource.getResourceType() == 1) {
                gridList.add(new GridInfo(resource, new SchemeDetail(resource.getResourceId())));
            } else if (resource.getResourceType() == 3) {
                gridList.add(new GridInfo(resource, new SchemeVideo(resource.getResourceUrl())));
            }
        }

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
}
