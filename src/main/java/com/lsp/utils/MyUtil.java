package com.lsp.utils;

import cn.dev33.satoken.stp.StpUtil;
import com.lsp.pojo.score.entity.ScoreDetail;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public static String getTodayDate(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        return currentDate.format(formatter);
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
