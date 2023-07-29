package com.lsp.pojo.question.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;
  private Integer questionType;
  private Integer questionShow;
  private String questionText;
  private String questionPhoto;
  private String optionA;
  private String optionB;
  private String optionC;
  private String optionD;
  private String parse;
  private String answer;



}
