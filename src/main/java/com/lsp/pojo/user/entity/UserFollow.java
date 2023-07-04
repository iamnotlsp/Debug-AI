package com.lsp.pojo.user.entity;


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
    private String headPhone;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer isDeleted;



}
