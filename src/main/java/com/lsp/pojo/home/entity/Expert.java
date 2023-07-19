package com.lsp.pojo.home.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expert implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer resourceId;
    private String expertName;
    private String expertDescribe;
    private String expertPhoto;


}
