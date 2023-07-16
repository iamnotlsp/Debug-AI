package com.lsp.pojo.home.response.subclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteInfo {
    private String point;
    private Integer positiveSide;
    private Integer negativeSide;
}
