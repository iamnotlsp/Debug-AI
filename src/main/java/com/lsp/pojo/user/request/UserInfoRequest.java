package com.lsp.pojo.user.request;

import com.lsp.pojo.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: LinShanPeng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoRequest extends User{
    private String userPhone;
    private String userPassword;
    private String userName;
    private String headPhoto;
    private String location;
    private String realName;
    private Integer userAge;
    private String userSex;
    private String userLikes;
    private String userInfo;
    private String userWork;


}
