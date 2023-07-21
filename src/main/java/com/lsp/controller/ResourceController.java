package com.lsp.controller;

import com.lsp.pojo.Result;
import com.lsp.pojo.resource.entity.Resource;
import com.lsp.pojo.resource.response.Comment;
import com.lsp.pojo.resource.response.MyState;
import com.lsp.pojo.resource.response.ResourceDetail;
import com.lsp.pojo.resource.response.ResourceInfo;
import com.lsp.service.member.MemberService;
import com.lsp.service.resource.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LinShanPeng
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/getDetail")
    public Result<ResourceDetail> getSource(Integer resourceId) {
        Resource resource = resourceService.getResourceById(resourceId);
        resourceService.insertHistory(resourceId);
        resourceService.addReads(resourceId);

        List<String> photosUrl = resourceService.getPhotoUrl(resourceId);
        List<Comment> comments = resourceService.getComments(resourceId);
        MyState myState = resourceService.getMyState(resourceId);

        ResourceDetail resourceDetail = new ResourceDetail();
        resourceDetail.setPhotosUrl(photosUrl);
        resourceDetail.setComment(comments);
        resourceDetail.setResourceInfo(new ResourceInfo(resource));
        resourceDetail.setMyState(myState);

        return Result.success("资源具体内容:", resourceDetail);
    }

    @PostMapping("/like/add")
    public Result<Integer> like(Integer resourceId) {
        if (resourceService.addLike(resourceId)) {
            Resource resource = resourceService.getResourceById(resourceId);
            return Result.success("点赞成功,当前点赞数:", resource.getResourceLikes());
        }else {
            return Result.error("点赞失败");
        }
    }

    @PostMapping("/like/cancel")
    public Result<Integer> cancelLike(Integer resourceId) {
        if (resourceService.cancelLike(resourceId)) {
            Resource resource = resourceService.getResourceById(resourceId);
            return Result.success("取消点赞,当前点赞数:", resource.getResourceLikes());
        }else {
            return Result.error("取消失败");
        }
    }

    @PostMapping("/collection/add")
    public Result<String> addCollection(Integer resourceId) {
        if (resourceService.addCollection(resourceId)) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }

    @PostMapping("/collection/cancel")
    public Result<String> cancelCollect(Integer resourceId) {
        if (resourceService.cancelCollection(resourceId)) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }


}
