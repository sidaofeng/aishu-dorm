package com.waken.dorm.common.entity.user;

import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class User extends BaseEntity {
    private static final long serialVersionUID = 7187159566642237315L;
    private String userId;

    private String parentId;

    private String mobile;

    private String password;

    private String userName;

    private String name;

    private Integer gender;

    private String telPhone;

    private String identifyType;

    private String identifyNo;

    private String nickName;

    private Integer userType;

    private Integer status;

    private String imgUrl;

    private String identifyImgUrl;

    private String workImgUrl;

    private String email;

    private Date lastLoginTime;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;

    private Date auditTime;

    private String auditUserId;

    private String thirdPartQq;

    private String thirdPartWechat;

    private String clientId;

    private String platformType;

    private String channelId;

    private String easemobUid;

    private String qrCode;

    private Integer agentLevel;

    private Integer provinceId;

    private Integer cityId;

    private Integer areaId;

    private String memo;

    private String userToken;
}