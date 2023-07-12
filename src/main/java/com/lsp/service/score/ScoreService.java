package com.lsp.service.score;

import com.lsp.pojo.score.response.ScoreDetailResponse;
import com.lsp.pojo.score.response.ScoreRankResponse;
import com.lsp.pojo.score.response.ScoreTaskDetail;
import com.lsp.pojo.score.response.ScoreTaskResponse;

/**
 * @Author: LinShanPeng
 */
public interface ScoreService {
    Integer getSumScore();

    Integer addScore(Integer type);

    Integer reduceScore(Integer reduce);

    ScoreRankResponse getSumRank();

    ScoreDetailResponse getDetail(Integer start, Integer pageSize);

    ScoreTaskResponse getScoreTask();
}
