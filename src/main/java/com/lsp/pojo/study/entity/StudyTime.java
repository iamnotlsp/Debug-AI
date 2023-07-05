package com.lsp.pojo.study.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyTime implements Serializable {
  private static final long serialVersionUID = 1L;

  private Integer id;
  private String userPhone;
  private Integer groupId;
  private String studyTime;


}
