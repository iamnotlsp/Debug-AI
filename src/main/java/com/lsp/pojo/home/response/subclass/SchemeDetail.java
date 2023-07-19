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
public class SchemeDetail extends Scheme{
    private Integer type = 3;
    private String route = "详细资源";
    private Integer resourceId;

    public SchemeDetail(Integer resourceId) {
        this.resourceId = resourceId;
    }
}
