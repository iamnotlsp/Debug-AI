package com.lsp.pojo.resource.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceComment implements Serializable {
  private static final long serialVersionUID = 1L;

  private Integer id;
  private String userPhone;
  private Integer group_id;
  private Integer resourceId;
  private String comment;
  private String commentPhoto;
  private Integer commentLikes;
  private String createTime;


}
