package com.lsp.controller;

import com.lsp.pojo.Result;
import com.lsp.pojo.question.response.QuestionAnswerResponse;
import com.lsp.pojo.question.response.QuestionCollectionResponse;
import com.lsp.pojo.question.response.QuestionResponse;
import com.lsp.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: LinShanPeng
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/get")
    public Result<QuestionResponse> getQuestion(@RequestParam(defaultValue = "5") int count) {
        return Result.success("问题:", questionService.getQuestion(count));
    }

    @GetMapping("/answer")
    public Result<QuestionAnswerResponse> answerQuestion(int questionId, String answer) {
        return Result.success("回答情况:", questionService.answerQuestion(questionId, answer));
    }

    @PostMapping("/collection/post")
    public Result<String> postCollection(int questionId) {
        if (questionService.postCollection(questionId)) {
            return Result.success("收藏成功");
        } else {
            return Result.error("收藏失败,已经收藏");
        }
    }

    @PostMapping("/collection/delete")
    public Result<String> deleteCollection(int questionId) {
        if (questionService.deleteCollection(questionId)){
            return Result.success("取消收藏");
        }else {
            return Result.error("取消失败");
        }
    }

    @GetMapping("/collection/get")
    public Result<QuestionCollectionResponse> getCollections(){
        return Result.success("收藏列表:",questionService.getCollections());
    }

    @GetMapping("/mistake/get")
    public Result<QuestionCollectionResponse> getMistakes(){
        return Result.success("收藏列表:",questionService.getMistakes());
    }

    @PostMapping("/mistake/delete")
    public Result<String> deleteMistake(int questionId) {
        if (questionService.deleteMistake(questionId)){
            return Result.success("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }
}
