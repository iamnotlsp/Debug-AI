package com.lsp.pojo.home.response;

import com.lsp.pojo.home.response.subclass.LastBrowseInfo;
import com.lsp.pojo.home.response.subclass.LikeInfo;
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
public class AllLastBrowseInfo extends BaseHomeType {
    private Integer type;
    private LastBrowseInfo lastBrowseInfo;
}
