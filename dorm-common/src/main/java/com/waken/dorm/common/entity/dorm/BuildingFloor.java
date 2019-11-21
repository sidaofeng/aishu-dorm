package com.waken.dorm.common.entity.dorm;

import com.baomidou.mybatisplus.annotation.TableName;
import com.waken.dorm.common.base.BaseEntity;

import java.time.LocalDateTime;

/**
 * <p>
 * 建筑物楼层
 * </p>
 *
 * @author zhaoRong
 * @since 2019-11-21
 */
@TableName("rm_building_floor")
public class BuildingFloor extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 建筑物ID
     */
    private String buildingId;

    /**
     * 名称
     */
    private String name;

    /**
     * 楼层
     */
    private String code;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
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

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
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

    @Override
    public String toString() {
        return "BuildingFloor{" +
                "id=" + id +
                ", buildingId=" + buildingId +
                ", name=" + name +
                ", code=" + code +
                ", isDeleted=" + isDeleted +
                ", createTime=" + createTime +
                ", createUserId=" + createUserId +
                ", lastModifyTime=" + lastModifyTime +
                ", lastModifyUserId=" + lastModifyUserId +
                "}";
    }
}
