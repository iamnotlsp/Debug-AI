package com.lsp.controller;

import com.lsp.pojo.Result;
import com.lsp.pojo.resource.entity.Resource;
import com.lsp.service.resource.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LinShanPeng
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/getDetail")
    public Result<Resource> getSource(Integer resourceId){
        Resource resource = resourceService.getResourceById(resourceId);
        return Result.success("资源具体内容:",resource);
    }


}
