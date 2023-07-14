package com.lsp.pojo.home.response;

import lombok.Data;

/**
 * @Author: LinShanPeng
 */
@Data
public abstract class BaseHomeType {
    public static final int Carousel = 1; // 轮播图
    public static final int Like = 2; // 推荐内容
    public static final int TYPE3 = 3; // 视频
}
