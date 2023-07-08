package com.lsp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPage {
    private long current; //当前第几页
    private long pages; //一共多少页
    private long size; //每页显示数
    private long total; //总数据
}
