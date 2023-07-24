package com.lsp.pojo.resource.response;

import com.lsp.pojo.resource.response.subclass.MyState;
import com.lsp.pojo.resource.response.subclass.ResourceInfo;
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
public class ResourceDetailResponse {

    private ResourceInfo resourceInfo;

    private List<String> photosUrl;

    private MyState myState;





}
