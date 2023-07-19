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
public class NewInfo {
    private String title;
    private String photo;
    private String author;
    private String authorHead;
    private Integer readHot;
    private Scheme scheme;

    public NewInfo(Resource resource, Scheme scheme) {
        this.title = resource.getResourceTitle();
        this.photo = resource.getResourcePhoto();
        this.author = resource.getResourceAuthorName();
        this.authorHead = resource.getResourceAuthorHead();
        this.readHot = resource.getResourceReads() * 10 + 5;
        this.scheme = scheme;
    }
}
