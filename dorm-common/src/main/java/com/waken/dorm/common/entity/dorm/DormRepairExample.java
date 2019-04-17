package com.waken.dorm.common.entity.dorm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DormRepairExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DormRepairExample() {
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

        public Criteria andPkDormRepairIdIsNull() {
            addCriterion("pk_dorm_repair_id is null");
            return (Criteria) this;
        }

        public Criteria andPkDormRepairIdIsNotNull() {
            addCriterion("pk_dorm_repair_id is not null");
            return (Criteria) this;
        }

        public Criteria andPkDormRepairIdEqualTo(String value) {
            addCriterion("pk_dorm_repair_id =", value, "pkDormRepairId");
            return (Criteria) this;
        }

        public Criteria andPkDormRepairIdNotEqualTo(String value) {
            addCriterion("pk_dorm_repair_id <>", value, "pkDormRepairId");
            return (Criteria) this;
        }

        public Criteria andPkDormRepairIdGreaterThan(String value) {
            addCriterion("pk_dorm_repair_id >", value, "pkDormRepairId");
            return (Criteria) this;
        }

        public Criteria andPkDormRepairIdGreaterThanOrEqualTo(String value) {
            addCriterion("pk_dorm_repair_id >=", value, "pkDormRepairId");
            return (Criteria) this;
        }

        public Criteria andPkDormRepairIdLessThan(String value) {
            addCriterion("pk_dorm_repair_id <", value, "pkDormRepairId");
            return (Criteria) this;
        }

        public Criteria andPkDormRepairIdLessThanOrEqualTo(String value) {
            addCriterion("pk_dorm_repair_id <=", value, "pkDormRepairId");
            return (Criteria) this;
        }

        public Criteria andPkDormRepairIdLike(String value) {
            addCriterion("pk_dorm_repair_id like", value, "pkDormRepairId");
            return (Criteria) this;
        }

        public Criteria andPkDormRepairIdNotLike(String value) {
            addCriterion("pk_dorm_repair_id not like", value, "pkDormRepairId");
            return (Criteria) this;
        }

        public Criteria andPkDormRepairIdIn(List<String> values) {
            addCriterion("pk_dorm_repair_id in", values, "pkDormRepairId");
            return (Criteria) this;
        }

        public Criteria andPkDormRepairIdNotIn(List<String> values) {
            addCriterion("pk_dorm_repair_id not in", values, "pkDormRepairId");
            return (Criteria) this;
        }

        public Criteria andPkDormRepairIdBetween(String value1, String value2) {
            addCriterion("pk_dorm_repair_id between", value1, value2, "pkDormRepairId");
            return (Criteria) this;
        }

        public Criteria andPkDormRepairIdNotBetween(String value1, String value2) {
            addCriterion("pk_dorm_repair_id not between", value1, value2, "pkDormRepairId");
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

        public Criteria andRepairTypeIsNull() {
            addCriterion("repair_type is null");
            return (Criteria) this;
        }

        public Criteria andRepairTypeIsNotNull() {
            addCriterion("repair_type is not null");
            return (Criteria) this;
        }

        public Criteria andRepairTypeEqualTo(Integer value) {
            addCriterion("repair_type =", value, "repairType");
            return (Criteria) this;
        }

        public Criteria andRepairTypeNotEqualTo(Integer value) {
            addCriterion("repair_type <>", value, "repairType");
            return (Criteria) this;
        }

        public Criteria andRepairTypeGreaterThan(Integer value) {
            addCriterion("repair_type >", value, "repairType");
            return (Criteria) this;
        }

        public Criteria andRepairTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("repair_type >=", value, "repairType");
            return (Criteria) this;
        }

        public Criteria andRepairTypeLessThan(Integer value) {
            addCriterion("repair_type <", value, "repairType");
            return (Criteria) this;
        }

        public Criteria andRepairTypeLessThanOrEqualTo(Integer value) {
            addCriterion("repair_type <=", value, "repairType");
            return (Criteria) this;
        }

        public Criteria andRepairTypeIn(List<Integer> values) {
            addCriterion("repair_type in", values, "repairType");
            return (Criteria) this;
        }

        public Criteria andRepairTypeNotIn(List<Integer> values) {
            addCriterion("repair_type not in", values, "repairType");
            return (Criteria) this;
        }

        public Criteria andRepairTypeBetween(Integer value1, Integer value2) {
            addCriterion("repair_type between", value1, value2, "repairType");
            return (Criteria) this;
        }

        public Criteria andRepairTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("repair_type not between", value1, value2, "repairType");
            return (Criteria) this;
        }

        public Criteria andRepairDescIsNull() {
            addCriterion("repair_desc is null");
            return (Criteria) this;
        }

        public Criteria andRepairDescIsNotNull() {
            addCriterion("repair_desc is not null");
            return (Criteria) this;
        }

        public Criteria andRepairDescEqualTo(String value) {
            addCriterion("repair_desc =", value, "repairDesc");
            return (Criteria) this;
        }

        public Criteria andRepairDescNotEqualTo(String value) {
            addCriterion("repair_desc <>", value, "repairDesc");
            return (Criteria) this;
        }

        public Criteria andRepairDescGreaterThan(String value) {
            addCriterion("repair_desc >", value, "repairDesc");
            return (Criteria) this;
        }

        public Criteria andRepairDescGreaterThanOrEqualTo(String value) {
            addCriterion("repair_desc >=", value, "repairDesc");
            return (Criteria) this;
        }

        public Criteria andRepairDescLessThan(String value) {
            addCriterion("repair_desc <", value, "repairDesc");
            return (Criteria) this;
        }

        public Criteria andRepairDescLessThanOrEqualTo(String value) {
            addCriterion("repair_desc <=", value, "repairDesc");
            return (Criteria) this;
        }

        public Criteria andRepairDescLike(String value) {
            addCriterion("repair_desc like", value, "repairDesc");
            return (Criteria) this;
        }

        public Criteria andRepairDescNotLike(String value) {
            addCriterion("repair_desc not like", value, "repairDesc");
            return (Criteria) this;
        }

        public Criteria andRepairDescIn(List<String> values) {
            addCriterion("repair_desc in", values, "repairDesc");
            return (Criteria) this;
        }

        public Criteria andRepairDescNotIn(List<String> values) {
            addCriterion("repair_desc not in", values, "repairDesc");
            return (Criteria) this;
        }

        public Criteria andRepairDescBetween(String value1, String value2) {
            addCriterion("repair_desc between", value1, value2, "repairDesc");
            return (Criteria) this;
        }

        public Criteria andRepairDescNotBetween(String value1, String value2) {
            addCriterion("repair_desc not between", value1, value2, "repairDesc");
            return (Criteria) this;
        }

        public Criteria andRepairImgUrlIsNull() {
            addCriterion("repair_img_url is null");
            return (Criteria) this;
        }

        public Criteria andRepairImgUrlIsNotNull() {
            addCriterion("repair_img_url is not null");
            return (Criteria) this;
        }

        public Criteria andRepairImgUrlEqualTo(String value) {
            addCriterion("repair_img_url =", value, "repairImgUrl");
            return (Criteria) this;
        }

        public Criteria andRepairImgUrlNotEqualTo(String value) {
            addCriterion("repair_img_url <>", value, "repairImgUrl");
            return (Criteria) this;
        }

        public Criteria andRepairImgUrlGreaterThan(String value) {
            addCriterion("repair_img_url >", value, "repairImgUrl");
            return (Criteria) this;
        }

        public Criteria andRepairImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("repair_img_url >=", value, "repairImgUrl");
            return (Criteria) this;
        }

        public Criteria andRepairImgUrlLessThan(String value) {
            addCriterion("repair_img_url <", value, "repairImgUrl");
            return (Criteria) this;
        }

        public Criteria andRepairImgUrlLessThanOrEqualTo(String value) {
            addCriterion("repair_img_url <=", value, "repairImgUrl");
            return (Criteria) this;
        }

        public Criteria andRepairImgUrlLike(String value) {
            addCriterion("repair_img_url like", value, "repairImgUrl");
            return (Criteria) this;
        }

        public Criteria andRepairImgUrlNotLike(String value) {
            addCriterion("repair_img_url not like", value, "repairImgUrl");
            return (Criteria) this;
        }

        public Criteria andRepairImgUrlIn(List<String> values) {
            addCriterion("repair_img_url in", values, "repairImgUrl");
            return (Criteria) this;
        }

        public Criteria andRepairImgUrlNotIn(List<String> values) {
            addCriterion("repair_img_url not in", values, "repairImgUrl");
            return (Criteria) this;
        }

        public Criteria andRepairImgUrlBetween(String value1, String value2) {
            addCriterion("repair_img_url between", value1, value2, "repairImgUrl");
            return (Criteria) this;
        }

        public Criteria andRepairImgUrlNotBetween(String value1, String value2) {
            addCriterion("repair_img_url not between", value1, value2, "repairImgUrl");
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

        public Criteria andStudentMobileIsNull() {
            addCriterion("student_mobile is null");
            return (Criteria) this;
        }

        public Criteria andStudentMobileIsNotNull() {
            addCriterion("student_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andStudentMobileEqualTo(String value) {
            addCriterion("student_mobile =", value, "studentMobile");
            return (Criteria) this;
        }

        public Criteria andStudentMobileNotEqualTo(String value) {
            addCriterion("student_mobile <>", value, "studentMobile");
            return (Criteria) this;
        }

        public Criteria andStudentMobileGreaterThan(String value) {
            addCriterion("student_mobile >", value, "studentMobile");
            return (Criteria) this;
        }

        public Criteria andStudentMobileGreaterThanOrEqualTo(String value) {
            addCriterion("student_mobile >=", value, "studentMobile");
            return (Criteria) this;
        }

        public Criteria andStudentMobileLessThan(String value) {
            addCriterion("student_mobile <", value, "studentMobile");
            return (Criteria) this;
        }

        public Criteria andStudentMobileLessThanOrEqualTo(String value) {
            addCriterion("student_mobile <=", value, "studentMobile");
            return (Criteria) this;
        }

        public Criteria andStudentMobileLike(String value) {
            addCriterion("student_mobile like", value, "studentMobile");
            return (Criteria) this;
        }

        public Criteria andStudentMobileNotLike(String value) {
            addCriterion("student_mobile not like", value, "studentMobile");
            return (Criteria) this;
        }

        public Criteria andStudentMobileIn(List<String> values) {
            addCriterion("student_mobile in", values, "studentMobile");
            return (Criteria) this;
        }

        public Criteria andStudentMobileNotIn(List<String> values) {
            addCriterion("student_mobile not in", values, "studentMobile");
            return (Criteria) this;
        }

        public Criteria andStudentMobileBetween(String value1, String value2) {
            addCriterion("student_mobile between", value1, value2, "studentMobile");
            return (Criteria) this;
        }

        public Criteria andStudentMobileNotBetween(String value1, String value2) {
            addCriterion("student_mobile not between", value1, value2, "studentMobile");
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

        public Criteria andRepairCostIsNull() {
            addCriterion("repair_cost is null");
            return (Criteria) this;
        }

        public Criteria andRepairCostIsNotNull() {
            addCriterion("repair_cost is not null");
            return (Criteria) this;
        }

        public Criteria andRepairCostEqualTo(BigDecimal value) {
            addCriterion("repair_cost =", value, "repairCost");
            return (Criteria) this;
        }

        public Criteria andRepairCostNotEqualTo(BigDecimal value) {
            addCriterion("repair_cost <>", value, "repairCost");
            return (Criteria) this;
        }

        public Criteria andRepairCostGreaterThan(BigDecimal value) {
            addCriterion("repair_cost >", value, "repairCost");
            return (Criteria) this;
        }

        public Criteria andRepairCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("repair_cost >=", value, "repairCost");
            return (Criteria) this;
        }

        public Criteria andRepairCostLessThan(BigDecimal value) {
            addCriterion("repair_cost <", value, "repairCost");
            return (Criteria) this;
        }

        public Criteria andRepairCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("repair_cost <=", value, "repairCost");
            return (Criteria) this;
        }

        public Criteria andRepairCostIn(List<BigDecimal> values) {
            addCriterion("repair_cost in", values, "repairCost");
            return (Criteria) this;
        }

        public Criteria andRepairCostNotIn(List<BigDecimal> values) {
            addCriterion("repair_cost not in", values, "repairCost");
            return (Criteria) this;
        }

        public Criteria andRepairCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("repair_cost between", value1, value2, "repairCost");
            return (Criteria) this;
        }

        public Criteria andRepairCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("repair_cost not between", value1, value2, "repairCost");
            return (Criteria) this;
        }

        public Criteria andRepairBillUrlIsNull() {
            addCriterion("repair_bill_url is null");
            return (Criteria) this;
        }

        public Criteria andRepairBillUrlIsNotNull() {
            addCriterion("repair_bill_url is not null");
            return (Criteria) this;
        }

        public Criteria andRepairBillUrlEqualTo(String value) {
            addCriterion("repair_bill_url =", value, "repairBillUrl");
            return (Criteria) this;
        }

        public Criteria andRepairBillUrlNotEqualTo(String value) {
            addCriterion("repair_bill_url <>", value, "repairBillUrl");
            return (Criteria) this;
        }

        public Criteria andRepairBillUrlGreaterThan(String value) {
            addCriterion("repair_bill_url >", value, "repairBillUrl");
            return (Criteria) this;
        }

        public Criteria andRepairBillUrlGreaterThanOrEqualTo(String value) {
            addCriterion("repair_bill_url >=", value, "repairBillUrl");
            return (Criteria) this;
        }

        public Criteria andRepairBillUrlLessThan(String value) {
            addCriterion("repair_bill_url <", value, "repairBillUrl");
            return (Criteria) this;
        }

        public Criteria andRepairBillUrlLessThanOrEqualTo(String value) {
            addCriterion("repair_bill_url <=", value, "repairBillUrl");
            return (Criteria) this;
        }

        public Criteria andRepairBillUrlLike(String value) {
            addCriterion("repair_bill_url like", value, "repairBillUrl");
            return (Criteria) this;
        }

        public Criteria andRepairBillUrlNotLike(String value) {
            addCriterion("repair_bill_url not like", value, "repairBillUrl");
            return (Criteria) this;
        }

        public Criteria andRepairBillUrlIn(List<String> values) {
            addCriterion("repair_bill_url in", values, "repairBillUrl");
            return (Criteria) this;
        }

        public Criteria andRepairBillUrlNotIn(List<String> values) {
            addCriterion("repair_bill_url not in", values, "repairBillUrl");
            return (Criteria) this;
        }

        public Criteria andRepairBillUrlBetween(String value1, String value2) {
            addCriterion("repair_bill_url between", value1, value2, "repairBillUrl");
            return (Criteria) this;
        }

        public Criteria andRepairBillUrlNotBetween(String value1, String value2) {
            addCriterion("repair_bill_url not between", value1, value2, "repairBillUrl");
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