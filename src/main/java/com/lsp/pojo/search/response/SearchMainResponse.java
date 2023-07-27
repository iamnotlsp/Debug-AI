package com.lsp.pojo.search.response;

import com.lsp.pojo.search.response.subclass.HistoryInfo;
import com.lsp.pojo.search.response.subclass.HotResourceInfo;
import com.lsp.pojo.search.response.subclass.HotTopicInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: LinShanPeng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchMainResponse {
    private List<HistoryInfo> historyInfos;
    private HotResourceInfo hotResourceInfos;
    private List<HotTopicInfo> hotTopicInfos;
}
