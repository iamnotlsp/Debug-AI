package com.lsp.pojo.resource.entity;
;


public class ResourceComment {
  private static final long serialVersionUID = 1L;

  private long id;
  private String userPhone;
  private String userName;
  private long resourceId;
  private String comment;
  private long commentLikes;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUserPhone() {
    return userPhone;
  }

  public void setUserPhone(String userPhone) {
    this.userPhone = userPhone;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public long getResourceId() {
    return resourceId;
  }

  public void setResourceId(long resourceId) {
    this.resourceId = resourceId;
  }


  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }


  public long getCommentLikes() {
    return commentLikes;
  }

  public void setCommentLikes(long commentLikes) {
    this.commentLikes = commentLikes;
  }

}
