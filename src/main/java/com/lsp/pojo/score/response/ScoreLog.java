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
public class ScoreLog {
    private Integer todayGetSum;
    private List<ScoreTask> task;
    private String createTime;
}
