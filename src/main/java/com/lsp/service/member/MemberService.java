package com.lsp.service.member;

import com.lsp.pojo.member.request.PlanRequest;
import com.lsp.pojo.member.response.*;
import com.lsp.pojo.study.response.PlanResponse;
import com.lsp.pojo.user.request.UserInfoRequest;

import java.util.List;

/**
 * @Author: LinShanPeng
 */
public interface MemberService {

    boolean finishInfo(UserInfoRequest request);

    MemberInfoResponse getInfo();

    CollectionResponse getCollections();

    FollowResponse getFollower();

    NoteResponse getNote(Integer start, Integer pageSize);

    HistoryResponse getHistory(Integer start, Integer pageSize);

    MemberMainResponse getMain();

    boolean addCollection(Integer resourceId);


    boolean addNote(Integer resourceId, String content);

    boolean updateNote(Integer id, String content);

    boolean addEvent(PlanRequest planRequest, Integer planId);

    Integer addPlan(String planName, String startTime, String endTime);

    List<PlanResponse> getPlan();

    List<MemberStudyEvent> getEventByPlan(Integer planId);

    boolean deletePlanById(Integer planId);

    boolean deleteNoteById(Integer noteId);
}
