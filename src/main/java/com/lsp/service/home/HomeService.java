package com.lsp.service.home;

import com.lsp.pojo.home.response.AllAiHistoryInfo;
import com.lsp.pojo.home.response.AllExpertInfo;
import com.lsp.pojo.home.response.AllFallInfo;
import com.lsp.pojo.home.response.HomeAllResponse;

/**
 * @Author: LinShanPeng
 */

public interface HomeService {
    HomeAllResponse getAll();

    AllAiHistoryInfo getMoreHistory();

    AllExpertInfo getMoreExpert(Integer start,Integer pageSize);

    AllFallInfo getFallInfo(Integer start,Integer pageSize);
}
