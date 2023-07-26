package com.lsp.service.resource;

import com.lsp.pojo.home.response.subclass.GridInfo;
import com.lsp.pojo.resource.entity.Resource;
import com.lsp.pojo.resource.request.CommentRequest;
import com.lsp.pojo.resource.response.Comment;
import com.lsp.pojo.resource.response.CommentResponse;
import com.lsp.pojo.resource.response.subclass.MyState;

import java.util.List;

/**
 * @Author: LinShanPeng
 */
public interface ResourceService {

    Resource getResourceById(Integer resourceId);

    void insertHistory(Integer resourceId);

    void addReads(Integer resourceId);

    List<String> getPhotoUrl(Integer resourceId);

    CommentResponse getComments(Integer resourceId, Integer start, Integer pageSize);

    MyState getMyState(Integer resourceId);

    boolean addLike(Integer resourceId);

    boolean cancelLike(Integer resourceId);

    boolean cancelCollection(Integer resourceId);

    boolean addCollection(Integer resourceId);

    List<GridInfo> getInfoByLabel(String label);

    CommentResponse postComment(Integer resourceId, CommentRequest request);
}
