package com.lsp.pojo.member.response.subclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

/**
 * @Author: LinShanPeng
 */
@Data
@NoArgsConstructor
public class WeekReportInfo {
    private String studyTime = "8小时40分钟";//周学习时间
    private String dayStudyTime = "1小时20分钟";//日均学习时间
    private String studyTask = "完成";//本周学习任务
    private Integer noFinishPoint = 0;//未完成的点
    private Integer getScore;//本周获得的积分
    private Integer consumeScore;//本周消费的积分
    private Integer answerSum;//答题数目
    private String answerRight = "87%";//答题正确率
    private Integer readSum;//阅读数量
    private String videoTime = "4小时08分";//视频时长
    private String aiTime = "2小时16分";//ai时长
    private String aiStudyTime = "6小时30分";//学习ai时长

    public WeekReportInfo(Integer getScore, Integer consumeScore) {
        this.getScore = getScore;
        this.consumeScore = consumeScore;
        this.answerSum = new Random().nextInt(10) + 30;
        this.readSum = new Random().nextInt(20) + 50;
    }
}
