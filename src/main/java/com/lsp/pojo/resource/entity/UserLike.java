package com.lsp.pojo.resource.entity;


import com.baomidou.mybatisplus.annotation.TableLogic;
import com.lsp.pojo.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLike implements Serializable {

  private Integer id;
  private String userPhone;
  private Integer resourceId;
  private String createTime;
  @TableLogic(value = "0",delval = "1")
  private Integer isDeleted;

  public UserLike(String userPhone, Integer resourceId) {
    this.userPhone = userPhone;
    this.resourceId = resourceId;
  }
}
