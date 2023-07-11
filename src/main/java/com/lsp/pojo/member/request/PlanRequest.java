package com.lsp.pojo.member.request;

import com.lsp.pojo.member.response.PlanPost;
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
public class PlanRequest {
    private List<PlanPost> planPosts;
}
