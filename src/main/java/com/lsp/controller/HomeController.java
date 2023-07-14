package com.lsp.controller;

import com.lsp.pojo.Result;
import com.lsp.pojo.home.response.HomeAllResponse;
import com.lsp.pojo.resource.entity.Resource;
import com.lsp.service.home.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public Result<HomeAllResponse> getAll(){
        return Result.success("首页内容:",homeService.getAll());

    }
}
