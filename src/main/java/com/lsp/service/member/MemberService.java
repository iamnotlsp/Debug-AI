package com.lsp.service.member;

import com.lsp.pojo.Result;
import com.lsp.pojo.member.response.CollectionResponse;
import com.lsp.pojo.user.entity.User;
import com.lsp.pojo.user.resquest.UserInfoRequest;

/**
 * @Author: LinShanPeng
 */
public interface MemberService {

    boolean finishInfo(UserInfoRequest request);

    Result<User> getInfo();

    CollectionResponse getCollections();
}
