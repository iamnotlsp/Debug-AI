package com.lsp.pojo.member.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
public class Notes {
    private Integer resourceId;
    private String noteContent;
    private String createTime;
    private String updateTime;
}
