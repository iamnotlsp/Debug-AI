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
    private String title;
    private String photo;
    private Integer likes;
    private Integer comments;
    private Scheme scheme;

    public HotTopicInfo(Resource resource) {
        this.title = resource.getResourceTitle();
        this.photo = resource.getResourcePhoto();
        this.likes = resource.getResourceLikes();
        this.comments = resource.getResourceComments();
    }
}
