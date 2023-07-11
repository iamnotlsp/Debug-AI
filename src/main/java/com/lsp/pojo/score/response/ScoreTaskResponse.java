package com.lsp.pojo.score.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreTaskResponse {
    private Integer ScoreSums;
    private Integer TodaySums;
    private String TogetherDays;
    private List<ScoreTaskDetail> taskDetails;
}
