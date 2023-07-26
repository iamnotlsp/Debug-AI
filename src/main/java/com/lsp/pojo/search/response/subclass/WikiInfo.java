package com.lsp.pojo.search.response.subclass;

import com.lsp.pojo.home.response.subclass.Scheme;
import com.lsp.pojo.search.entity.SearchContent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LinShanPeng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WikiInfo {
    private String Title;
    private String ShortText;

    public WikiInfo(SearchContent content) {
        Title = content.getSearchTitle();
        ShortText = content.getSearchShortText();
    }
}
