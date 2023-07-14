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
public class CarouselInfo {
    private String photoUrl;
    private String title;
    private Scheme scheme;
}
