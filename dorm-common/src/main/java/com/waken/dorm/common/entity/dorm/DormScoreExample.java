package com.waken.dorm.common.entity.dorm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DormScoreExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DormScoreExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andPkDormScoreIdIsNull() {
            addCriterion("pk_dorm_score_id is null");
            return (Criteria) this;
        }

        public Criteria andPkDormScoreIdIsNotNull() {
            addCriterion("pk_dorm_score_id is not null");
            return (Criteria) this;
        }

        public Criteria andPkDormScoreIdEqualTo(String value) {
            addCriterion("pk_dorm_score_id =", value, "pkDormScoreId");
            return (Criteria) this;
        }

        public Criteria andPkDormScoreIdNotEqualTo(String value) {
            addCriterion("pk_dorm_score_id <>", value, "pkDormScoreId");
            return (Criteria) this;
        }

        public Criteria andPkDormScoreIdGreaterThan(String value) {
            addCriterion("pk_dorm_score_id >", value, "pkDormScoreId");
            return (Criteria) this;
        }

        public Criteria andPkDormScoreIdGreaterThanOrEqualTo(String value) {
            addCriterion("pk_dorm_score_id >=", value, "pkDormScoreId");
            return (Criteria) this;
        }

        public Criteria andPkDormScoreIdLessThan(String value) {
            addCriterion("pk_dorm_score_id <", value, "pkDormScoreId");
            return (Criteria) this;
        }

        public Criteria andPkDormScoreIdLessThanOrEqualTo(String value) {
            addCriterion("pk_dorm_score_id <=", value, "pkDormScoreId");
            return (Criteria) this;
        }

        public Criteria andPkDormScoreIdLike(String value) {
            addCriterion("pk_dorm_score_id like", value, "pkDormScoreId");
            return (Criteria) this;
        }

        public Criteria andPkDormScoreIdNotLike(String value) {
            addCriterion("pk_dorm_score_id not like", value, "pkDormScoreId");
            return (Criteria) this;
        }

        public Criteria andPkDormScoreIdIn(List<String> values) {
            addCriterion("pk_dorm_score_id in", values, "pkDormScoreId");
            return (Criteria) this;
        }

        public Criteria andPkDormScoreIdNotIn(List<String> values) {
            addCriterion("pk_dorm_score_id not in", values, "pkDormScoreId");
            return (Criteria) this;
        }

        public Criteria andPkDormScoreIdBetween(String value1, String value2) {
            addCriterion("pk_dorm_score_id between", value1, value2, "pkDormScoreId");
            return (Criteria) this;
        }

        public Criteria andPkDormScoreIdNotBetween(String value1, String value2) {
            addCriterion("pk_dorm_score_id not between", value1, value2, "pkDormScoreId");
            return (Criteria) this;
        }

        public Criteria andDormNumIsNull() {
            addCriterion("dorm_num is null");
            return (Criteria) this;
        }

        public Criteria andDormNumIsNotNull() {
            addCriterion("dorm_num is not null");
            return (Criteria) this;
        }

        public Criteria andDormNumEqualTo(String value) {
            addCriterion("dorm_num =", value, "dormNum");
            return (Criteria) this;
        }

        public Criteria andDormNumNotEqualTo(String value) {
            addCriterion("dorm_num <>", value, "dormNum");
            return (Criteria) this;
        }

        public Criteria andDormNumGreaterThan(String value) {
            addCriterion("dorm_num >", value, "dormNum");
            return (Criteria) this;
        }

        public Criteria andDormNumGreaterThanOrEqualTo(String value) {
            addCriterion("dorm_num >=", value, "dormNum");
            return (Criteria) this;
        }

        public Criteria andDormNumLessThan(String value) {
            addCriterion("dorm_num <", value, "dormNum");
            return (Criteria) this;
        }

        public Criteria andDormNumLessThanOrEqualTo(String value) {
            addCriterion("dorm_num <=", value, "dormNum");
            return (Criteria) this;
        }

        public Criteria andDormNumLike(String value) {
            addCriterion("dorm_num like", value, "dormNum");
            return (Criteria) this;
        }

        public Criteria andDormNumNotLike(String value) {
            addCriterion("dorm_num not like", value, "dormNum");
            return (Criteria) this;
        }

        public Criteria andDormNumIn(List<String> values) {
            addCriterion("dorm_num in", values, "dormNum");
            return (Criteria) this;
        }

        public Criteria andDormNumNotIn(List<String> values) {
            addCriterion("dorm_num not in", values, "dormNum");
            return (Criteria) this;
        }

        public Criteria andDormNumBetween(String value1, String value2) {
            addCriterion("dorm_num between", value1, value2, "dormNum");
            return (Criteria) this;
        }

        public Criteria andDormNumNotBetween(String value1, String value2) {
            addCriterion("dorm_num not between", value1, value2, "dormNum");
            return (Criteria) this;
        }

        public Criteria andCultureScoreIsNull() {
            addCriterion("culture_score is null");
            return (Criteria) this;
        }

        public Criteria andCultureScoreIsNotNull() {
            addCriterion("culture_score is not null");
            return (Criteria) this;
        }

        public Criteria andCultureScoreEqualTo(Integer value) {
            addCriterion("culture_score =", value, "cultureScore");
            return (Criteria) this;
        }

        public Criteria andCultureScoreNotEqualTo(Integer value) {
            addCriterion("culture_score <>", value, "cultureScore");
            return (Criteria) this;
        }

        public Criteria andCultureScoreGreaterThan(Integer value) {
            addCriterion("culture_score >", value, "cultureScore");
            return (Criteria) this;
        }

        public Criteria andCultureScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("culture_score >=", value, "cultureScore");
            return (Criteria) this;
        }

        public Criteria andCultureScoreLessThan(Integer value) {
            addCriterion("culture_score <", value, "cultureScore");
            return (Criteria) this;
        }

        public Criteria andCultureScoreLessThanOrEqualTo(Integer value) {
            addCriterion("culture_score <=", value, "cultureScore");
            return (Criteria) this;
        }

        public Criteria andCultureScoreIn(List<Integer> values) {
            addCriterion("culture_score in", values, "cultureScore");
            return (Criteria) this;
        }

        public Criteria andCultureScoreNotIn(List<Integer> values) {
            addCriterion("culture_score not in", values, "cultureScore");
            return (Criteria) this;
        }

        public Criteria andCultureScoreBetween(Integer value1, Integer value2) {
            addCriterion("culture_score between", value1, value2, "cultureScore");
            return (Criteria) this;
        }

        public Criteria andCultureScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("culture_score not between", value1, value2, "cultureScore");
            return (Criteria) this;
        }

        public Criteria andDisciplineScoreIsNull() {
            addCriterion("discipline_score is null");
            return (Criteria) this;
        }

        public Criteria andDisciplineScoreIsNotNull() {
            addCriterion("discipline_score is not null");
            return (Criteria) this;
        }

        public Criteria andDisciplineScoreEqualTo(Integer value) {
            addCriterion("discipline_score =", value, "disciplineScore");
            return (Criteria) this;
        }

        public Criteria andDisciplineScoreNotEqualTo(Integer value) {
            addCriterion("discipline_score <>", value, "disciplineScore");
            return (Criteria) this;
        }

        public Criteria andDisciplineScoreGreaterThan(Integer value) {
            addCriterion("discipline_score >", value, "disciplineScore");
            return (Criteria) this;
        }

        public Criteria andDisciplineScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("discipline_score >=", value, "disciplineScore");
            return (Criteria) this;
        }

        public Criteria andDisciplineScoreLessThan(Integer value) {
            addCriterion("discipline_score <", value, "disciplineScore");
            return (Criteria) this;
        }

        public Criteria andDisciplineScoreLessThanOrEqualTo(Integer value) {
            addCriterion("discipline_score <=", value, "disciplineScore");
            return (Criteria) this;
        }

        public Criteria andDisciplineScoreIn(List<Integer> values) {
            addCriterion("discipline_score in", values, "disciplineScore");
            return (Criteria) this;
        }

        public Criteria andDisciplineScoreNotIn(List<Integer> values) {
            addCriterion("discipline_score not in", values, "disciplineScore");
            return (Criteria) this;
        }

        public Criteria andDisciplineScoreBetween(Integer value1, Integer value2) {
            addCriterion("discipline_score between", value1, value2, "disciplineScore");
            return (Criteria) this;
        }

        public Criteria andDisciplineScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("discipline_score not between", value1, value2, "disciplineScore");
            return (Criteria) this;
        }

        public Criteria andBedScoreIsNull() {
            addCriterion("bed_score is null");
            return (Criteria) this;
        }

        public Criteria andBedScoreIsNotNull() {
            addCriterion("bed_score is not null");
            return (Criteria) this;
        }

        public Criteria andBedScoreEqualTo(Integer value) {
            addCriterion("bed_score =", value, "bedScore");
            return (Criteria) this;
        }

        public Criteria andBedScoreNotEqualTo(Integer value) {
            addCriterion("bed_score <>", value, "bedScore");
            return (Criteria) this;
        }

        public Criteria andBedScoreGreaterThan(Integer value) {
            addCriterion("bed_score >", value, "bedScore");
            return (Criteria) this;
        }

        public Criteria andBedScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("bed_score >=", value, "bedScore");
            return (Criteria) this;
        }

        public Criteria andBedScoreLessThan(Integer value) {
            addCriterion("bed_score <", value, "bedScore");
            return (Criteria) this;
        }

        public Criteria andBedScoreLessThanOrEqualTo(Integer value) {
            addCriterion("bed_score <=", value, "bedScore");
            return (Criteria) this;
        }

        public Criteria andBedScoreIn(List<Integer> values) {
            addCriterion("bed_score in", values, "bedScore");
            return (Criteria) this;
        }

        public Criteria andBedScoreNotIn(List<Integer> values) {
            addCriterion("bed_score not in", values, "bedScore");
            return (Criteria) this;
        }

        public Criteria andBedScoreBetween(Integer value1, Integer value2) {
            addCriterion("bed_score between", value1, value2, "bedScore");
            return (Criteria) this;
        }

        public Criteria andBedScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("bed_score not between", value1, value2, "bedScore");
            return (Criteria) this;
        }

        public Criteria andDeskScoreIsNull() {
            addCriterion("desk_score is null");
            return (Criteria) this;
        }

        public Criteria andDeskScoreIsNotNull() {
            addCriterion("desk_score is not null");
            return (Criteria) this;
        }

        public Criteria andDeskScoreEqualTo(Integer value) {
            addCriterion("desk_score =", value, "deskScore");
            return (Criteria) this;
        }

        public Criteria andDeskScoreNotEqualTo(Integer value) {
            addCriterion("desk_score <>", value, "deskScore");
            return (Criteria) this;
        }

        public Criteria andDeskScoreGreaterThan(Integer value) {
            addCriterion("desk_score >", value, "deskScore");
            return (Criteria) this;
        }

        public Criteria andDeskScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("desk_score >=", value, "deskScore");
            return (Criteria) this;
        }

        public Criteria andDeskScoreLessThan(Integer value) {
            addCriterion("desk_score <", value, "deskScore");
            return (Criteria) this;
        }

        public Criteria andDeskScoreLessThanOrEqualTo(Integer value) {
            addCriterion("desk_score <=", value, "deskScore");
            return (Criteria) this;
        }

        public Criteria andDeskScoreIn(List<Integer> values) {
            addCriterion("desk_score in", values, "deskScore");
            return (Criteria) this;
        }

        public Criteria andDeskScoreNotIn(List<Integer> values) {
            addCriterion("desk_score not in", values, "deskScore");
            return (Criteria) this;
        }

        public Criteria andDeskScoreBetween(Integer value1, Integer value2) {
            addCriterion("desk_score between", value1, value2, "deskScore");
            return (Criteria) this;
        }

        public Criteria andDeskScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("desk_score not between", value1, value2, "deskScore");
            return (Criteria) this;
        }

        public Criteria andBalconyScoreIsNull() {
            addCriterion("balcony_score is null");
            return (Criteria) this;
        }

        public Criteria andBalconyScoreIsNotNull() {
            addCriterion("balcony_score is not null");
            return (Criteria) this;
        }

        public Criteria andBalconyScoreEqualTo(Integer value) {
            addCriterion("balcony_score =", value, "balconyScore");
            return (Criteria) this;
        }

        public Criteria andBalconyScoreNotEqualTo(Integer value) {
            addCriterion("balcony_score <>", value, "balconyScore");
            return (Criteria) this;
        }

        public Criteria andBalconyScoreGreaterThan(Integer value) {
            addCriterion("balcony_score >", value, "balconyScore");
            return (Criteria) this;
        }

        public Criteria andBalconyScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("balcony_score >=", value, "balconyScore");
            return (Criteria) this;
        }

        public Criteria andBalconyScoreLessThan(Integer value) {
            addCriterion("balcony_score <", value, "balconyScore");
            return (Criteria) this;
        }

        public Criteria andBalconyScoreLessThanOrEqualTo(Integer value) {
            addCriterion("balcony_score <=", value, "balconyScore");
            return (Criteria) this;
        }

        public Criteria andBalconyScoreIn(List<Integer> values) {
            addCriterion("balcony_score in", values, "balconyScore");
            return (Criteria) this;
        }

        public Criteria andBalconyScoreNotIn(List<Integer> values) {
            addCriterion("balcony_score not in", values, "balconyScore");
            return (Criteria) this;
        }

        public Criteria andBalconyScoreBetween(Integer value1, Integer value2) {
            addCriterion("balcony_score between", value1, value2, "balconyScore");
            return (Criteria) this;
        }

        public Criteria andBalconyScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("balcony_score not between", value1, value2, "balconyScore");
            return (Criteria) this;
        }

        public Criteria andToiletScoreIsNull() {
            addCriterion("toilet_score is null");
            return (Criteria) this;
        }

        public Criteria andToiletScoreIsNotNull() {
            addCriterion("toilet_score is not null");
            return (Criteria) this;
        }

        public Criteria andToiletScoreEqualTo(Integer value) {
            addCriterion("toilet_score =", value, "toiletScore");
            return (Criteria) this;
        }

        public Criteria andToiletScoreNotEqualTo(Integer value) {
            addCriterion("toilet_score <>", value, "toiletScore");
            return (Criteria) this;
        }

        public Criteria andToiletScoreGreaterThan(Integer value) {
            addCriterion("toilet_score >", value, "toiletScore");
            return (Criteria) this;
        }

        public Criteria andToiletScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("toilet_score >=", value, "toiletScore");
            return (Criteria) this;
        }

        public Criteria andToiletScoreLessThan(Integer value) {
            addCriterion("toilet_score <", value, "toiletScore");
            return (Criteria) this;
        }

        public Criteria andToiletScoreLessThanOrEqualTo(Integer value) {
            addCriterion("toilet_score <=", value, "toiletScore");
            return (Criteria) this;
        }

        public Criteria andToiletScoreIn(List<Integer> values) {
            addCriterion("toilet_score in", values, "toiletScore");
            return (Criteria) this;
        }

        public Criteria andToiletScoreNotIn(List<Integer> values) {
            addCriterion("toilet_score not in", values, "toiletScore");
            return (Criteria) this;
        }

        public Criteria andToiletScoreBetween(Integer value1, Integer value2) {
            addCriterion("toilet_score between", value1, value2, "toiletScore");
            return (Criteria) this;
        }

        public Criteria andToiletScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("toilet_score not between", value1, value2, "toiletScore");
            return (Criteria) this;
        }

        public Criteria andGroundScoreIsNull() {
            addCriterion("ground_score is null");
            return (Criteria) this;
        }

        public Criteria andGroundScoreIsNotNull() {
            addCriterion("ground_score is not null");
            return (Criteria) this;
        }

        public Criteria andGroundScoreEqualTo(Integer value) {
            addCriterion("ground_score =", value, "groundScore");
            return (Criteria) this;
        }

        public Criteria andGroundScoreNotEqualTo(Integer value) {
            addCriterion("ground_score <>", value, "groundScore");
            return (Criteria) this;
        }

        public Criteria andGroundScoreGreaterThan(Integer value) {
            addCriterion("ground_score >", value, "groundScore");
            return (Criteria) this;
        }

        public Criteria andGroundScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("ground_score >=", value, "groundScore");
            return (Criteria) this;
        }

        public Criteria andGroundScoreLessThan(Integer value) {
            addCriterion("ground_score <", value, "groundScore");
            return (Criteria) this;
        }

        public Criteria andGroundScoreLessThanOrEqualTo(Integer value) {
            addCriterion("ground_score <=", value, "groundScore");
            return (Criteria) this;
        }

        public Criteria andGroundScoreIn(List<Integer> values) {
            addCriterion("ground_score in", values, "groundScore");
            return (Criteria) this;
        }

        public Criteria andGroundScoreNotIn(List<Integer> values) {
            addCriterion("ground_score not in", values, "groundScore");
            return (Criteria) this;
        }

        public Criteria andGroundScoreBetween(Integer value1, Integer value2) {
            addCriterion("ground_score between", value1, value2, "groundScore");
            return (Criteria) this;
        }

        public Criteria andGroundScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("ground_score not between", value1, value2, "groundScore");
            return (Criteria) this;
        }

        public Criteria andDoorWindowScoreIsNull() {
            addCriterion("door_window_score is null");
            return (Criteria) this;
        }

        public Criteria andDoorWindowScoreIsNotNull() {
            addCriterion("door_window_score is not null");
            return (Criteria) this;
        }

        public Criteria andDoorWindowScoreEqualTo(Integer value) {
            addCriterion("door_window_score =", value, "doorWindowScore");
            return (Criteria) this;
        }

        public Criteria andDoorWindowScoreNotEqualTo(Integer value) {
            addCriterion("door_window_score <>", value, "doorWindowScore");
            return (Criteria) this;
        }

        public Criteria andDoorWindowScoreGreaterThan(Integer value) {
            addCriterion("door_window_score >", value, "doorWindowScore");
            return (Criteria) this;
        }

        public Criteria andDoorWindowScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("door_window_score >=", value, "doorWindowScore");
            return (Criteria) this;
        }

        public Criteria andDoorWindowScoreLessThan(Integer value) {
            addCriterion("door_window_score <", value, "doorWindowScore");
            return (Criteria) this;
        }

        public Criteria andDoorWindowScoreLessThanOrEqualTo(Integer value) {
            addCriterion("door_window_score <=", value, "doorWindowScore");
            return (Criteria) this;
        }

        public Criteria andDoorWindowScoreIn(List<Integer> values) {
            addCriterion("door_window_score in", values, "doorWindowScore");
            return (Criteria) this;
        }

        public Criteria andDoorWindowScoreNotIn(List<Integer> values) {
            addCriterion("door_window_score not in", values, "doorWindowScore");
            return (Criteria) this;
        }

        public Criteria andDoorWindowScoreBetween(Integer value1, Integer value2) {
            addCriterion("door_window_score between", value1, value2, "doorWindowScore");
            return (Criteria) this;
        }

        public Criteria andDoorWindowScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("door_window_score not between", value1, value2, "doorWindowScore");
            return (Criteria) this;
        }

        public Criteria andMetopeScoreIsNull() {
            addCriterion("metope_score is null");
            return (Criteria) this;
        }

        public Criteria andMetopeScoreIsNotNull() {
            addCriterion("metope_score is not null");
            return (Criteria) this;
        }

        public Criteria andMetopeScoreEqualTo(Integer value) {
            addCriterion("metope_score =", value, "metopeScore");
            return (Criteria) this;
        }

        public Criteria andMetopeScoreNotEqualTo(Integer value) {
            addCriterion("metope_score <>", value, "metopeScore");
            return (Criteria) this;
        }

        public Criteria andMetopeScoreGreaterThan(Integer value) {
            addCriterion("metope_score >", value, "metopeScore");
            return (Criteria) this;
        }

        public Criteria andMetopeScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("metope_score >=", value, "metopeScore");
            return (Criteria) this;
        }

        public Criteria andMetopeScoreLessThan(Integer value) {
            addCriterion("metope_score <", value, "metopeScore");
            return (Criteria) this;
        }

        public Criteria andMetopeScoreLessThanOrEqualTo(Integer value) {
            addCriterion("metope_score <=", value, "metopeScore");
            return (Criteria) this;
        }

        public Criteria andMetopeScoreIn(List<Integer> values) {
            addCriterion("metope_score in", values, "metopeScore");
            return (Criteria) this;
        }

        public Criteria andMetopeScoreNotIn(List<Integer> values) {
            addCriterion("metope_score not in", values, "metopeScore");
            return (Criteria) this;
        }

        public Criteria andMetopeScoreBetween(Integer value1, Integer value2) {
            addCriterion("metope_score between", value1, value2, "metopeScore");
            return (Criteria) this;
        }

        public Criteria andMetopeScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("metope_score not between", value1, value2, "metopeScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIsNull() {
            addCriterion("total_score is null");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIsNotNull() {
            addCriterion("total_score is not null");
            return (Criteria) this;
        }

        public Criteria andTotalScoreEqualTo(Integer value) {
            addCriterion("total_score =", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotEqualTo(Integer value) {
            addCriterion("total_score <>", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreGreaterThan(Integer value) {
            addCriterion("total_score >", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_score >=", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreLessThan(Integer value) {
            addCriterion("total_score <", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreLessThanOrEqualTo(Integer value) {
            addCriterion("total_score <=", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIn(List<Integer> values) {
            addCriterion("total_score in", values, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotIn(List<Integer> values) {
            addCriterion("total_score not in", values, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreBetween(Integer value1, Integer value2) {
            addCriterion("total_score between", value1, value2, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("total_score not between", value1, value2, "totalScore");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(String value) {
            addCriterion("create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(String value) {
            addCriterion("create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(String value) {
            addCriterion("create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(String value) {
            addCriterion("create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(String value) {
            addCriterion("create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLike(String value) {
            addCriterion("create_user_id like", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotLike(String value) {
            addCriterion("create_user_id not like", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<String> values) {
            addCriterion("create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<String> values) {
            addCriterion("create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(String value1, String value2) {
            addCriterion("create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(String value1, String value2) {
            addCriterion("create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeIsNull() {
            addCriterion("last_modify_time is null");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeIsNotNull() {
            addCriterion("last_modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeEqualTo(Date value) {
            addCriterion("last_modify_time =", value, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeNotEqualTo(Date value) {
            addCriterion("last_modify_time <>", value, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeGreaterThan(Date value) {
            addCriterion("last_modify_time >", value, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_modify_time >=", value, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeLessThan(Date value) {
            addCriterion("last_modify_time <", value, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_modify_time <=", value, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeIn(List<Date> values) {
            addCriterion("last_modify_time in", values, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeNotIn(List<Date> values) {
            addCriterion("last_modify_time not in", values, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeBetween(Date value1, Date value2) {
            addCriterion("last_modify_time between", value1, value2, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_modify_time not between", value1, value2, "lastModifyTime");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdIsNull() {
            addCriterion("last_modify_user_id is null");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdIsNotNull() {
            addCriterion("last_modify_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdEqualTo(String value) {
            addCriterion("last_modify_user_id =", value, "lastModifyUserId");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdNotEqualTo(String value) {
            addCriterion("last_modify_user_id <>", value, "lastModifyUserId");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdGreaterThan(String value) {
            addCriterion("last_modify_user_id >", value, "lastModifyUserId");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("last_modify_user_id >=", value, "lastModifyUserId");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdLessThan(String value) {
            addCriterion("last_modify_user_id <", value, "lastModifyUserId");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdLessThanOrEqualTo(String value) {
            addCriterion("last_modify_user_id <=", value, "lastModifyUserId");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdLike(String value) {
            addCriterion("last_modify_user_id like", value, "lastModifyUserId");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdNotLike(String value) {
            addCriterion("last_modify_user_id not like", value, "lastModifyUserId");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdIn(List<String> values) {
            addCriterion("last_modify_user_id in", values, "lastModifyUserId");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdNotIn(List<String> values) {
            addCriterion("last_modify_user_id not in", values, "lastModifyUserId");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdBetween(String value1, String value2) {
            addCriterion("last_modify_user_id between", value1, value2, "lastModifyUserId");
            return (Criteria) this;
        }

        public Criteria andLastModifyUserIdNotBetween(String value1, String value2) {
            addCriterion("last_modify_user_id not between", value1, value2, "lastModifyUserId");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("memo is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("memo is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("memo =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("memo <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("memo >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("memo >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("memo <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("memo <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("memo like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("memo not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("memo in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("memo not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("memo between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("memo not between", value1, value2, "memo");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}