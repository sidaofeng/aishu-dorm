package com.waken.dorm.common.entity.dorm;

import com.baomidou.mybatisplus.annotation.TableName;
import com.waken.dorm.common.base.BaseEntity;

import java.time.LocalDateTime;

/**
 * <p>
 * 宿舍床位
 * </p>
 *
 * @author zhaoRong
 * @since 2019-11-21
 */
@TableName("rm_dorm_bed")
public class DormBed extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 宿舍id
     */
    private String dormId;

    /**
     * 床位名称
     */
    private String name;

    /**
     * 床位号
     */
    private String code;

    /**
     * 入住人id（学生/教师/宿管/其他）
     */
    private String subjectId;

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

    /**
     * 备注
     */
    private String memo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public LocalDateTime getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(LocalDateTime lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getLastModifyUserId() {
        return lastModifyUserId;
    }

    public void setLastModifyUserId(String lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "DormBed{" +
                "id=" + id +
                ", dormId=" + dormId +
                ", name=" + name +
                ", code=" + code +
                ", subjectId=" + subjectId +
                ", createTime=" + createTime +
                ", createUserId=" + createUserId +
                ", lastModifyTime=" + lastModifyTime +
                ", lastModifyUserId=" + lastModifyUserId +
                ", memo=" + memo +
                "}";
    }
}
