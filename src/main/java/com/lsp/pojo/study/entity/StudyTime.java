package com.lsp.pojo.study.entity;


import java.io.Serializable;

public class StudyTime implements Serializable {
  private static final long serialVersionUID = 1L;

  private long id;
  private String userPhone;
  private long groupId;
  private String studyTime;


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


  public long getGroupId() {
    return groupId;
  }

  public void setGroupId(long groupId) {
    this.groupId = groupId;
  }


  public String getStudyTime() {
    return studyTime;
  }

  public void setStudyTime(String studyTime) {
    this.studyTime = studyTime;
  }

}
