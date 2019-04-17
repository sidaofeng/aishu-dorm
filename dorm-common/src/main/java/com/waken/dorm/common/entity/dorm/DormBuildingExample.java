package com.waken.dorm.common.entity.dorm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DormBuildingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DormBuildingExample() {
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

        public Criteria andPkDormBuildingIdIsNull() {
            addCriterion("pk_dorm_building_id is null");
            return (Criteria) this;
        }

        public Criteria andPkDormBuildingIdIsNotNull() {
            addCriterion("pk_dorm_building_id is not null");
            return (Criteria) this;
        }

        public Criteria andPkDormBuildingIdEqualTo(String value) {
            addCriterion("pk_dorm_building_id =", value, "pkDormBuildingId");
            return (Criteria) this;
        }

        public Criteria andPkDormBuildingIdNotEqualTo(String value) {
            addCriterion("pk_dorm_building_id <>", value, "pkDormBuildingId");
            return (Criteria) this;
        }

        public Criteria andPkDormBuildingIdGreaterThan(String value) {
            addCriterion("pk_dorm_building_id >", value, "pkDormBuildingId");
            return (Criteria) this;
        }

        public Criteria andPkDormBuildingIdGreaterThanOrEqualTo(String value) {
            addCriterion("pk_dorm_building_id >=", value, "pkDormBuildingId");
            return (Criteria) this;
        }

        public Criteria andPkDormBuildingIdLessThan(String value) {
            addCriterion("pk_dorm_building_id <", value, "pkDormBuildingId");
            return (Criteria) this;
        }

        public Criteria andPkDormBuildingIdLessThanOrEqualTo(String value) {
            addCriterion("pk_dorm_building_id <=", value, "pkDormBuildingId");
            return (Criteria) this;
        }

        public Criteria andPkDormBuildingIdLike(String value) {
            addCriterion("pk_dorm_building_id like", value, "pkDormBuildingId");
            return (Criteria) this;
        }

        public Criteria andPkDormBuildingIdNotLike(String value) {
            addCriterion("pk_dorm_building_id not like", value, "pkDormBuildingId");
            return (Criteria) this;
        }

        public Criteria andPkDormBuildingIdIn(List<String> values) {
            addCriterion("pk_dorm_building_id in", values, "pkDormBuildingId");
            return (Criteria) this;
        }

        public Criteria andPkDormBuildingIdNotIn(List<String> values) {
            addCriterion("pk_dorm_building_id not in", values, "pkDormBuildingId");
            return (Criteria) this;
        }

        public Criteria andPkDormBuildingIdBetween(String value1, String value2) {
            addCriterion("pk_dorm_building_id between", value1, value2, "pkDormBuildingId");
            return (Criteria) this;
        }

        public Criteria andPkDormBuildingIdNotBetween(String value1, String value2) {
            addCriterion("pk_dorm_building_id not between", value1, value2, "pkDormBuildingId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIsNull() {
            addCriterion("school_id is null");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIsNotNull() {
            addCriterion("school_id is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolIdEqualTo(String value) {
            addCriterion("school_id =", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotEqualTo(String value) {
            addCriterion("school_id <>", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdGreaterThan(String value) {
            addCriterion("school_id >", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdGreaterThanOrEqualTo(String value) {
            addCriterion("school_id >=", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLessThan(String value) {
            addCriterion("school_id <", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLessThanOrEqualTo(String value) {
            addCriterion("school_id <=", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLike(String value) {
            addCriterion("school_id like", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotLike(String value) {
            addCriterion("school_id not like", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIn(List<String> values) {
            addCriterion("school_id in", values, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotIn(List<String> values) {
            addCriterion("school_id not in", values, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdBetween(String value1, String value2) {
            addCriterion("school_id between", value1, value2, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotBetween(String value1, String value2) {
            addCriterion("school_id not between", value1, value2, "schoolId");
            return (Criteria) this;
        }

        public Criteria andDormBuildingTypeIsNull() {
            addCriterion("dorm_building_type is null");
            return (Criteria) this;
        }

        public Criteria andDormBuildingTypeIsNotNull() {
            addCriterion("dorm_building_type is not null");
            return (Criteria) this;
        }

        public Criteria andDormBuildingTypeEqualTo(Integer value) {
            addCriterion("dorm_building_type =", value, "dormBuildingType");
            return (Criteria) this;
        }

        public Criteria andDormBuildingTypeNotEqualTo(Integer value) {
            addCriterion("dorm_building_type <>", value, "dormBuildingType");
            return (Criteria) this;
        }

        public Criteria andDormBuildingTypeGreaterThan(Integer value) {
            addCriterion("dorm_building_type >", value, "dormBuildingType");
            return (Criteria) this;
        }

        public Criteria andDormBuildingTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("dorm_building_type >=", value, "dormBuildingType");
            return (Criteria) this;
        }

        public Criteria andDormBuildingTypeLessThan(Integer value) {
            addCriterion("dorm_building_type <", value, "dormBuildingType");
            return (Criteria) this;
        }

        public Criteria andDormBuildingTypeLessThanOrEqualTo(Integer value) {
            addCriterion("dorm_building_type <=", value, "dormBuildingType");
            return (Criteria) this;
        }

        public Criteria andDormBuildingTypeIn(List<Integer> values) {
            addCriterion("dorm_building_type in", values, "dormBuildingType");
            return (Criteria) this;
        }

        public Criteria andDormBuildingTypeNotIn(List<Integer> values) {
            addCriterion("dorm_building_type not in", values, "dormBuildingType");
            return (Criteria) this;
        }

        public Criteria andDormBuildingTypeBetween(Integer value1, Integer value2) {
            addCriterion("dorm_building_type between", value1, value2, "dormBuildingType");
            return (Criteria) this;
        }

        public Criteria andDormBuildingTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("dorm_building_type not between", value1, value2, "dormBuildingType");
            return (Criteria) this;
        }

        public Criteria andDormBuildingNumIsNull() {
            addCriterion("dorm_building_num is null");
            return (Criteria) this;
        }

        public Criteria andDormBuildingNumIsNotNull() {
            addCriterion("dorm_building_num is not null");
            return (Criteria) this;
        }

        public Criteria andDormBuildingNumEqualTo(String value) {
            addCriterion("dorm_building_num =", value, "dormBuildingNum");
            return (Criteria) this;
        }

        public Criteria andDormBuildingNumNotEqualTo(String value) {
            addCriterion("dorm_building_num <>", value, "dormBuildingNum");
            return (Criteria) this;
        }

        public Criteria andDormBuildingNumGreaterThan(String value) {
            addCriterion("dorm_building_num >", value, "dormBuildingNum");
            return (Criteria) this;
        }

        public Criteria andDormBuildingNumGreaterThanOrEqualTo(String value) {
            addCriterion("dorm_building_num >=", value, "dormBuildingNum");
            return (Criteria) this;
        }

        public Criteria andDormBuildingNumLessThan(String value) {
            addCriterion("dorm_building_num <", value, "dormBuildingNum");
            return (Criteria) this;
        }

        public Criteria andDormBuildingNumLessThanOrEqualTo(String value) {
            addCriterion("dorm_building_num <=", value, "dormBuildingNum");
            return (Criteria) this;
        }

        public Criteria andDormBuildingNumLike(String value) {
            addCriterion("dorm_building_num like", value, "dormBuildingNum");
            return (Criteria) this;
        }

        public Criteria andDormBuildingNumNotLike(String value) {
            addCriterion("dorm_building_num not like", value, "dormBuildingNum");
            return (Criteria) this;
        }

        public Criteria andDormBuildingNumIn(List<String> values) {
            addCriterion("dorm_building_num in", values, "dormBuildingNum");
            return (Criteria) this;
        }

        public Criteria andDormBuildingNumNotIn(List<String> values) {
            addCriterion("dorm_building_num not in", values, "dormBuildingNum");
            return (Criteria) this;
        }

        public Criteria andDormBuildingNumBetween(String value1, String value2) {
            addCriterion("dorm_building_num between", value1, value2, "dormBuildingNum");
            return (Criteria) this;
        }

        public Criteria andDormBuildingNumNotBetween(String value1, String value2) {
            addCriterion("dorm_building_num not between", value1, value2, "dormBuildingNum");
            return (Criteria) this;
        }

        public Criteria andDormBuildingLevelsIsNull() {
            addCriterion("dorm_building_levels is null");
            return (Criteria) this;
        }

        public Criteria andDormBuildingLevelsIsNotNull() {
            addCriterion("dorm_building_levels is not null");
            return (Criteria) this;
        }

        public Criteria andDormBuildingLevelsEqualTo(Integer value) {
            addCriterion("dorm_building_levels =", value, "dormBuildingLevels");
            return (Criteria) this;
        }

        public Criteria andDormBuildingLevelsNotEqualTo(Integer value) {
            addCriterion("dorm_building_levels <>", value, "dormBuildingLevels");
            return (Criteria) this;
        }

        public Criteria andDormBuildingLevelsGreaterThan(Integer value) {
            addCriterion("dorm_building_levels >", value, "dormBuildingLevels");
            return (Criteria) this;
        }

        public Criteria andDormBuildingLevelsGreaterThanOrEqualTo(Integer value) {
            addCriterion("dorm_building_levels >=", value, "dormBuildingLevels");
            return (Criteria) this;
        }

        public Criteria andDormBuildingLevelsLessThan(Integer value) {
            addCriterion("dorm_building_levels <", value, "dormBuildingLevels");
            return (Criteria) this;
        }

        public Criteria andDormBuildingLevelsLessThanOrEqualTo(Integer value) {
            addCriterion("dorm_building_levels <=", value, "dormBuildingLevels");
            return (Criteria) this;
        }

        public Criteria andDormBuildingLevelsIn(List<Integer> values) {
            addCriterion("dorm_building_levels in", values, "dormBuildingLevels");
            return (Criteria) this;
        }

        public Criteria andDormBuildingLevelsNotIn(List<Integer> values) {
            addCriterion("dorm_building_levels not in", values, "dormBuildingLevels");
            return (Criteria) this;
        }

        public Criteria andDormBuildingLevelsBetween(Integer value1, Integer value2) {
            addCriterion("dorm_building_levels between", value1, value2, "dormBuildingLevels");
            return (Criteria) this;
        }

        public Criteria andDormBuildingLevelsNotBetween(Integer value1, Integer value2) {
            addCriterion("dorm_building_levels not between", value1, value2, "dormBuildingLevels");
            return (Criteria) this;
        }

        public Criteria andDormBuildingDescIsNull() {
            addCriterion("dorm_building_desc is null");
            return (Criteria) this;
        }

        public Criteria andDormBuildingDescIsNotNull() {
            addCriterion("dorm_building_desc is not null");
            return (Criteria) this;
        }

        public Criteria andDormBuildingDescEqualTo(String value) {
            addCriterion("dorm_building_desc =", value, "dormBuildingDesc");
            return (Criteria) this;
        }

        public Criteria andDormBuildingDescNotEqualTo(String value) {
            addCriterion("dorm_building_desc <>", value, "dormBuildingDesc");
            return (Criteria) this;
        }

        public Criteria andDormBuildingDescGreaterThan(String value) {
            addCriterion("dorm_building_desc >", value, "dormBuildingDesc");
            return (Criteria) this;
        }

        public Criteria andDormBuildingDescGreaterThanOrEqualTo(String value) {
            addCriterion("dorm_building_desc >=", value, "dormBuildingDesc");
            return (Criteria) this;
        }

        public Criteria andDormBuildingDescLessThan(String value) {
            addCriterion("dorm_building_desc <", value, "dormBuildingDesc");
            return (Criteria) this;
        }

        public Criteria andDormBuildingDescLessThanOrEqualTo(String value) {
            addCriterion("dorm_building_desc <=", value, "dormBuildingDesc");
            return (Criteria) this;
        }

        public Criteria andDormBuildingDescLike(String value) {
            addCriterion("dorm_building_desc like", value, "dormBuildingDesc");
            return (Criteria) this;
        }

        public Criteria andDormBuildingDescNotLike(String value) {
            addCriterion("dorm_building_desc not like", value, "dormBuildingDesc");
            return (Criteria) this;
        }

        public Criteria andDormBuildingDescIn(List<String> values) {
            addCriterion("dorm_building_desc in", values, "dormBuildingDesc");
            return (Criteria) this;
        }

        public Criteria andDormBuildingDescNotIn(List<String> values) {
            addCriterion("dorm_building_desc not in", values, "dormBuildingDesc");
            return (Criteria) this;
        }

        public Criteria andDormBuildingDescBetween(String value1, String value2) {
            addCriterion("dorm_building_desc between", value1, value2, "dormBuildingDesc");
            return (Criteria) this;
        }

        public Criteria andDormBuildingDescNotBetween(String value1, String value2) {
            addCriterion("dorm_building_desc not between", value1, value2, "dormBuildingDesc");
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