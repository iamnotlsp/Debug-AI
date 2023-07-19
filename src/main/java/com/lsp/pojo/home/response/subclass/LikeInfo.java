package com.lsp.pojo.home.response.subclass;

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
    private Integer resourceType;
    private String label;
    private String title;
    private String authorHead;
    private String author;
    private Integer reads;
    private Integer likes;
    private Scheme scheme;

    public LikeInfo(Resource resource) {
        this.resourceType = resource.getResourceType();
        this.label = resource.getResourceLabel();
        this.title = resource.getResourceTitle();
        this.authorHead = resource.getResourceAuthorHead();
        this.author = resource.getResourceAuthorName();
        this.reads = resource.getResourceReads();
        this.likes = resource.getResourceLikes();
    }
}
