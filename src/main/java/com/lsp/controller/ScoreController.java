package com.lsp.controller;

import com.lsp.pojo.Result;
import com.lsp.pojo.score.response.ScoreDetailResponse;
import com.lsp.pojo.score.response.ScoreRankResponse;
import com.lsp.pojo.score.response.ScoreTaskDetail;
import com.lsp.pojo.score.response.ScoreTaskResponse;
import com.lsp.service.score.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: LinShanPeng
 */
@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping("/getTask")
    public Result<ScoreTaskResponse> getTask() {
        return Result.success("获取积分任务:", scoreService.getScoreTask());
    }

    @PostMapping("/addArticle")
    public Result<Integer> addArticle() {
        Integer type = 1;
        return Result.success("添加成功,现总积分:", scoreService.addScore(type));
    }

    @PostMapping("/addView")
    public Result<Integer> addView() {
        Integer type = 2;
        return Result.success("添加成功,现总积分:", scoreService.addScore(type));
    }

    @PostMapping("/addAnswer")
    public Result<Integer> addAnswer() {
        Integer type = 3;
        return Result.success("添加成功,现总积分:", scoreService.addScore(type));
    }

    @PostMapping("/addPk")
    public Result<Integer> addPk() {
        Integer type = 4;
        return Result.success("添加成功,现总积分:", scoreService.addScore(type));
    }

    @PostMapping("/addAi")
    public Result<Integer> addAi() {
        Integer type = 5;
        return Result.success("添加成功,现总积分:", scoreService.addScore(type));
    }


    @PostMapping("/reduce")
    public Result<Integer> reduceScore(Integer reduce) {
        return Result.success("减少成功,现总积分:", scoreService.reduceScore(reduce));
    }

    @GetMapping("/getRank")
    public Result<ScoreRankResponse> getRank() {
        return Result.success("排名:", scoreService.getSumRank());
    }

    @GetMapping("/getDetail")
    public Result<ScoreDetailResponse> getDetailScore(@RequestParam(defaultValue = "1") Integer start,
                                                      @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.success("积分明细:", scoreService.getDetail(start, pageSize));
    }
}
