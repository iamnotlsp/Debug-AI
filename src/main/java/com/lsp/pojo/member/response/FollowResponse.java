package com.lsp.pojo.member.response;

import com.lsp.pojo.member.response.subclass.Followers;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
public class FollowResponse {
    private String userPhone;
    private Integer collectionNums;
    private List<Followers> list;
}
