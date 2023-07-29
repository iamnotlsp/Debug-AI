package com.lsp.service.question;

import com.lsp.pojo.question.response.QuestionAnswerResponse;
import com.lsp.pojo.question.response.QuestionCollectionResponse;
import com.lsp.pojo.question.response.QuestionResponse;

/**
 * @Author: LinShanPeng
 */
public interface QuestionService {
    QuestionResponse getQuestion(int count);

    QuestionAnswerResponse answerQuestion(int questionId, String answer);

    boolean postCollection(int questionId);

    boolean deleteCollection(int questionId);

    QuestionCollectionResponse getCollections();

    QuestionCollectionResponse getMistakes();

    boolean deleteMistake(int questionId);
}
