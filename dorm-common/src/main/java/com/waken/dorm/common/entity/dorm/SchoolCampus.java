package com.waken.dorm.common.entity.dorm;

import com.baomidou.mybatisplus.annotation.TableName;
import com.waken.dorm.common.base.BaseEntity;

import java.time.LocalDateTime;

/**
 * <p>
 * 校区基本数据表
 * </p>
 *
 * @author zhaoRong
 * @since 2019-11-21
 */
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

    private LocalDateTime createTime;

    private String createUserId;

    private LocalDateTime lastModifyTime;

    private String lastModifyUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
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
        return "SchoolCampus{" +
                "id=" + id +
                ", code=" + code +
                ", name=" + name +
                ", address=" + address +
                ", postal=" + postal +
                ", tel=" + tel +
                ", fax=" + fax +
                ", leader=" + leader +
                ", isDeleted=" + isDeleted +
                ", createTime=" + createTime +
                ", createUserId=" + createUserId +
                ", lastModifyTime=" + lastModifyTime +
                ", lastModifyUserId=" + lastModifyUserId +
                "}";
    }
}
