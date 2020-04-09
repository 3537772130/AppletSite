package com.applet.common.entity;

import com.applet.common.util.Page;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ViewUserOrderCountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public ViewUserOrderCountExample() {
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

    public void setPage(Page page) {
        this.page=page;
    }

    public Page getPage() {
        return page;
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andIncompleteCountIsNull() {
            addCriterion("incomplete_count is null");
            return (Criteria) this;
        }

        public Criteria andIncompleteCountIsNotNull() {
            addCriterion("incomplete_count is not null");
            return (Criteria) this;
        }

        public Criteria andIncompleteCountEqualTo(BigDecimal value) {
            addCriterion("incomplete_count =", value, "incompleteCount");
            return (Criteria) this;
        }

        public Criteria andIncompleteCountNotEqualTo(BigDecimal value) {
            addCriterion("incomplete_count <>", value, "incompleteCount");
            return (Criteria) this;
        }

        public Criteria andIncompleteCountGreaterThan(BigDecimal value) {
            addCriterion("incomplete_count >", value, "incompleteCount");
            return (Criteria) this;
        }

        public Criteria andIncompleteCountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("incomplete_count >=", value, "incompleteCount");
            return (Criteria) this;
        }

        public Criteria andIncompleteCountLessThan(BigDecimal value) {
            addCriterion("incomplete_count <", value, "incompleteCount");
            return (Criteria) this;
        }

        public Criteria andIncompleteCountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("incomplete_count <=", value, "incompleteCount");
            return (Criteria) this;
        }

        public Criteria andIncompleteCountIn(List<BigDecimal> values) {
            addCriterion("incomplete_count in", values, "incompleteCount");
            return (Criteria) this;
        }

        public Criteria andIncompleteCountNotIn(List<BigDecimal> values) {
            addCriterion("incomplete_count not in", values, "incompleteCount");
            return (Criteria) this;
        }

        public Criteria andIncompleteCountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("incomplete_count between", value1, value2, "incompleteCount");
            return (Criteria) this;
        }

        public Criteria andIncompleteCountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("incomplete_count not between", value1, value2, "incompleteCount");
            return (Criteria) this;
        }

        public Criteria andWaitMeetCountIsNull() {
            addCriterion("wait_meet_count is null");
            return (Criteria) this;
        }

        public Criteria andWaitMeetCountIsNotNull() {
            addCriterion("wait_meet_count is not null");
            return (Criteria) this;
        }

        public Criteria andWaitMeetCountEqualTo(BigDecimal value) {
            addCriterion("wait_meet_count =", value, "waitMeetCount");
            return (Criteria) this;
        }

        public Criteria andWaitMeetCountNotEqualTo(BigDecimal value) {
            addCriterion("wait_meet_count <>", value, "waitMeetCount");
            return (Criteria) this;
        }

        public Criteria andWaitMeetCountGreaterThan(BigDecimal value) {
            addCriterion("wait_meet_count >", value, "waitMeetCount");
            return (Criteria) this;
        }

        public Criteria andWaitMeetCountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("wait_meet_count >=", value, "waitMeetCount");
            return (Criteria) this;
        }

        public Criteria andWaitMeetCountLessThan(BigDecimal value) {
            addCriterion("wait_meet_count <", value, "waitMeetCount");
            return (Criteria) this;
        }

        public Criteria andWaitMeetCountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("wait_meet_count <=", value, "waitMeetCount");
            return (Criteria) this;
        }

        public Criteria andWaitMeetCountIn(List<BigDecimal> values) {
            addCriterion("wait_meet_count in", values, "waitMeetCount");
            return (Criteria) this;
        }

        public Criteria andWaitMeetCountNotIn(List<BigDecimal> values) {
            addCriterion("wait_meet_count not in", values, "waitMeetCount");
            return (Criteria) this;
        }

        public Criteria andWaitMeetCountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wait_meet_count between", value1, value2, "waitMeetCount");
            return (Criteria) this;
        }

        public Criteria andWaitMeetCountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wait_meet_count not between", value1, value2, "waitMeetCount");
            return (Criteria) this;
        }

        public Criteria andWaitCollectCountIsNull() {
            addCriterion("wait_collect_count is null");
            return (Criteria) this;
        }

        public Criteria andWaitCollectCountIsNotNull() {
            addCriterion("wait_collect_count is not null");
            return (Criteria) this;
        }

        public Criteria andWaitCollectCountEqualTo(BigDecimal value) {
            addCriterion("wait_collect_count =", value, "waitCollectCount");
            return (Criteria) this;
        }

        public Criteria andWaitCollectCountNotEqualTo(BigDecimal value) {
            addCriterion("wait_collect_count <>", value, "waitCollectCount");
            return (Criteria) this;
        }

        public Criteria andWaitCollectCountGreaterThan(BigDecimal value) {
            addCriterion("wait_collect_count >", value, "waitCollectCount");
            return (Criteria) this;
        }

        public Criteria andWaitCollectCountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("wait_collect_count >=", value, "waitCollectCount");
            return (Criteria) this;
        }

        public Criteria andWaitCollectCountLessThan(BigDecimal value) {
            addCriterion("wait_collect_count <", value, "waitCollectCount");
            return (Criteria) this;
        }

        public Criteria andWaitCollectCountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("wait_collect_count <=", value, "waitCollectCount");
            return (Criteria) this;
        }

        public Criteria andWaitCollectCountIn(List<BigDecimal> values) {
            addCriterion("wait_collect_count in", values, "waitCollectCount");
            return (Criteria) this;
        }

        public Criteria andWaitCollectCountNotIn(List<BigDecimal> values) {
            addCriterion("wait_collect_count not in", values, "waitCollectCount");
            return (Criteria) this;
        }

        public Criteria andWaitCollectCountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wait_collect_count between", value1, value2, "waitCollectCount");
            return (Criteria) this;
        }

        public Criteria andWaitCollectCountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wait_collect_count not between", value1, value2, "waitCollectCount");
            return (Criteria) this;
        }

        public Criteria andFinishCountIsNull() {
            addCriterion("finish_count is null");
            return (Criteria) this;
        }

        public Criteria andFinishCountIsNotNull() {
            addCriterion("finish_count is not null");
            return (Criteria) this;
        }

        public Criteria andFinishCountEqualTo(BigDecimal value) {
            addCriterion("finish_count =", value, "finishCount");
            return (Criteria) this;
        }

        public Criteria andFinishCountNotEqualTo(BigDecimal value) {
            addCriterion("finish_count <>", value, "finishCount");
            return (Criteria) this;
        }

        public Criteria andFinishCountGreaterThan(BigDecimal value) {
            addCriterion("finish_count >", value, "finishCount");
            return (Criteria) this;
        }

        public Criteria andFinishCountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("finish_count >=", value, "finishCount");
            return (Criteria) this;
        }

        public Criteria andFinishCountLessThan(BigDecimal value) {
            addCriterion("finish_count <", value, "finishCount");
            return (Criteria) this;
        }

        public Criteria andFinishCountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("finish_count <=", value, "finishCount");
            return (Criteria) this;
        }

        public Criteria andFinishCountIn(List<BigDecimal> values) {
            addCriterion("finish_count in", values, "finishCount");
            return (Criteria) this;
        }

        public Criteria andFinishCountNotIn(List<BigDecimal> values) {
            addCriterion("finish_count not in", values, "finishCount");
            return (Criteria) this;
        }

        public Criteria andFinishCountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("finish_count between", value1, value2, "finishCount");
            return (Criteria) this;
        }

        public Criteria andFinishCountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("finish_count not between", value1, value2, "finishCount");
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