package com.lsp.pojo.home.response;

import com.lsp.pojo.home.response.subclass.ExpertInfo;

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
public class AllGrid4Info extends BaseHomeType{
    private Integer type;
    private List<GridInfo> grid4Infos;
}
