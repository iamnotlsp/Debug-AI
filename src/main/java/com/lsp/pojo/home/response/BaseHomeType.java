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
    public static final int EXPERT = 6; // 专家
    public static final int NEW = 7; // 新闻
    public static final int BROWSE = 8; // 上次浏览
    public static final int LAW = 9; // 法律
    public static final int GRID4 = 10; // 四宫格
    public static final int Flash = 11; // 闪烁宫格
    public static final int GRID = 12; // 可变宫格
    public static final int Fall = 13; // 可变宫格
}
