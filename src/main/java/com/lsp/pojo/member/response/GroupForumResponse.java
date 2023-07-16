package com.lsp.pojo.member.response;

import com.lsp.pojo.member.response.subclass.GroupForumInfo;
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
public class GroupForumResponse {
    private List<GroupForumInfo> list;
}
