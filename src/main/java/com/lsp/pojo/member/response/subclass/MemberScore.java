package com.lsp.pojo.member.response.subclass;

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
public class MemberScore {
    private Integer todayScore;
    private Integer sumsScore;
    private List<Day7Score> day7Score;
}
