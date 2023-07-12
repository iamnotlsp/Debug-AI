package com.lsp.utils;

import cn.dev33.satoken.stp.StpUtil;
import com.lsp.mapper.UserMapper;
import com.lsp.pojo.score.entity.ScoreDetail;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

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

    OkHttpClient client = new OkHttpClient();

    public String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
