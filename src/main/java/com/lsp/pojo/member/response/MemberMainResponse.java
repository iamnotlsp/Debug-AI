package com.lsp.pojo.member.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberMainResponse {
    private MemberBrief memberBrief;
    private Member4Part member4Part;
    private MemberScore memberScore;
    private MemberStudyPlan memberStudyPlan;
    private MemberGroup memberGroup;

}
