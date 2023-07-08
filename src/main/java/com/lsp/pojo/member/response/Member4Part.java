package com.lsp.pojo.member.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member4Part {
    private Integer collectionsNum;
    private Integer followersNum;
    private Integer historiesNum;
    private Integer notesNum;

}
