package com.lsp.controller;

import com.lsp.pojo.Result;
import com.lsp.pojo.home.response.AllAiHistoryInfo;
import com.lsp.pojo.home.response.AllExpertInfo;
import com.lsp.pojo.home.response.AllFallInfo;
import com.lsp.pojo.home.response.HomeAllResponse;
import com.lsp.pojo.resource.entity.Resource;
import com.lsp.service.home.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LinShanPeng
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping
    public Result<HomeAllResponse> getAll() {
        return Result.success("首页内容:", homeService.getAll());
    }

    @GetMapping("/history/get")
    public Result<AllAiHistoryInfo> getHistory() {
        return Result.success("历史更多内容:", homeService.getMoreHistory());
    }

    @GetMapping("/expert/get")
    public Result<AllExpertInfo> getExpertInfo(@RequestParam(defaultValue = "1") Integer start,
                                               @RequestParam(defaultValue = "4") Integer pageSize) {
        return Result.success("专家更多内容:", homeService.getMoreExpert(start, pageSize));
    }

    @GetMapping("/fall/get")
    public Result<AllFallInfo> getFallInfo(@RequestParam(defaultValue = "2") Integer start,
                                           @RequestParam(defaultValue = "6") Integer pageSize) {
        return Result.success("下拉瀑布流:",homeService.getFallInfo(start,pageSize));
    }
}
