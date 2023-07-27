package com.lsp.service.search;

import com.lsp.pojo.search.response.SearchAssociationalResponse;
import com.lsp.pojo.search.response.SearchContentResponse;
import com.lsp.pojo.search.response.SearchMainResponse;

/**
 * @Author: LinShanPeng
 */
public interface SearchService {
    SearchContentResponse getSearchContent(String keyword);

    SearchMainResponse getSearchMain();

    SearchAssociationalResponse getAssociationalWord(String keyword);
}
