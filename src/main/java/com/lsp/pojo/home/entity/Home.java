package com.lsp.pojo.home.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: LinShanPeng
 */
@TableName("home")
@Data
public class Home {
    private Integer id;

    private Integer type;

    private Integer resourceId;

    private String photo;

    private Integer size;

    private String title;

    private String subhead;

    private String h5url;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer isDeleted;
}
