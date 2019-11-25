package com.waken.dorm.common.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.waken.dorm.common.annotation.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName BaseEntity
 * @Description TODO
 * @Author zhaoRong
 * @Date 2019/3/22 10:30
 **/
@Getter
@Setter
@ToString
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -8238093870975853177L;

    @Id
    private String id;

    @TableField("create_time")
    private Date createTime;

    @TableField("create_user_id")
    private String createUserId;

    @TableField("last_modify_time")
    private Date lastModifyTime;

    @TableField("last_modify_user_id")
    private String lastModifyUserId;
}
