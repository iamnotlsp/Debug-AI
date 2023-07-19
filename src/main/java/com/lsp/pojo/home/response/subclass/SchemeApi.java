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
public class SchemeApi  extends Scheme{
    private Integer type = 5;
    private String route = "调用接口";
    private String apiPath;

    public SchemeApi(String apiPath) {
        this.apiPath = apiPath;
    }
}
