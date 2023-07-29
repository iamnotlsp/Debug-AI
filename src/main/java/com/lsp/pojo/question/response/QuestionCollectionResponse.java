package com.lsp.pojo.question.response;

import com.lsp.pojo.question.response.subclass.CollectionInfo;
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
public class QuestionCollectionResponse {
    private int counts;
    private List<CollectionInfo> collections;
}
