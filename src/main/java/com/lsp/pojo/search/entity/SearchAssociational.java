package com.lsp.pojo.search.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchAssociational implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;
  private String searchWord;
  private String associationalWord;



}
