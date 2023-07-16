package com.lsp.pojo.home.response;

import lombok.Data;

/**
 * @Author: LinShanPeng
 */
@Data
public abstract class BaseHomeType {
    public static final int Carousel = 1; // 轮播图
    public static final int Like = 2; // 推荐内容
    public static final int HOT = 3; // 热点
    public static final int VOTE = 4; // 投票
    public static final int HISTORY = 5; // AI历史
}
