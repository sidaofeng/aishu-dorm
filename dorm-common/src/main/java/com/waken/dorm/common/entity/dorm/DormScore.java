package com.waken.dorm.common.entity.dorm;

import com.waken.dorm.common.annotation.ExcelColumn;
import com.waken.dorm.common.base.BaseEntity;

import java.util.Date;

public class DormScore extends BaseEntity {
    private static final long serialVersionUID = -1442722153675048338L;
    private String pkDormScoreId;

    private String dormId;

    @ExcelColumn(name = "宿舍号", column = "A")
    private String dormNum;

    @ExcelColumn(name = "寝室文化", column = "B")
    private Integer cultureScore;

    @ExcelColumn(name = "纪律", column = "C")
    private Integer disciplineScore;

    @ExcelColumn(name = "床铺", column = "D")
    private Integer bedScore;

    @ExcelColumn(name = "书桌", column = "E")
    private Integer deskScore;

    @ExcelColumn(name = "阳台", column = "F")
    private Integer balconyScore;

    @ExcelColumn(name = "厕所", column = "G")
    private Integer toiletScore;

    @ExcelColumn(name = "地面", column = "H")
    private Integer groundScore;

    @ExcelColumn(name = "门窗", column = "I")
    private Integer doorWindowScore;

    @ExcelColumn(name = "墙面", column = "J")
    private Integer metopeScore;

    @ExcelColumn(name = "总分", column = "K")
    private Integer totalScore;

    private Integer status;

    private Date createTime;

    private String createUserId;

    private Date lastModifyTime;

    private String lastModifyUserId;

    @ExcelColumn(name = "备注", column = "L")
    private String memo;

    public String getPkDormScoreId() {
        return pkDormScoreId;
    }

    public void setPkDormScoreId(String pkDormScoreId) {
        this.pkDormScoreId = pkDormScoreId == null ? null : pkDormScoreId.trim();
    }

    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId;
    }

    public String getDormNum() {
        return dormNum;
    }

    public void setDormNum(String dormNum) {
        this.dormNum = dormNum;
    }

    public Integer getCultureScore() {
        return cultureScore;
    }

    public void setCultureScore(Integer cultureScore) {
        this.cultureScore = cultureScore;
    }

    public Integer getDisciplineScore() {
        return disciplineScore;
    }

    public void setDisciplineScore(Integer disciplineScore) {
        this.disciplineScore = disciplineScore;
    }

    public Integer getBedScore() {
        return bedScore;
    }

    public void setBedScore(Integer bedScore) {
        this.bedScore = bedScore;
    }

    public Integer getDeskScore() {
        return deskScore;
    }

    public void setDeskScore(Integer deskScore) {
        this.deskScore = deskScore;
    }

    public Integer getBalconyScore() {
        return balconyScore;
    }

    public void setBalconyScore(Integer balconyScore) {
        this.balconyScore = balconyScore;
    }

    public Integer getToiletScore() {
        return toiletScore;
    }

    public void setToiletScore(Integer toiletScore) {
        this.toiletScore = toiletScore;
    }

    public Integer getGroundScore() {
        return groundScore;
    }

    public void setGroundScore(Integer groundScore) {
        this.groundScore = groundScore;
    }

    public Integer getDoorWindowScore() {
        return doorWindowScore;
    }

    public void setDoorWindowScore(Integer doorWindowScore) {
        this.doorWindowScore = doorWindowScore;
    }

    public Integer getMetopeScore() {
        return metopeScore;
    }

    public void setMetopeScore(Integer metopeScore) {
        this.metopeScore = metopeScore;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getLastModifyUserId() {
        return lastModifyUserId;
    }

    public void setLastModifyUserId(String lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId == null ? null : lastModifyUserId.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}