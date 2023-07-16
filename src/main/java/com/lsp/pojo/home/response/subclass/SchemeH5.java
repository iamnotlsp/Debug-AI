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
public class SchemeH5 extends Scheme{
    private Integer type;
    private String route;
    private String url;
}
