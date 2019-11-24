package com.waken.dorm.common.entity.dorm;

import com.baomidou.mybatisplus.annotation.TableName;
import com.waken.dorm.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * <p>
 * 校区基本数据表
 * </p>
 *
 * @author zhaoRong
 * @since 2019-11-21
 */
@Getter
@Setter
@ToString
@TableName("rm_school_campus")
public class SchoolCampus extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 校区基本数据id
     */
    private String id;

    /**
     * 校区号
     */
    private String code;

    /**
     * 校区名称
     */
    private String name;

    /**
     * 校区地址
     */
    private String address;

    /**
     * 校区邮政编码
     */
    private String postal;

    /**
     * 校区联系电话
     */
    private String tel;

    /**
     * 校区传真电话
     */
    private String fax;

    /**
     * 负责人id
     */
    private String leader;

    /**
     * 是否删除（0否，1是）
     */
    private Boolean isDeleted;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;

}
