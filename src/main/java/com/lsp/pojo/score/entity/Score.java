package com.lsp.pojo.score.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score implements Serializable {
  private static final long serialVersionUID = 1L;

  private Integer id;
  private String userPhone;
  private Integer groupId;
  private Integer score;

  public Score(String userPhone) {
    this.userPhone = userPhone;
  }

  public Score(String userPhone, Integer groupId) {
    this.userPhone = userPhone;
    this.groupId = groupId;
  }
}
