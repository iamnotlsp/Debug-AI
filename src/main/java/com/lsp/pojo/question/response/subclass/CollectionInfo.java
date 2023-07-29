package com.lsp.pojo.question.response.subclass;

import com.lsp.pojo.question.entity.Question;
import com.lsp.pojo.question.entity.QuestionCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionInfo {
    private Integer questionId;
    private Integer questionType;
    private String questionText;

    public CollectionInfo(Question question) {
        this.questionId = question.getId();
        this.questionType = question.getQuestionType();
        this.questionText = question.getQuestionText();
    }
}
