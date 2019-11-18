package com.waken.dorm.common.entity.log;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.waken.dorm.common.annotation.Id;
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
@TableName("rm_log")
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @Id
    @TableId("pk_log_id")
    private String pkLogId;

    /**
     * 操作用户id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 操作内容
     */
    @TableField("operation_content")
    private String operationContent;

    /**
     * 耗时
     */
    private Integer duration;

    /**
     * 操作方法
     */
    private String method;

    /**
     * 方法参数
     */
    private String params;

    /**
     * 操作者IP
     */
    private String ip;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 操作地点
     */
    private String location;
}
