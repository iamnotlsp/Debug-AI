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
public class ScoreTaskDetail {
    private String name;
    private String describe;
    private Integer nums;
    private Integer upperLimit;
}
