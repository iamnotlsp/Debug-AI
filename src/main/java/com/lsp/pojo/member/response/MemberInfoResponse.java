package com.lsp.pojo.member.response;

import com.lsp.pojo.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoResponse {
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
    private Integer groupId;
    private String achievement;
    private String work;
    private String createTime;


    public MemberInfoResponse(User user) {
        this.userPhone = user.getUserPhone();
        this.userPassword = user.getUserPassword();
        this.userName = user.getUserName();
        this.headPhoto = user.getHeadPhoto();
        this.location = user.getLocation();
        this.realName = user.getRealName();
        this.userAge = user.getUserAge();
        this.userSex = user.getUserSex();
        this.userLikes = user.getUserLikes();
        this.userInfo = user.getUserInfo();
        this.groupId = user.getGroupId();
        this.achievement = user.getAchievement();
        this.work = user.getWork();
        this.createTime = user.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
