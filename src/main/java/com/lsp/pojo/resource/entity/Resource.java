package com.lsp.pojo.resource.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resource implements Serializable {
  private static final long serialVersionUID = 1L;

  private Integer id;
  private Integer resourceId;
  private Integer resourceType;
  private String resourceLabel;
  private String resourceTitle;
  private String resourceDescribe;
  private String resourceContent;
  private String resourcePhoto;
  private Integer resourceReads;
  private Integer resourceSearch;
  private Integer resourceLikes;
  private Integer resourceComments;
  private String createTime;
  private String updateTime;
  private long isDeleted;


}
