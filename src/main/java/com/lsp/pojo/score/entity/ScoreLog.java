package com.lsp.pojo.score.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreLog implements Serializable {
  private static final long serialVersionUID = 1L;

  private Integer id;
  private String userPhone;
  private String scoreType;
  private Integer scoreAdd;
  private Integer scoreReduce;
  private Integer score;
  private LocalDateTime updateTime;


}
