package com.lsp.pojo.home.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeAllResponse {
    private List<BaseHomeType> list;
}
