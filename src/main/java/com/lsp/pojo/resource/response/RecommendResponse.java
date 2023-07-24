package com.lsp.pojo.resource.response;

import com.lsp.pojo.home.response.subclass.GridInfo;
import com.lsp.pojo.resource.response.subclass.RecommendInfo;
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
public class RecommendResponse {
    private List<GridInfo> recommendInfos;
}
