package com.lsp.pojo.score.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreRankResponse {
    private Integer groupId;
    private String groupName;
    private Integer peopleNums;
    private List<GroupRank> groupRankList;

}
