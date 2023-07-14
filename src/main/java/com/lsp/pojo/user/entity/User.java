package com.lsp.pojo.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.lsp.pojo.user.request.UserInfoRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: LinShanPeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "User对象", description = "用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("手机账号")
    private String userPhone;

    @ApiModelProperty("密码")
    private String userPassword;

    @ApiModelProperty("昵称")
    private String userName;

    @ApiModelProperty("头像")
    private String headPhoto;

    @ApiModelProperty("归属地")
    private String location;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("年龄")
    private Integer userAge;

    @ApiModelProperty("性别")
    private String userSex;

    @ApiModelProperty("感兴趣的标签")
    private String userLikes;

    @ApiModelProperty("介绍")
    private String userInfo;

    @ApiModelProperty("群体id")
    private Integer groupId;

    @ApiModelProperty("成就称号")
    private String achievement;

    @ApiModelProperty("工作")
    private String work;

    private LocalDateTime createTime;

    @TableField(update = "now()")
    private LocalDateTime updateTime;

    private Integer isDeleted;

    public User(String userPhone, String userPassword) {
        this.userPhone = userPhone;
        this.userPassword = userPassword;
    }


    public void finishUserInfo(UserInfoRequest info) {
        this.userPhone = info.getUserPhone();
        this.userPassword = info.getUserPassword();
        this.userName = info.getUserName();
        this.headPhoto = info.getHeadPhoto();
        this.location = info.getLocation();
        this.realName = info.getRealName();
        this.userAge = info.getUserAge();
        this.userSex = info.getUserSex();
        this.userLikes = info.getUserLikes();
        this.userInfo = info.getUserInfo();
        this.work = info.getUserWork();
    }
}