package com.lsp.pojo.member.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Followers {
    private String followerName;
    private String followerDescribe;
    private String headPhoto;
    private String createTime;
}
