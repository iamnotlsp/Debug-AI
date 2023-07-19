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
public class LastBrowseInfo {
    private String resourceTitle;
    private Scheme scheme;
}
