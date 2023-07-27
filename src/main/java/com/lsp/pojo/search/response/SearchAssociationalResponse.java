package com.lsp.pojo.search.response;

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
public class SearchAssociationalResponse {
    private List<String> associationalWord;
}
