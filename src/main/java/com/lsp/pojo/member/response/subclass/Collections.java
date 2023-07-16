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
public class Collections {
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
    private String createTime;
}
