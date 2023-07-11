package com.lsp.pojo.study.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanResponse {
    private Integer planId;
    private String planName;
    private String startTime;
    private String endTime;
    private Integer planState;
    private String createTime;
}
