package com.waken.dorm.common.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaoRong
 * @since 2019-08-05
 */
@Getter
@Setter
@ToString
@TableName("rm_user")
@Excel("宿舍管理系统人员信息统计表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId("user_id")
    private String userId;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    @ExcelField(value = "用户名")
    @TableField("user_name")
    private String userName;

    /**
     * 手机号码
     */
    @ExcelField(value = "手机号")
    private String mobile;

    /**
     * 邮箱
     */
    @ExcelField(value = "邮箱")
    private String email;

    /**
     * 姓名
     */
    private String name;

    /**
     * 姓名 1-男，2-女
     */
    @ExcelField(value = "性别", writeConverterExp = "1=男,2=女,3=保密")
    private Integer gender;

    /**
     * 座机号码
     */
    @TableField("tel_phone")
    private String telPhone;

    /**
     * 证件类型 1-身份证,2-学生证
     */
    @ExcelField(value = "证件类型", writeConverterExp = "1=身份证,2=学生证")
    @TableField("identify_type")
    private String identifyType;

    /**
     * 证件号码
     */
    @ExcelField(value = "证件号码")
    @TableField("identify_no")
    private String identifyNo;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 用户类型(1-系统管理员，2-宿管管理员，3-辅导员管理员,4-学生处管理员，5-学生)
     */
    @ExcelField(value = "用户类型", writeConverterExp = "1=系统管理员,2=宿管管理员,3=辅导员管理员,4=学生处管理员,5=学生")
    @TableField("user_type")
    private Integer userType;

    /**
     * 用户状态(-1已删除，0禁用，1启用)
     */
    @ExcelField(value = "用户状态", writeConverterExp = "-1=已删除,0=禁用,1=启用")
    private Integer status;

    /**
     * 头像
     */
    @TableField("img_url")
    private String imgUrl;

    /**
     * 证件照片
     */
    @TableField("identify_img_url")
    private String identifyImgUrl;

    /**
     * 工作照
     */
    @TableField("work_img_url")
    private String workImgUrl;


    /**
     * 上次登录时间
     */
    @TableField("last_login_time")
    private Date lastLoginTime;

    @TableField("create_time")
    private Date createTime;

    @TableField("create_user_id")
    private String createUserId;

    @TableField("last_modify_time")
    private Date lastModifyTime;

    @TableField("last_modify_user_id")
    private String lastModifyUserId;

    /**
     * 审核时间
     */
    @TableField("audit_time")
    private Date auditTime;

    /**
     * 审核人
     */
    @TableField("audit_user_id")
    private String auditUserId;

    /**
     * qq登录
     */
    @TableField("third_part_qq")
    private String thirdPartQq;

    /**
     * 微信登录
     */
    @TableField("third_part_wechat")
    private String thirdPartWechat;

    /**
     * 登录手机地址
     */
    @TableField("client_id")
    private String clientId;

    /**
     * 登录手机类型： 1 安卓 ；2 IOS
     */
    @TableField("platform_type")
    private String platformType;

    /**
     * 推送消息Id
     */
    @TableField("channel_id")
    private String channelId;

    /**
     * 环信uuid
     */
    @TableField("easemob_uid")
    private String easemobUid;

    /**
     * 二维码
     */
    @TableField("qr_code")
    private String qrCode;

    /**
     * 代理级别（1-省代，2-市代，3-区代）
     */
    @TableField("agent_level")
    private Integer agentLevel;

    /**
     * 省级ID
     */
    @TableField("province_id")
    private Integer provinceId;

    /**
     * 市级id
     */
    @TableField("city_id")
    private Integer cityId;

    /**
     * 区域id
     */
    @TableField("area_id")
    private Integer areaId;

    /**
     * 备注
     */
    @ExcelField(value = "备注")
    private String memo;

    /**
     * 在线用户的唯一id
     */
    @TableField(exist = false)
    private String activeUserId;
}
