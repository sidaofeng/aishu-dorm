package com.waken.dorm.common.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.waken.dorm.common.annotation.Id;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(hidden = true)
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(hidden = true)
    @TableField("create_user_id")
    private String createUserId;

    @ApiModelProperty(hidden = true)
    @TableField("last_modify_time")
    private Date lastModifyTime;

    @ApiModelProperty(hidden = true)
    @TableField("last_modify_user_id")
    private String lastModifyUserId;
}
