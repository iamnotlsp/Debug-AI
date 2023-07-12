package com.lsp.pojo.score.response;

import com.lsp.pojo.MyPage;
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
public class ScoreDetailResponse {
    private String userPhone;
    private Integer sumScore;
    private String togetherDay;
    private MyPage myPage;
    private List<ScoreLog> list;
}
