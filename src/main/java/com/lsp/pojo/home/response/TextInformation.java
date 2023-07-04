package com.lsp.pojo.home.response;


import com.lsp.pojo.home.entity.Home;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextInformation extends BaseHomeType{
    private Home home;
}
