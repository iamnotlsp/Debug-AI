package com.lsp.pojo.resource.response.subclass;

import com.lsp.pojo.resource.entity.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceInfo {
    private Integer resourceId;
    private Integer resourceType;
    private String resourceLabel;
    private String resourceTitle;
    private String resourceAuthorName;
    private String resourceAuthorHead;
    private String resourceContent;
    private Integer resourceReads;
    private Integer resourceSearch;
    private Integer resourceLikes;
    private Integer resourceComments;
    private String createTime;

    public ResourceInfo(Resource resource) {
        this.resourceId = resource.getResourceId();
        this.resourceType = resource.getResourceType();
        this.resourceLabel = resource.getResourceLabel();
        this.resourceTitle = resource.getResourceTitle();
        this.resourceAuthorName = resource.getResourceAuthorName();
        this.resourceAuthorHead = resource.getResourceAuthorHead();
        this.resourceContent = resource.getResourceContent();
        this.resourceReads = resource.getResourceReads();
        this.resourceSearch = resource.getResourceSearch();
        this.resourceLikes = resource.getResourceLikes();
        this.resourceComments = resource.getResourceComments();
        this.createTime = resource.getCreateTime();
    }
}
