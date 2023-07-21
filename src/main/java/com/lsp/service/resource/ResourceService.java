package com.lsp.service.resource;

import com.lsp.pojo.resource.entity.Resource;
import com.lsp.pojo.resource.response.Comment;
import com.lsp.pojo.resource.response.MyState;

import java.util.List;

/**
 * @Author: LinShanPeng
 */
public interface ResourceService {

    Resource getResourceById(Integer resourceId);

    void insertHistory(Integer resourceId);

    void addReads(Integer resourceId);

    List<String> getPhotoUrl(Integer resourceId);

    List<Comment> getComments(Integer resourceId);

    MyState getMyState(Integer resourceId);

    boolean addLike(Integer resourceId);

    boolean cancelLike(Integer resourceId);

    boolean cancelCollection(Integer resourceId);

    boolean addCollection(Integer resourceId);
}
