package com.lsp.pojo.member.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
public class Histories {
    private Integer resourceId;
    private Integer resourceType;
    private String resourceLabel;
    private String resourceTitle;
    private String resourceDescribe;

}
