package com.lsp.service.score;

import com.lsp.pojo.score.response.ScoreDetailResponse;
import com.lsp.pojo.score.response.ScoreRankResponse;

/**
 * @Author: LinShanPeng
 */
public interface ScoreService {
    Integer getSumScore();

    Integer addScore(Integer add,Integer type);

    Integer reduceScore(Integer reduce);

    ScoreRankResponse getSumRank();

    ScoreDetailResponse getDetail();
}
