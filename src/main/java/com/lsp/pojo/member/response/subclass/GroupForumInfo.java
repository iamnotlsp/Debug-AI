package com.lsp.pojo.member.response.subclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupForumInfo {
    private String userName;
    private String headPhoto;
    private Integer resourceId;
    private String resourceTitle;
    private String resourcePhoto;
    private String comment;
    private String commentPhoto;
    private Integer resourceLikes;
    private Integer resourceComments;
    private String createTime;
}
