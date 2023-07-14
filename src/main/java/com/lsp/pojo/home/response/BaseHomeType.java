package com.lsp.pojo.home.response;

import lombok.Data;

/**
 * @Author: LinShanPeng
 */
@Data
public abstract class BaseHomeType {
    public static final int TYPE1 = 1; // 轮播图
    public static final int TYPE2 = 2; // 短视频
    public static final int TYPE3 = 3; // 视频
}
