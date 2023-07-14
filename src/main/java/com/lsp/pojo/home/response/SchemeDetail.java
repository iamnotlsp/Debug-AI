package com.lsp.pojo.home.response;

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
    private Integer type;
    private String route;
    private String resourceId;
}
