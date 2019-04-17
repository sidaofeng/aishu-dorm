package com.waken.dorm.common.entity.dorm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DormExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DormExample() {
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

        public Criteria andPkDormIdIsNull() {
            addCriterion("pk_dorm_id is null");
            return (Criteria) this;
        }

        public Criteria andPkDormIdIsNotNull() {
            addCriterion("pk_dorm_id is not null");
            return (Criteria) this;
        }

        public Criteria andPkDormIdEqualTo(String value) {
            addCriterion("pk_dorm_id =", value, "pkDormId");
            return (Criteria) this;
        }

        public Criteria andPkDormIdNotEqualTo(String value) {
            addCriterion("pk_dorm_id <>", value, "pkDormId");
            return (Criteria) this;
        }

        public Criteria andPkDormIdGreaterThan(String value) {
            addCriterion("pk_dorm_id >", value, "pkDormId");
            return (Criteria) this;
        }

        public Criteria andPkDormIdGreaterThanOrEqualTo(String value) {
            addCriterion("pk_dorm_id >=", value, "pkDormId");
            return (Criteria) this;
        }

        public Criteria andPkDormIdLessThan(String value) {
            addCriterion("pk_dorm_id <", value, "pkDormId");
            return (Criteria) this;
        }

        public Criteria andPkDormIdLessThanOrEqualTo(String value) {
            addCriterion("pk_dorm_id <=", value, "pkDormId");
            return (Criteria) this;
        }

        public Criteria andPkDormIdLike(String value) {
            addCriterion("pk_dorm_id like", value, "pkDormId");
            return (Criteria) this;
        }

        public Criteria andPkDormIdNotLike(String value) {
            addCriterion("pk_dorm_id not like", value, "pkDormId");
            return (Criteria) this;
        }

        public Criteria andPkDormIdIn(List<String> values) {
            addCriterion("pk_dorm_id in", values, "pkDormId");
            return (Criteria) this;
        }

        public Criteria andPkDormIdNotIn(List<String> values) {
            addCriterion("pk_dorm_id not in", values, "pkDormId");
            return (Criteria) this;
        }

        public Criteria andPkDormIdBetween(String value1, String value2) {
            addCriterion("pk_dorm_id between", value1, value2, "pkDormId");
            return (Criteria) this;
        }

        public Criteria andPkDormIdNotBetween(String value1, String value2) {
            addCriterion("pk_dorm_id not between", value1, value2, "pkDormId");
            return (Criteria) this;
        }

        public Criteria andDormBuildingIdIsNull() {
            addCriterion("dorm_building_id is null");
            return (Criteria) this;
        }

        public Criteria andDormBuildingIdIsNotNull() {
            addCriterion("dorm_building_id is not null");
            return (Criteria) this;
        }

        public Criteria andDormBuildingIdEqualTo(String value) {
            addCriterion("dorm_building_id =", value, "dormBuildingId");
            return (Criteria) this;
        }

        public Criteria andDormBuildingIdNotEqualTo(String value) {
            addCriterion("dorm_building_id <>", value, "dormBuildingId");
            return (Criteria) this;
        }

        public Criteria andDormBuildingIdGreaterThan(String value) {
            addCriterion("dorm_building_id >", value, "dormBuildingId");
            return (Criteria) this;
        }

        public Criteria andDormBuildingIdGreaterThanOrEqualTo(String value) {
            addCriterion("dorm_building_id >=", value, "dormBuildingId");
            return (Criteria) this;
        }

        public Criteria andDormBuildingIdLessThan(String value) {
            addCriterion("dorm_building_id <", value, "dormBuildingId");
            return (Criteria) this;
        }

        public Criteria andDormBuildingIdLessThanOrEqualTo(String value) {
            addCriterion("dorm_building_id <=", value, "dormBuildingId");
            return (Criteria) this;
        }

        public Criteria andDormBuildingIdLike(String value) {
            addCriterion("dorm_building_id like", value, "dormBuildingId");
            return (Criteria) this;
        }

        public Criteria andDormBuildingIdNotLike(String value) {
            addCriterion("dorm_building_id not like", value, "dormBuildingId");
            return (Criteria) this;
        }

        public Criteria andDormBuildingIdIn(List<String> values) {
            addCriterion("dorm_building_id in", values, "dormBuildingId");
            return (Criteria) this;
        }

        public Criteria andDormBuildingIdNotIn(List<String> values) {
            addCriterion("dorm_building_id not in", values, "dormBuildingId");
            return (Criteria) this;
        }

        public Criteria andDormBuildingIdBetween(String value1, String value2) {
            addCriterion("dorm_building_id between", value1, value2, "dormBuildingId");
            return (Criteria) this;
        }

        public Criteria andDormBuildingIdNotBetween(String value1, String value2) {
            addCriterion("dorm_building_id not between", value1, value2, "dormBuildingId");
            return (Criteria) this;
        }

        public Criteria andBuildingLevelthIsNull() {
            addCriterion("building_levelth is null");
            return (Criteria) this;
        }

        public Criteria andBuildingLevelthIsNotNull() {
            addCriterion("building_levelth is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingLevelthEqualTo(Integer value) {
            addCriterion("building_levelth =", value, "buildingLevelth");
            return (Criteria) this;
        }

        public Criteria andBuildingLevelthNotEqualTo(Integer value) {
            addCriterion("building_levelth <>", value, "buildingLevelth");
            return (Criteria) this;
        }

        public Criteria andBuildingLevelthGreaterThan(Integer value) {
            addCriterion("building_levelth >", value, "buildingLevelth");
            return (Criteria) this;
        }

        public Criteria andBuildingLevelthGreaterThanOrEqualTo(Integer value) {
            addCriterion("building_levelth >=", value, "buildingLevelth");
            return (Criteria) this;
        }

        public Criteria andBuildingLevelthLessThan(Integer value) {
            addCriterion("building_levelth <", value, "buildingLevelth");
            return (Criteria) this;
        }

        public Criteria andBuildingLevelthLessThanOrEqualTo(Integer value) {
            addCriterion("building_levelth <=", value, "buildingLevelth");
            return (Criteria) this;
        }

        public Criteria andBuildingLevelthIn(List<Integer> values) {
            addCriterion("building_levelth in", values, "buildingLevelth");
            return (Criteria) this;
        }

        public Criteria andBuildingLevelthNotIn(List<Integer> values) {
            addCriterion("building_levelth not in", values, "buildingLevelth");
            return (Criteria) this;
        }

        public Criteria andBuildingLevelthBetween(Integer value1, Integer value2) {
            addCriterion("building_levelth between", value1, value2, "buildingLevelth");
            return (Criteria) this;
        }

        public Criteria andBuildingLevelthNotBetween(Integer value1, Integer value2) {
            addCriterion("building_levelth not between", value1, value2, "buildingLevelth");
            return (Criteria) this;
        }

        public Criteria andDormTypeIsNull() {
            addCriterion("dorm_type is null");
            return (Criteria) this;
        }

        public Criteria andDormTypeIsNotNull() {
            addCriterion("dorm_type is not null");
            return (Criteria) this;
        }

        public Criteria andDormTypeEqualTo(Integer value) {
            addCriterion("dorm_type =", value, "dormType");
            return (Criteria) this;
        }

        public Criteria andDormTypeNotEqualTo(Integer value) {
            addCriterion("dorm_type <>", value, "dormType");
            return (Criteria) this;
        }

        public Criteria andDormTypeGreaterThan(Integer value) {
            addCriterion("dorm_type >", value, "dormType");
            return (Criteria) this;
        }

        public Criteria andDormTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("dorm_type >=", value, "dormType");
            return (Criteria) this;
        }

        public Criteria andDormTypeLessThan(Integer value) {
            addCriterion("dorm_type <", value, "dormType");
            return (Criteria) this;
        }

        public Criteria andDormTypeLessThanOrEqualTo(Integer value) {
            addCriterion("dorm_type <=", value, "dormType");
            return (Criteria) this;
        }

        public Criteria andDormTypeIn(List<Integer> values) {
            addCriterion("dorm_type in", values, "dormType");
            return (Criteria) this;
        }

        public Criteria andDormTypeNotIn(List<Integer> values) {
            addCriterion("dorm_type not in", values, "dormType");
            return (Criteria) this;
        }

        public Criteria andDormTypeBetween(Integer value1, Integer value2) {
            addCriterion("dorm_type between", value1, value2, "dormType");
            return (Criteria) this;
        }

        public Criteria andDormTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("dorm_type not between", value1, value2, "dormType");
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

        public Criteria andDormDescIsNull() {
            addCriterion("dorm_desc is null");
            return (Criteria) this;
        }

        public Criteria andDormDescIsNotNull() {
            addCriterion("dorm_desc is not null");
            return (Criteria) this;
        }

        public Criteria andDormDescEqualTo(String value) {
            addCriterion("dorm_desc =", value, "dormDesc");
            return (Criteria) this;
        }

        public Criteria andDormDescNotEqualTo(String value) {
            addCriterion("dorm_desc <>", value, "dormDesc");
            return (Criteria) this;
        }

        public Criteria andDormDescGreaterThan(String value) {
            addCriterion("dorm_desc >", value, "dormDesc");
            return (Criteria) this;
        }

        public Criteria andDormDescGreaterThanOrEqualTo(String value) {
            addCriterion("dorm_desc >=", value, "dormDesc");
            return (Criteria) this;
        }

        public Criteria andDormDescLessThan(String value) {
            addCriterion("dorm_desc <", value, "dormDesc");
            return (Criteria) this;
        }

        public Criteria andDormDescLessThanOrEqualTo(String value) {
            addCriterion("dorm_desc <=", value, "dormDesc");
            return (Criteria) this;
        }

        public Criteria andDormDescLike(String value) {
            addCriterion("dorm_desc like", value, "dormDesc");
            return (Criteria) this;
        }

        public Criteria andDormDescNotLike(String value) {
            addCriterion("dorm_desc not like", value, "dormDesc");
            return (Criteria) this;
        }

        public Criteria andDormDescIn(List<String> values) {
            addCriterion("dorm_desc in", values, "dormDesc");
            return (Criteria) this;
        }

        public Criteria andDormDescNotIn(List<String> values) {
            addCriterion("dorm_desc not in", values, "dormDesc");
            return (Criteria) this;
        }

        public Criteria andDormDescBetween(String value1, String value2) {
            addCriterion("dorm_desc between", value1, value2, "dormDesc");
            return (Criteria) this;
        }

        public Criteria andDormDescNotBetween(String value1, String value2) {
            addCriterion("dorm_desc not between", value1, value2, "dormDesc");
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