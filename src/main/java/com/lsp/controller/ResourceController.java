package com.lsp.controller;

import com.lsp.pojo.Result;
import com.lsp.pojo.home.response.subclass.GridInfo;
import com.lsp.pojo.resource.entity.Resource;
import com.lsp.pojo.resource.request.CommentRequest;
import com.lsp.pojo.resource.response.Comment;
import com.lsp.pojo.resource.response.CommentResponse;
import com.lsp.pojo.resource.response.RecommendResponse;
import com.lsp.pojo.resource.response.subclass.MyState;
import com.lsp.pojo.resource.response.ResourceDetailResponse;
import com.lsp.pojo.resource.response.subclass.ResourceInfo;
import com.lsp.service.member.MemberService;
import com.lsp.service.resource.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result<ResourceDetailResponse> getSource(Integer resourceId) {
        Resource resource = resourceService.getResourceById(resourceId);
        resourceService.insertHistory(resourceId);
        resourceService.addReads(resourceId);

        List<String> photosUrl = resourceService.getPhotoUrl(resourceId);
        MyState myState = resourceService.getMyState(resourceId);

        ResourceDetailResponse resourceDetail = new ResourceDetailResponse();
        resourceDetail.setPhotosUrl(photosUrl);
        resourceDetail.setResourceInfo(new ResourceInfo(resource));
        resourceDetail.setMyState(myState);

        return Result.success("资源具体内容:", resourceDetail);
    }

    @GetMapping("/comment/get")
    public Result<CommentResponse> getComment(Integer resourceId,
                                              @RequestParam(defaultValue = "1") Integer start,
                                              @RequestParam(defaultValue = "3") Integer pageSize) {
        CommentResponse comments = resourceService.getComments(resourceId, start, pageSize);

        return Result.success("评论内容:", comments);
    }

    @PostMapping("/comment/post")
    public Result<CommentResponse> postComment(Integer resourceId,
                                               @RequestBody CommentRequest request) {
        CommentResponse comments = resourceService.postComment(resourceId, request);
        return Result.success("评论内容:", comments);
    }

    @GetMapping("/recommend/get")
    public Result<RecommendResponse> getRecommend() {
        List<GridInfo> info = resourceService.getInfoByLabel("最新资讯");
        RecommendResponse recommend = new RecommendResponse(info);
        return Result.success("推荐资讯", recommend);
    }

    @PostMapping("/like/add")
    public Result<Integer> like(Integer resourceId) {
        if (resourceService.addLike(resourceId)) {
            Resource resource = resourceService.getResourceById(resourceId);
            return Result.success("点赞成功,当前点赞数:", resource.getResourceLikes());
        } else {
            return Result.error("点赞失败");
        }
    }

    @PostMapping("/like/cancel")
    public Result<Integer> cancelLike(Integer resourceId) {
        if (resourceService.cancelLike(resourceId)) {
            Resource resource = resourceService.getResourceById(resourceId);
            return Result.success("取消点赞,当前点赞数:", resource.getResourceLikes());
        } else {
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
