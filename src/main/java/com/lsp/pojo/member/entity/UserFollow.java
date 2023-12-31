package com.lsp.pojo.member.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFollow implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;
    private String userPhone;
    private String followName;
    private String followDescribe;
    private String headPhoto;
    private String createTime;
    @TableField(update = "now()")
    private String updateTime;
    private Integer isDeleted;



}
