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
public class GridInfo {
    private String title;
    private String author;
    private String authorHead;
    private String content;
    private String photo;
    private Integer readHot;
    private Integer likes;
    private Scheme scheme;

    public GridInfo(Resource resource, Scheme scheme) {
        this.title = resource.getResourceTitle();
        this.author = resource.getResourceAuthorName();
        this.authorHead = resource.getResourceAuthorHead();
        if (resource.getResourceContent().length() < 30) {
            this.content = resource.getResourceContent();
        } else {
            this.content = resource.getResourceContent().substring(0, 30);
        }
        this.photo = resource.getResourcePhoto();
        this.readHot = resource.getResourceReads() * 10 + 5;
        this.likes = resource.getResourceLikes();
        this.scheme = scheme;
    }
}
