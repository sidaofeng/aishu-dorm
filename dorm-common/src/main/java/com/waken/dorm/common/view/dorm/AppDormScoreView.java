package com.waken.dorm.common.view.dorm;

/**
 * @ClassName AppDormScoreView
 * @Description AppDormScoreView
 * @Author zhaoRong
 * @Date 2019/4/3 13:00
 **/
public class AppDormScoreView {
    private String pkDormScoreId;

    private String dormNum;

    private Integer cultureScore;

    private Integer disciplineScore;

    private Integer bedScore;

    private Integer deskScore;

    private Integer balconyScore;

    private Integer toiletScore;

    private Integer groundScore;

    private Integer doorWindowScore;

    private Integer metopeScore;

    private Integer totalScore;

    private String memo;

    public String getPkDormScoreId() {
        return pkDormScoreId;
    }

    public void setPkDormScoreId(String pkDormScoreId) {
        this.pkDormScoreId = pkDormScoreId;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "AppDormScoreView{" +
                "pkDormScoreId='" + pkDormScoreId + '\'' +
                ", dormNum='" + dormNum + '\'' +
                ", cultureScore=" + cultureScore +
                ", disciplineScore=" + disciplineScore +
                ", bedScore=" + bedScore +
                ", deskScore=" + deskScore +
                ", balconyScore=" + balconyScore +
                ", toiletScore=" + toiletScore +
                ", groundScore=" + groundScore +
                ", doorWindowScore=" + doorWindowScore +
                ", metopeScore=" + metopeScore +
                ", totalScore=" + totalScore +
                ", memo='" + memo + '\'' +
                '}';
    }
}
