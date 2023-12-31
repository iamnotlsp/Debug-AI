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
public class SchemeH5 extends Scheme {
    private Integer type = 1;
    private String route = "跳转链接";
    private String url;

    public SchemeH5(String url) {
        this.url = url;
    }
}
