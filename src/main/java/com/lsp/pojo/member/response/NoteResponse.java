package com.lsp.pojo.member.response;

import com.lsp.pojo.MyPage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
public class NoteResponse {
    private String userPhone;
    private Integer NoteNums;
    private MyPage myPage;
    private List<Notes> list;
}
