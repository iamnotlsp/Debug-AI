package com.lsp.service.group;

import com.lsp.pojo.member.response.GroupForumResponse;

/**
 * @Author: LinShanPeng
 */
public interface GroupService {

    Integer getGroupNums();

    GroupForumResponse getGroupForum(Integer start, Integer pageSize);

}
