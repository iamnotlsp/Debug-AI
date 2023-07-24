package com.lsp.pojo.resource.response;

import com.lsp.pojo.MyPage;
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
public class CommentResponse {
    private MyPage myPage;
    private List<Comment> comment;
}
