package com.lsp.pojo.question.response.subclass;

import com.lsp.pojo.question.entity.Question;
import com.lsp.pojo.resource.response.subclass.MyState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionInfo {
    private Integer id;
    private Integer questionType;
    private Integer questionShow;
    private String questionText;
    private String questionPhoto;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private Integer collectState;

    public QuestionInfo(Question question) {
        this.id = question.getId();
        this.questionType = question.getQuestionType();
        this.questionShow = question.getQuestionShow();
        this.questionText = question.getQuestionText();
        this.questionPhoto = question.getQuestionPhoto();
        this.optionA = question.getOptionA();
        this.optionB = question.getOptionB();
        this.optionC = question.getOptionC();
        this.optionD = question.getOptionD();
    }
}
