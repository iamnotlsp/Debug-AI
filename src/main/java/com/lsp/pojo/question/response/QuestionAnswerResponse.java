package com.lsp.pojo.question.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAnswerResponse {
    private String situation;
    private String rightAnswer;
    private String parse;
}
