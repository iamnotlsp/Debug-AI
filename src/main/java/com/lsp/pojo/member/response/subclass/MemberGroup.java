package com.lsp.pojo.member.response.subclass;

import com.lsp.pojo.score.response.GroupRank;
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
public class MemberGroup {
    private Integer groupId;
    private String groupName;
    private Integer peopleNums;
    private List<GroupRank> groupRankList;
}
