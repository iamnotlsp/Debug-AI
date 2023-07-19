package com.lsp.pojo.home.response.subclass;

import com.lsp.pojo.home.entity.Expert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpertInfo {
    private String name;
    private String describe;
    private String photo;
    private Scheme scheme;

    public ExpertInfo(Expert expert,Scheme scheme) {
        this.name = expert.getExpertName();
        this.describe = expert.getExpertDescribe();
        this.photo = expert.getExpertPhoto();
        this.scheme = scheme;
    }
}
