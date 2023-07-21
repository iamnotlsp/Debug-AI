package com.lsp.pojo.resource.response;

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
public class ResourceDetail {

    private ResourceInfo resourceInfo;

    private List<String> photosUrl;

    private MyState myState;

    private List<Comment> comment;




}
