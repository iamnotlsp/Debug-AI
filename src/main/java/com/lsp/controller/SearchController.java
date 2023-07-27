package com.lsp.controller;

import com.lsp.pojo.Result;
import com.lsp.pojo.search.response.SearchAssociationalResponse;
import com.lsp.pojo.search.response.SearchContentResponse;
import com.lsp.pojo.search.response.SearchMainResponse;
import com.lsp.service.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LinShanPeng
 */
@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping("/content/get")
    public Result<SearchContentResponse> getSearchContent(String keyword) {
        return Result.success("搜索页面内容:", searchService.getSearchContent(keyword));
    }

    @GetMapping
    public Result<SearchMainResponse> getSearchMain() {
        return Result.success("搜索初始页:", searchService.getSearchMain());
    }

    @GetMapping("/content")
    public Result<SearchAssociationalResponse> getAssociationalWord(String keyword) {
        return Result.success("搜索关联词:",searchService.getAssociationalWord(keyword));
    }
}
