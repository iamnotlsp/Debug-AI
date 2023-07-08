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
public class HistoryResponse {
    private String userPhone;
    private Integer collectionNums;
    private MyPage myPage;
    private List<Histories> list;
}
