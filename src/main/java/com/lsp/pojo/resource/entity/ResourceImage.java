package com.lsp.pojo.resource.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceImage implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer resourceId;
    private String photoUrl;
    private String createTime;


}
