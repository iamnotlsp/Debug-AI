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
public class MemberStudyEvent {
    private Integer eventId;
    private String eventName;
    private String eventTime;
    private Integer eventState;

}
