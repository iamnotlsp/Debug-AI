package com.lsp.pojo.score.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreLog {
    private Integer TodayGetScore;
    private Integer loginScore;
    private Integer articleScore;
    private Integer viewScore;
    private Integer answerScore;
    private Integer pkScore;
    private Integer aiScore;
    private Integer expenseScore;
    private String createTime;
}
