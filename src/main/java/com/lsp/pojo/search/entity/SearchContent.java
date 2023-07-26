package com.lsp.pojo.search.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchContent implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;
  private String searchType;
  private String searchShow;
  private String searchPhoto;
  private String searchTitle;
  private String searchShortText;
  private String searchContent;
  private String searchWay;



}
