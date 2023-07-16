package com.lsp.pojo.home.response;

import com.lsp.pojo.home.response.subclass.AiHistoryInfo;
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
public class AllAiHistoryInfo extends BaseHomeType{
    private Integer type;
    private List<AiHistoryInfo> AiHistory;
}
