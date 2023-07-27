package com.lsp.pojo.search.response.subclass;

import com.lsp.pojo.home.response.subclass.Scheme;
import com.lsp.pojo.resource.entity.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LinShanPeng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotTopicInfo {
    private String resourceTitle;
    private Integer resourceReads;
    private Scheme scheme;

    public HotTopicInfo(Resource resource, Scheme scheme) {
        this.resourceTitle = resource.getResourceTitle();
        this.resourceReads = resource.getResourceReads();
        this.scheme = scheme;
    }
}
