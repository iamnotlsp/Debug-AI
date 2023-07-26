package com.lsp.service.search;

import com.lsp.pojo.search.response.SearchContentResponse;

/**
 * @Author: LinShanPeng
 */
public interface SearchService {
    SearchContentResponse getSearchContent(String keyword);
}
