package com.lsp.pojo.home.response.subclass;

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
public class HotTopicInfo {
    private Integer rankings;
    private Integer resourceId;
    private String resourceTitle;
    private String resourcePhoto;
    private Integer resourceLikes;
    private Integer resourceComments;
    private Scheme scheme;

    public HotTopicInfo(Resource resource) {
        this.resourceId = resource.getResourceId();
        this.resourceTitle = resource.getResourceTitle();
        this.resourcePhoto = resource.getResourcePhoto();
        this.resourceLikes = resource.getResourceLikes();
        this.resourceComments = resource.getResourceComments();
    }
}
