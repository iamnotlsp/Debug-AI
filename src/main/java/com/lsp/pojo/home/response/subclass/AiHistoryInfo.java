package com.lsp.pojo.home.response.subclass;

import com.lsp.pojo.home.entity.AiHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AiHistoryInfo {
    private String pointInTime;
    private String historyEvent;

    public AiHistoryInfo(AiHistory history) {
        this.pointInTime = history.getHistoryTime();
        this.historyEvent = history.getHistoryEvent();
    }
}
