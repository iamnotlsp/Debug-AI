package com.lsp.pojo.resource.response.subclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyState {
    private Integer likeState;//点赞状态，点赞了为1，没点为0
    private Integer collectState;//收藏状态，收藏了为1，没收藏为0
}
