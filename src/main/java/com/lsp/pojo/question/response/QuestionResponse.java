package com.lsp.pojo.question.response;
import com.lsp.pojo.question.response.subclass.QuestionInfo;
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
public class QuestionResponse {
    private List<QuestionInfo> questions;
    private Integer count;
}
