package com.lsp.pojo.home.response;

import com.lsp.pojo.resource.entity.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeInfo {
    private Integer resourceId;
    private Integer resourceType;
    private String Label;
    private String Title;
    private String describe;
    private Integer reads;
    private Integer likes;

    public LikeInfo(Resource resource) {
        this.resourceId = resource.getResourceId();
        this.resourceType = resource.getResourceType();
        Label = resource.getResourceLabel();
        Title = resource.getResourceTitle();
        this.describe = resource.getResourceDescribe();
        this.reads = resource.getResourceReads();
        this.likes = resource.getResourceLikes();
    }
}
