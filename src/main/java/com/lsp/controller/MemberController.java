package com.lsp.controller;

import com.lsp.pojo.Result;
import com.lsp.pojo.member.request.PlanRequest;
import com.lsp.pojo.member.response.*;
import com.lsp.pojo.study.response.PlanResponse;
import com.lsp.pojo.user.entity.User;
import com.lsp.pojo.user.request.UserInfoRequest;
import com.lsp.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: LinShanPeng
 */
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping
    public Result<MemberMainResponse> getMain() {
        return Result.success("我的:", memberService.getMain());
    }

    @PostMapping("/info/post")
    public Result<User> finishInfo(@RequestBody UserInfoRequest request) {
        if (memberService.finishInfo(request)) {
            return Result.success("更改成功");
        } else {
            return Result.error("更改失败");
        }
    }

    @GetMapping("/info/get")
    public Result<MemberInfoResponse> getInfo() {
        return Result.success("我的信息详情:", memberService.getInfo());
    }

    @GetMapping("/collection/get")
    public Result<CollectionResponse> getCollections() {
        return Result.success("收藏:", memberService.getCollections());
    }

    @PostMapping("/collection/post")
    public Result<String> addCollection(Integer resourceId) {
        if (memberService.addCollection(resourceId)) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }


    @GetMapping("/follower/get")
    public Result<FollowResponse> getFollower() {
        return Result.success("关注:", memberService.getFollower());
    }


    @GetMapping("/note/get")
    public Result<NoteResponse> getNote(@RequestParam(defaultValue = "1") Integer start,
                                        @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.success("笔记:", memberService.getNote(start, pageSize));
    }

    @PostMapping("/note/post")
    public Result<String> addNote(Integer resourceId, String content) {
        if (memberService.addNote(resourceId, content)) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }

    @PostMapping("/note/update")
    public Result<String> updateNote(Integer id, String content) {
        if (memberService.updateNote(id, content)) {
            return Result.success("更改成功");
        } else {
            return Result.error("更改失败");
        }
    }

    @GetMapping("/history/get")
    public Result<HistoryResponse> getHistory(@RequestParam(defaultValue = "1") Integer start,
                                              @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.success("历史:", memberService.getHistory(start, pageSize));
    }

    @PostMapping("/plan/post")
    public Result<String> addPlan(String planName, String startTime, String endTime,
                                  @RequestBody PlanRequest planRequest) {
        Integer planId = memberService.addPlan(planName, startTime, endTime);
        if (planId == 0) {
            return Result.error("添加失败");
        } else if (memberService.addEvent(planRequest, planId)) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }

    @GetMapping("/plan/get")
    public Result<List<PlanResponse>> getPlan() {
        return Result.success("学习计划:", memberService.getPlan());
    }

    @GetMapping("/plan/event/get")
    public Result<List<MemberStudyEvent>> getEvent(Integer planId) {
        return Result.success("学习计划:", memberService.getEventByPlan(planId));
    }
}
