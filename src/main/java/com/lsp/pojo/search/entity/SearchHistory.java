package com.lsp.pojo.search.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String userPhone;
    private String keyword;
    private String createTime;

    public SearchHistory(String userPhone, String keyword) {
        this.userPhone = userPhone;
        this.keyword = keyword;
    }
}
