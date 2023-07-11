package com.lsp.pojo.member.response;

import com.lsp.pojo.MyPage;
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
public class NoteResponse {
    private String userPhone;
    private Integer NoteNums;
    private MyPage myPage;
    private List<Notes> list;

    @Override
    public String toString() {
        return "NoteResponse{" +
                "userPhone='" + userPhone + '\'' +
                ", NoteNums=" + NoteNums +
                ", myPage=" + myPage +
                ", list=" + list +
                '}';
    }
}
