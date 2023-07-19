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
public class FlashGridInfo {
    private String title;
    private String author;
    private String authorHead;
    private String photo;
    private Integer readHot;
    private Scheme scheme;

    public FlashGridInfo(Resource resource, Scheme scheme) {
        this.title = resource.getResourceTitle();
        this.author = resource.getResourceAuthorName();
        this.authorHead = resource.getResourceAuthorHead();
        this.photo = resource.getResourcePhoto();
        this.readHot = resource.getResourceReads() * 10 + 5;
        this.scheme = scheme;
    }
}
