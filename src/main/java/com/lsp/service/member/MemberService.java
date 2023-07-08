package com.lsp.service.member;

import com.lsp.pojo.Result;
import com.lsp.pojo.member.response.*;
import com.lsp.pojo.user.entity.User;
import com.lsp.pojo.user.resquest.UserInfoRequest;

/**
 * @Author: LinShanPeng
 */
public interface MemberService {

    boolean finishInfo(UserInfoRequest request);

    Result<User> getInfo();

    CollectionResponse getCollections();

    FollowResponse getFollower();

    NoteResponse getNote(Integer start, Integer pageSize);

    HistoryResponse getHistory(Integer start, Integer pageSize);

    MemberMainResponse getMain();
}
