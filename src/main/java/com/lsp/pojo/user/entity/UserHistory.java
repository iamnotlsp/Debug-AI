package com.lsp.pojo.user.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserHistory implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;
  private String userPhone;
  private Integer resourceId;
  private LocalDateTime createTime;



}
