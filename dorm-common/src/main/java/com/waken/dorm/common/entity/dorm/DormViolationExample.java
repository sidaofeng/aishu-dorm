package com.waken.dorm.common.entity.dorm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DormViolationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DormViolationExample() {
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

        public Criteria andPkDormViolationIdIsNull() {
            addCriterion("pk_dorm_violation_id is null");
            return (Criteria) this;
        }

        public Criteria andPkDormViolationIdIsNotNull() {
            addCriterion("pk_dorm_violation_id is not null");
            return (Criteria) this;
        }

        public Criteria andPkDormViolationIdEqualTo(String value) {
            addCriterion("pk_dorm_violation_id =", value, "pkDormViolationId");
            return (Criteria) this;
        }

        public Criteria andPkDormViolationIdNotEqualTo(String value) {
            addCriterion("pk_dorm_violation_id <>", value, "pkDormViolationId");
            return (Criteria) this;
        }

        public Criteria andPkDormViolationIdGreaterThan(String value) {
            addCriterion("pk_dorm_violation_id >", value, "pkDormViolationId");
            return (Criteria) this;
        }

        public Criteria andPkDormViolationIdGreaterThanOrEqualTo(String value) {
            addCriterion("pk_dorm_violation_id >=", value, "pkDormViolationId");
            return (Criteria) this;
        }

        public Criteria andPkDormViolationIdLessThan(String value) {
            addCriterion("pk_dorm_violation_id <", value, "pkDormViolationId");
            return (Criteria) this;
        }

        public Criteria andPkDormViolationIdLessThanOrEqualTo(String value) {
            addCriterion("pk_dorm_violation_id <=", value, "pkDormViolationId");
            return (Criteria) this;
        }

        public Criteria andPkDormViolationIdLike(String value) {
            addCriterion("pk_dorm_violation_id like", value, "pkDormViolationId");
            return (Criteria) this;
        }

        public Criteria andPkDormViolationIdNotLike(String value) {
            addCriterion("pk_dorm_violation_id not like", value, "pkDormViolationId");
            return (Criteria) this;
        }

        public Criteria andPkDormViolationIdIn(List<String> values) {
            addCriterion("pk_dorm_violation_id in", values, "pkDormViolationId");
            return (Criteria) this;
        }

        public Criteria andPkDormViolationIdNotIn(List<String> values) {
            addCriterion("pk_dorm_violation_id not in", values, "pkDormViolationId");
            return (Criteria) this;
        }

        public Criteria andPkDormViolationIdBetween(String value1, String value2) {
            addCriterion("pk_dorm_violation_id between", value1, value2, "pkDormViolationId");
            return (Criteria) this;
        }

        public Criteria andPkDormViolationIdNotBetween(String value1, String value2) {
            addCriterion("pk_dorm_violation_id not between", value1, value2, "pkDormViolationId");
            return (Criteria) this;
        }

        public Criteria andDormRuleIdIsNull() {
            addCriterion("dorm_rule_id is null");
            return (Criteria) this;
        }

        public Criteria andDormRuleIdIsNotNull() {
            addCriterion("dorm_rule_id is not null");
            return (Criteria) this;
        }

        public Criteria andDormRuleIdEqualTo(String value) {
            addCriterion("dorm_rule_id =", value, "dormRuleId");
            return (Criteria) this;
        }

        public Criteria andDormRuleIdNotEqualTo(String value) {
            addCriterion("dorm_rule_id <>", value, "dormRuleId");
            return (Criteria) this;
        }

        public Criteria andDormRuleIdGreaterThan(String value) {
            addCriterion("dorm_rule_id >", value, "dormRuleId");
            return (Criteria) this;
        }

        public Criteria andDormRuleIdGreaterThanOrEqualTo(String value) {
            addCriterion("dorm_rule_id >=", value, "dormRuleId");
            return (Criteria) this;
        }

        public Criteria andDormRuleIdLessThan(String value) {
            addCriterion("dorm_rule_id <", value, "dormRuleId");
            return (Criteria) this;
        }

        public Criteria andDormRuleIdLessThanOrEqualTo(String value) {
            addCriterion("dorm_rule_id <=", value, "dormRuleId");
            return (Criteria) this;
        }

        public Criteria andDormRuleIdLike(String value) {
            addCriterion("dorm_rule_id like", value, "dormRuleId");
            return (Criteria) this;
        }

        public Criteria andDormRuleIdNotLike(String value) {
            addCriterion("dorm_rule_id not like", value, "dormRuleId");
            return (Criteria) this;
        }

        public Criteria andDormRuleIdIn(List<String> values) {
            addCriterion("dorm_rule_id in", values, "dormRuleId");
            return (Criteria) this;
        }

        public Criteria andDormRuleIdNotIn(List<String> values) {
            addCriterion("dorm_rule_id not in", values, "dormRuleId");
            return (Criteria) this;
        }

        public Criteria andDormRuleIdBetween(String value1, String value2) {
            addCriterion("dorm_rule_id between", value1, value2, "dormRuleId");
            return (Criteria) this;
        }

        public Criteria andDormRuleIdNotBetween(String value1, String value2) {
            addCriterion("dorm_rule_id not between", value1, value2, "dormRuleId");
            return (Criteria) this;
        }

        public Criteria andDormIdIsNull() {
            addCriterion("dorm_id is null");
            return (Criteria) this;
        }

        public Criteria andDormIdIsNotNull() {
            addCriterion("dorm_id is not null");
            return (Criteria) this;
        }

        public Criteria andDormIdEqualTo(String value) {
            addCriterion("dorm_id =", value, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdNotEqualTo(String value) {
            addCriterion("dorm_id <>", value, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdGreaterThan(String value) {
            addCriterion("dorm_id >", value, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdGreaterThanOrEqualTo(String value) {
            addCriterion("dorm_id >=", value, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdLessThan(String value) {
            addCriterion("dorm_id <", value, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdLessThanOrEqualTo(String value) {
            addCriterion("dorm_id <=", value, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdLike(String value) {
            addCriterion("dorm_id like", value, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdNotLike(String value) {
            addCriterion("dorm_id not like", value, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdIn(List<String> values) {
            addCriterion("dorm_id in", values, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdNotIn(List<String> values) {
            addCriterion("dorm_id not in", values, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdBetween(String value1, String value2) {
            addCriterion("dorm_id between", value1, value2, "dormId");
            return (Criteria) this;
        }

        public Criteria andDormIdNotBetween(String value1, String value2) {
            addCriterion("dorm_id not between", value1, value2, "dormId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNull() {
            addCriterion("student_id is null");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNotNull() {
            addCriterion("student_id is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIdEqualTo(String value) {
            addCriterion("student_id =", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotEqualTo(String value) {
            addCriterion("student_id <>", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThan(String value) {
            addCriterion("student_id >", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThanOrEqualTo(String value) {
            addCriterion("student_id >=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThan(String value) {
            addCriterion("student_id <", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThanOrEqualTo(String value) {
            addCriterion("student_id <=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLike(String value) {
            addCriterion("student_id like", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotLike(String value) {
            addCriterion("student_id not like", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIn(List<String> values) {
            addCriterion("student_id in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotIn(List<String> values) {
            addCriterion("student_id not in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdBetween(String value1, String value2) {
            addCriterion("student_id between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotBetween(String value1, String value2) {
            addCriterion("student_id not between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andViolationImgUrlIsNull() {
            addCriterion("violation_img_url is null");
            return (Criteria) this;
        }

        public Criteria andViolationImgUrlIsNotNull() {
            addCriterion("violation_img_url is not null");
            return (Criteria) this;
        }

        public Criteria andViolationImgUrlEqualTo(String value) {
            addCriterion("violation_img_url =", value, "violationImgUrl");
            return (Criteria) this;
        }

        public Criteria andViolationImgUrlNotEqualTo(String value) {
            addCriterion("violation_img_url <>", value, "violationImgUrl");
            return (Criteria) this;
        }

        public Criteria andViolationImgUrlGreaterThan(String value) {
            addCriterion("violation_img_url >", value, "violationImgUrl");
            return (Criteria) this;
        }

        public Criteria andViolationImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("violation_img_url >=", value, "violationImgUrl");
            return (Criteria) this;
        }

        public Criteria andViolationImgUrlLessThan(String value) {
            addCriterion("violation_img_url <", value, "violationImgUrl");
            return (Criteria) this;
        }

        public Criteria andViolationImgUrlLessThanOrEqualTo(String value) {
            addCriterion("violation_img_url <=", value, "violationImgUrl");
            return (Criteria) this;
        }

        public Criteria andViolationImgUrlLike(String value) {
            addCriterion("violation_img_url like", value, "violationImgUrl");
            return (Criteria) this;
        }

        public Criteria andViolationImgUrlNotLike(String value) {
            addCriterion("violation_img_url not like", value, "violationImgUrl");
            return (Criteria) this;
        }

        public Criteria andViolationImgUrlIn(List<String> values) {
            addCriterion("violation_img_url in", values, "violationImgUrl");
            return (Criteria) this;
        }

        public Criteria andViolationImgUrlNotIn(List<String> values) {
            addCriterion("violation_img_url not in", values, "violationImgUrl");
            return (Criteria) this;
        }

        public Criteria andViolationImgUrlBetween(String value1, String value2) {
            addCriterion("violation_img_url between", value1, value2, "violationImgUrl");
            return (Criteria) this;
        }

        public Criteria andViolationImgUrlNotBetween(String value1, String value2) {
            addCriterion("violation_img_url not between", value1, value2, "violationImgUrl");
            return (Criteria) this;
        }

        public Criteria andViolationReasonIsNull() {
            addCriterion("violation_reason is null");
            return (Criteria) this;
        }

        public Criteria andViolationReasonIsNotNull() {
            addCriterion("violation_reason is not null");
            return (Criteria) this;
        }

        public Criteria andViolationReasonEqualTo(String value) {
            addCriterion("violation_reason =", value, "violationReason");
            return (Criteria) this;
        }

        public Criteria andViolationReasonNotEqualTo(String value) {
            addCriterion("violation_reason <>", value, "violationReason");
            return (Criteria) this;
        }

        public Criteria andViolationReasonGreaterThan(String value) {
            addCriterion("violation_reason >", value, "violationReason");
            return (Criteria) this;
        }

        public Criteria andViolationReasonGreaterThanOrEqualTo(String value) {
            addCriterion("violation_reason >=", value, "violationReason");
            return (Criteria) this;
        }

        public Criteria andViolationReasonLessThan(String value) {
            addCriterion("violation_reason <", value, "violationReason");
            return (Criteria) this;
        }

        public Criteria andViolationReasonLessThanOrEqualTo(String value) {
            addCriterion("violation_reason <=", value, "violationReason");
            return (Criteria) this;
        }

        public Criteria andViolationReasonLike(String value) {
            addCriterion("violation_reason like", value, "violationReason");
            return (Criteria) this;
        }

        public Criteria andViolationReasonNotLike(String value) {
            addCriterion("violation_reason not like", value, "violationReason");
            return (Criteria) this;
        }

        public Criteria andViolationReasonIn(List<String> values) {
            addCriterion("violation_reason in", values, "violationReason");
            return (Criteria) this;
        }

        public Criteria andViolationReasonNotIn(List<String> values) {
            addCriterion("violation_reason not in", values, "violationReason");
            return (Criteria) this;
        }

        public Criteria andViolationReasonBetween(String value1, String value2) {
            addCriterion("violation_reason between", value1, value2, "violationReason");
            return (Criteria) this;
        }

        public Criteria andViolationReasonNotBetween(String value1, String value2) {
            addCriterion("violation_reason not between", value1, value2, "violationReason");
            return (Criteria) this;
        }

        public Criteria andSolveResultIsNull() {
            addCriterion("solve_result is null");
            return (Criteria) this;
        }

        public Criteria andSolveResultIsNotNull() {
            addCriterion("solve_result is not null");
            return (Criteria) this;
        }

        public Criteria andSolveResultEqualTo(String value) {
            addCriterion("solve_result =", value, "solveResult");
            return (Criteria) this;
        }

        public Criteria andSolveResultNotEqualTo(String value) {
            addCriterion("solve_result <>", value, "solveResult");
            return (Criteria) this;
        }

        public Criteria andSolveResultGreaterThan(String value) {
            addCriterion("solve_result >", value, "solveResult");
            return (Criteria) this;
        }

        public Criteria andSolveResultGreaterThanOrEqualTo(String value) {
            addCriterion("solve_result >=", value, "solveResult");
            return (Criteria) this;
        }

        public Criteria andSolveResultLessThan(String value) {
            addCriterion("solve_result <", value, "solveResult");
            return (Criteria) this;
        }

        public Criteria andSolveResultLessThanOrEqualTo(String value) {
            addCriterion("solve_result <=", value, "solveResult");
            return (Criteria) this;
        }

        public Criteria andSolveResultLike(String value) {
            addCriterion("solve_result like", value, "solveResult");
            return (Criteria) this;
        }

        public Criteria andSolveResultNotLike(String value) {
            addCriterion("solve_result not like", value, "solveResult");
            return (Criteria) this;
        }

        public Criteria andSolveResultIn(List<String> values) {
            addCriterion("solve_result in", values, "solveResult");
            return (Criteria) this;
        }

        public Criteria andSolveResultNotIn(List<String> values) {
            addCriterion("solve_result not in", values, "solveResult");
            return (Criteria) this;
        }

        public Criteria andSolveResultBetween(String value1, String value2) {
            addCriterion("solve_result between", value1, value2, "solveResult");
            return (Criteria) this;
        }

        public Criteria andSolveResultNotBetween(String value1, String value2) {
            addCriterion("solve_result not between", value1, value2, "solveResult");
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