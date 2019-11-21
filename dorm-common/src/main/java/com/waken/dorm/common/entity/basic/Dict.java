package com.waken.dorm.common.entity.basic;

import com.baomidou.mybatisplus.annotation.TableName;
import com.waken.dorm.common.base.BaseEntity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaoRong
 * @since 2019-11-21
 */
@Data
@TableName("rm_dict")
public class Dict extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 父节点id
     */
    private String parentId;

    /**
     * 字典数据名称
     */
    private String name;

    /**
     * 字典数据编码
     */
    private String code;

    /**
     * 是否默认 0：否 1：是
     */
    private Boolean isDefault;

    /**
     * 排序sort
     */
    private Integer sort;

    /**
     * 字典描述
     */
    private String desc;

    /**
     * 是否删除（0否，1是）
     */
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private String createUserId;

    /**
     * 最终修改时间
     */
    private LocalDateTime lastModifyTime;

    private String lastModifyUserId;
}
