package com.lsp.controller;

import com.lsp.pojo.Result;
import com.lsp.pojo.user.entity.User;
import com.lsp.pojo.user.resquest.UserInfoRequest;
import com.lsp.service.member.MemberService;
import com.lsp.service.user.UserService;
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
}
