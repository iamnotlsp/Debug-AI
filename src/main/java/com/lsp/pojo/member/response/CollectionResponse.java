package com.lsp.pojo.member.response;

import com.lsp.pojo.resource.entity.Resource;
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
public class CollectionResponse {
    private String userPhone;
    private Integer collectionNums;
    private List<ResourceInfo> list;
}
