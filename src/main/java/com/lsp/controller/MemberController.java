package com.lsp.controller;

import com.lsp.pojo.Result;
import com.lsp.pojo.member.response.*;
import com.lsp.pojo.user.entity.User;
import com.lsp.pojo.user.resquest.UserInfoRequest;
import com.lsp.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: LinShanPeng
 */
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping
    public Result<MemberMainResponse> getMain(){
        return Result.success("我的:",memberService.getMain());
    }

    @PostMapping("/finishInfo")
    public Result<User> finishInfo(@RequestBody UserInfoRequest request){
        if (memberService.finishInfo(request)){
            return Result.success("更改成功");
        }else {
            return Result.error("更改失败");
        }
    }

    @GetMapping("/getInfo")
    public Result<User> getInfo(){
        return memberService.getInfo();
    }

    @GetMapping("/getCollections")
    public Result<CollectionResponse> getCollections(){
        return Result.success("收藏:",memberService.getCollections());
    }

    @GetMapping("/getFollower")
    public Result<FollowResponse> getFollower(){
        return Result.success("关注:",memberService.getFollower());
    }

    @GetMapping("/getNote")
    public Result<NoteResponse> getNote(@RequestParam(defaultValue = "1") Integer start,
                                        @RequestParam(defaultValue = "10") Integer pageSize){
        return Result.success("笔记:",memberService.getNote(start,pageSize));
    }

    @GetMapping("/getHistory")
    public Result<HistoryResponse> getHistory(@RequestParam(defaultValue = "1") Integer start,
                                              @RequestParam(defaultValue = "10") Integer pageSize){
        return Result.success("历史:",memberService.getHistory(start,pageSize));
    }
}
