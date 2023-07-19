package com.lsp.pojo.home.response;

import com.lsp.pojo.MyPage;
import com.lsp.pojo.home.response.subclass.CarouselInfo;
import com.lsp.pojo.home.response.subclass.ExpertInfo;
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
public class AllExpertInfo extends BaseHomeType{
    private Integer type;
    private List<ExpertInfo> experts;
    private MyPage myPage;

    public AllExpertInfo(Integer type, List<ExpertInfo> experts) {
        this.type = type;
        this.experts = experts;
    }
}
