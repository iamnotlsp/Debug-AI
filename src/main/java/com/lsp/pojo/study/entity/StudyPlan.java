package com.lsp.pojo.study.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyPlan implements Serializable {
  private static final long serialVersionUID = 1L;

  private Integer id;
  private String userPhone;
  private Integer planId;
  private String planName;
  private String planLabel;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
  private Integer isDeleted;



}
