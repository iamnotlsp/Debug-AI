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
public class LawInfo {
    private String title;
    private String author;
    private String authorHead;
    private String content;

    public LawInfo(Resource resource) {
        this.title = resource.getResourceTitle();
        this.author = resource.getResourceAuthorName();
        this.authorHead = resource.getResourceAuthorHead();
        this.content = resource.getResourceContent();
    }
}
