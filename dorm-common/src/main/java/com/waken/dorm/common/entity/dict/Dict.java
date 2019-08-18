package com.waken.dorm.common.entity.dict;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
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
@TableName("rm_dict")
public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("pk_dict_id")
    private String pkDictId;

    /**
     * 键
     */
    @TableField("dict_key")
    private String dictKey;

    /**
     * 值
     */
    @TableField("dict_value")
    private String dictValue;

    /**
     * 列名
     */
    @TableField("column_name")
    private String columnName;

    /**
     * 列描述
     */
    @TableField("column_desc")
    private String columnDesc;

    /**
     * 表名称
     */
    @TableField("table_name")
    private String tableName;

    /**
     * 表描述
     */
    @TableField("table_desc")
    private String tableDesc;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 学校id
     */
    @TableField("school_id")
    private String schoolId;

    @TableField("create_time")
    private Date createTime;

    @TableField("create_user_id")
    private String createUserId;

    /**
     * 最后修改时间
     */
    @TableField("last_modify_time")
    private Date lastModifyTime;

    @TableField("last_modify_user_id")
    private String lastModifyUserId;
}
