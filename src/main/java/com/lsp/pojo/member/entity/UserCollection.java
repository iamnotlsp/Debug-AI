package com.lsp.pojo.member.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: LinShanPeng
 *  用户收藏表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCollection implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String userPhone;
    private Integer resourceId;
    private LocalDateTime createTime;

}
