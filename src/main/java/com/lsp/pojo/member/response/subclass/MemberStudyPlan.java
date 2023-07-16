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
public class MemberStudyPlan {
    private Integer planId;
    private String name;
    private String startTime;
    private String endTime;
    private Integer state;
    private List<MemberStudyEvent> studyEvent;
}
