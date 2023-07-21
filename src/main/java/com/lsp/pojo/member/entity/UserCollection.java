package com.lsp.pojo.member.entity;



import com.baomidou.mybatisplus.annotation.TableLogic;
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
    private String createTime;
    @TableLogic(value = "0",delval = "1")
    private Integer isDeleted;


    public UserCollection(String userPhone, Integer resourceId) {
        this.userPhone = userPhone;
        this.resourceId = resourceId;
    }
}
