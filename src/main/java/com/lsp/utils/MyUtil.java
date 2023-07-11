package com.lsp.utils;

import cn.dev33.satoken.stp.StpUtil;
import com.lsp.mapper.UserMapper;
import com.lsp.pojo.score.entity.ScoreDetail;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: LinShanPeng
 */
public class MyUtil {

    public static Integer getLoginId() {
        Integer id = Integer.valueOf(String.valueOf(StpUtil.getLoginId()));
        return id;
    }

    public static Integer getTodaySum(ScoreDetail sd) {
        return sd.getLoginScore() + sd.getArticleScore() + sd.getViewScore()
                + sd.getAiScore() + sd.getPkScore() + sd.getAnswerScore();
    }
}
