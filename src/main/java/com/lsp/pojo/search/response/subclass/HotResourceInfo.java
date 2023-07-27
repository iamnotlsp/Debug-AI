package com.lsp.pojo.search.response.subclass;

import com.lsp.pojo.home.response.subclass.GridInfo;
import com.lsp.pojo.home.response.subclass.Scheme;
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
public class HotResourceInfo {
    private List<GridInfo> resourceInfos;
    private List<NoteInfo> noteInfos;
}
