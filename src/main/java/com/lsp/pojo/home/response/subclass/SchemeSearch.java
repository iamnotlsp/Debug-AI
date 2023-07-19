package com.lsp.pojo.home.response.subclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchemeSearch extends Scheme{
    private Integer type = 2;
    private String route = "跳转搜索";
    private String keyword;

    public SchemeSearch(String keyword) {
        this.keyword = keyword;
    }
}
