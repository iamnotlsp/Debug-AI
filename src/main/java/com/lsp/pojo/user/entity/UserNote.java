package com.lsp.pojo.user.entity;


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
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
  private Integer isDeleted;


}
