package com.lsp.pojo.search.response;

import com.lsp.pojo.home.response.subclass.GridInfo;
import com.lsp.pojo.search.response.subclass.NoteInfo;
import com.lsp.pojo.search.response.subclass.QuestionInfo;
import com.lsp.pojo.search.response.subclass.WikiInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: LinShanPeng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchContentResponse {
    private WikiInfo wikiInfo;
    private List<GridInfo> gridInfos;
    private QuestionInfo questionInfo;
    private List<NoteInfo> noteInfos;
}
