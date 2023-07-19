package com.lsp.pojo.home.response;

import com.lsp.pojo.MyPage;
import com.lsp.pojo.home.response.subclass.GridInfo;
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
public class AllFallInfo extends BaseHomeType {
    private Integer type;
    private List<GridInfo> fallInfos;
    private MyPage myPage;

    public AllFallInfo(Integer type, List<GridInfo> fallInfos) {
        this.type = type;
        this.fallInfos = fallInfos;
    }


}
