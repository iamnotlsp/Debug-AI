package com.lsp.pojo.home.response;

import com.lsp.pojo.home.response.subclass.LastBrowseInfo;
import com.lsp.pojo.home.response.subclass.LawInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllLawInfo extends BaseHomeType{
    private Integer type;
    private LawInfo lawInfo;
}
