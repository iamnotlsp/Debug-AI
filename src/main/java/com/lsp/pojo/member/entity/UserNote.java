package com.lsp.pojo.member.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNote implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;
  private String userPhone;
  private Integer resourceId;
  private String noteContent;
  private String createTime;
  @TableField(update = "now()")
  private String updateTime;
  @TableLogic(value = "0",delval = "1")
  private Integer isDeleted;


}
