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
public class MemberBrief {
    private String userPhone;
    private String userName;
    private String headPhoto;
    private String userLikes;
    private String userInfo;
    private String achievement;
    private String togetherDay;
}
