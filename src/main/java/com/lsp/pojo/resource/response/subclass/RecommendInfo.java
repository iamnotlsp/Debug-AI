package com.lsp.pojo.resource.response.subclass;

import com.lsp.pojo.home.response.subclass.GridInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendInfo {
    private GridInfo recommend;
}
