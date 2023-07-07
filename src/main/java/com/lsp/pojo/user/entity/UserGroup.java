package com.lsp.pojo.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer groupId;
    private Integer groupNums;
    private String groupName;


}
