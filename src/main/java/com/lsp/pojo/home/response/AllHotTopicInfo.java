package com.lsp.pojo.home.response;

import com.lsp.pojo.home.response.subclass.HotTopicInfo;
import com.lsp.pojo.member.response.subclass.GroupForumInfo;
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
public class AllHotTopicInfo extends BaseHomeType{
    private Integer type;
    private List<HotTopicInfo> hotTopic;
}
