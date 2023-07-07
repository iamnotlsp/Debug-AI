package com.lsp.controller;

import com.lsp.pojo.Result;
import com.lsp.pojo.score.response.ScoreDetailResponse;
import com.lsp.pojo.score.response.ScoreRankResponse;
import com.lsp.service.score.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LinShanPeng
 */
@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping("/getSumScore")
    public Result<Integer> getSumScore() {
        return Result.success("总积分:", scoreService.getSumScore());
    }

    @PostMapping("/addScore")
    public Result<Integer> addScore(Integer add,Integer type){
        return Result.success("添加成功,现总积分:", scoreService.addScore(add,type));
    }

    @PostMapping("/reduceScore")
    public Result<Integer> reduceScore(Integer reduce){
        return Result.success("减少成功,现总积分:", scoreService.reduceScore(reduce));
    }

    @GetMapping("/getRank")
    public Result<ScoreRankResponse> getRank(){
        return Result.success("排名:",scoreService.getSumRank());
    }

    @GetMapping("/getDetailScore")
    public Result<ScoreDetailResponse> getDetailScore(){
        return Result.success("积分明细:",scoreService.getDetail());
    }
}
