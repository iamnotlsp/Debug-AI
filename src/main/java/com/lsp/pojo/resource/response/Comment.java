package com.lsp.pojo.resource.response;

import com.lsp.pojo.resource.entity.ResourceComment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Integer groupId;
    private String userName;
    private String headPhoto;
    private String comment;
    private String commentPhoto;
    private Integer commentLikes;
    private String createTime;

    public Comment(ResourceComment comment) {
        this.groupId = comment.getGroup_id();
        this.comment = comment.getComment();
        this.commentPhoto = comment.getCommentPhoto();
        this.commentLikes = comment.getCommentLikes();
        this.createTime = comment.getCreateTime();
    }
}
