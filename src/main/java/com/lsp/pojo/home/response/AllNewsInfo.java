package com.lsp.pojo.home.response;

import com.lsp.pojo.home.response.subclass.NewInfo;
import com.lsp.pojo.home.response.subclass.VoteInfo;
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
public class AllNewsInfo extends BaseHomeType{
    private Integer type;
    private List<NewInfo> newInfos;
}
