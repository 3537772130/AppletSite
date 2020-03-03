package com.applet.common.entity;

import com.applet.common.util.Page;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ViewStoreUserOrderCountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public ViewStoreUserOrderCountExample() {
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

        public Criteria andStoreUserIdIsNull() {
            addCriterion("store_user_id is null");
            return (Criteria) this;
        }

        public Criteria andStoreUserIdIsNotNull() {
            addCriterion("store_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andStoreUserIdEqualTo(Integer value) {
            addCriterion("store_user_id =", value, "storeUserId");
            return (Criteria) this;
        }

        public Criteria andStoreUserIdNotEqualTo(Integer value) {
            addCriterion("store_user_id <>", value, "storeUserId");
            return (Criteria) this;
        }

        public Criteria andStoreUserIdGreaterThan(Integer value) {
            addCriterion("store_user_id >", value, "storeUserId");
            return (Criteria) this;
        }

        public Criteria andStoreUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("store_user_id >=", value, "storeUserId");
            return (Criteria) this;
        }

        public Criteria andStoreUserIdLessThan(Integer value) {
            addCriterion("store_user_id <", value, "storeUserId");
            return (Criteria) this;
        }

        public Criteria andStoreUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("store_user_id <=", value, "storeUserId");
            return (Criteria) this;
        }

        public Criteria andStoreUserIdIn(List<Integer> values) {
            addCriterion("store_user_id in", values, "storeUserId");
            return (Criteria) this;
        }

        public Criteria andStoreUserIdNotIn(List<Integer> values) {
            addCriterion("store_user_id not in", values, "storeUserId");
            return (Criteria) this;
        }

        public Criteria andStoreUserIdBetween(Integer value1, Integer value2) {
            addCriterion("store_user_id between", value1, value2, "storeUserId");
            return (Criteria) this;
        }

        public Criteria andStoreUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("store_user_id not between", value1, value2, "storeUserId");
            return (Criteria) this;
        }

        public Criteria andCancelCountIsNull() {
            addCriterion("cancel_count is null");
            return (Criteria) this;
        }

        public Criteria andCancelCountIsNotNull() {
            addCriterion("cancel_count is not null");
            return (Criteria) this;
        }

        public Criteria andCancelCountEqualTo(BigDecimal value) {
            addCriterion("cancel_count =", value, "cancelCount");
            return (Criteria) this;
        }

        public Criteria andCancelCountNotEqualTo(BigDecimal value) {
            addCriterion("cancel_count <>", value, "cancelCount");
            return (Criteria) this;
        }

        public Criteria andCancelCountGreaterThan(BigDecimal value) {
            addCriterion("cancel_count >", value, "cancelCount");
            return (Criteria) this;
        }

        public Criteria andCancelCountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cancel_count >=", value, "cancelCount");
            return (Criteria) this;
        }

        public Criteria andCancelCountLessThan(BigDecimal value) {
            addCriterion("cancel_count <", value, "cancelCount");
            return (Criteria) this;
        }

        public Criteria andCancelCountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cancel_count <=", value, "cancelCount");
            return (Criteria) this;
        }

        public Criteria andCancelCountIn(List<BigDecimal> values) {
            addCriterion("cancel_count in", values, "cancelCount");
            return (Criteria) this;
        }

        public Criteria andCancelCountNotIn(List<BigDecimal> values) {
            addCriterion("cancel_count not in", values, "cancelCount");
            return (Criteria) this;
        }

        public Criteria andCancelCountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cancel_count between", value1, value2, "cancelCount");
            return (Criteria) this;
        }

        public Criteria andCancelCountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cancel_count not between", value1, value2, "cancelCount");
            return (Criteria) this;
        }

        public Criteria andWaitCountIsNull() {
            addCriterion("wait_count is null");
            return (Criteria) this;
        }

        public Criteria andWaitCountIsNotNull() {
            addCriterion("wait_count is not null");
            return (Criteria) this;
        }

        public Criteria andWaitCountEqualTo(BigDecimal value) {
            addCriterion("wait_count =", value, "waitCount");
            return (Criteria) this;
        }

        public Criteria andWaitCountNotEqualTo(BigDecimal value) {
            addCriterion("wait_count <>", value, "waitCount");
            return (Criteria) this;
        }

        public Criteria andWaitCountGreaterThan(BigDecimal value) {
            addCriterion("wait_count >", value, "waitCount");
            return (Criteria) this;
        }

        public Criteria andWaitCountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("wait_count >=", value, "waitCount");
            return (Criteria) this;
        }

        public Criteria andWaitCountLessThan(BigDecimal value) {
            addCriterion("wait_count <", value, "waitCount");
            return (Criteria) this;
        }

        public Criteria andWaitCountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("wait_count <=", value, "waitCount");
            return (Criteria) this;
        }

        public Criteria andWaitCountIn(List<BigDecimal> values) {
            addCriterion("wait_count in", values, "waitCount");
            return (Criteria) this;
        }

        public Criteria andWaitCountNotIn(List<BigDecimal> values) {
            addCriterion("wait_count not in", values, "waitCount");
            return (Criteria) this;
        }

        public Criteria andWaitCountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wait_count between", value1, value2, "waitCount");
            return (Criteria) this;
        }

        public Criteria andWaitCountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wait_count not between", value1, value2, "waitCount");
            return (Criteria) this;
        }

        public Criteria andRefuseCountIsNull() {
            addCriterion("refuse_count is null");
            return (Criteria) this;
        }

        public Criteria andRefuseCountIsNotNull() {
            addCriterion("refuse_count is not null");
            return (Criteria) this;
        }

        public Criteria andRefuseCountEqualTo(BigDecimal value) {
            addCriterion("refuse_count =", value, "refuseCount");
            return (Criteria) this;
        }

        public Criteria andRefuseCountNotEqualTo(BigDecimal value) {
            addCriterion("refuse_count <>", value, "refuseCount");
            return (Criteria) this;
        }

        public Criteria andRefuseCountGreaterThan(BigDecimal value) {
            addCriterion("refuse_count >", value, "refuseCount");
            return (Criteria) this;
        }

        public Criteria andRefuseCountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refuse_count >=", value, "refuseCount");
            return (Criteria) this;
        }

        public Criteria andRefuseCountLessThan(BigDecimal value) {
            addCriterion("refuse_count <", value, "refuseCount");
            return (Criteria) this;
        }

        public Criteria andRefuseCountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refuse_count <=", value, "refuseCount");
            return (Criteria) this;
        }

        public Criteria andRefuseCountIn(List<BigDecimal> values) {
            addCriterion("refuse_count in", values, "refuseCount");
            return (Criteria) this;
        }

        public Criteria andRefuseCountNotIn(List<BigDecimal> values) {
            addCriterion("refuse_count not in", values, "refuseCount");
            return (Criteria) this;
        }

        public Criteria andRefuseCountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refuse_count between", value1, value2, "refuseCount");
            return (Criteria) this;
        }

        public Criteria andRefuseCountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refuse_count not between", value1, value2, "refuseCount");
            return (Criteria) this;
        }

        public Criteria andAcceptCountIsNull() {
            addCriterion("accept_count is null");
            return (Criteria) this;
        }

        public Criteria andAcceptCountIsNotNull() {
            addCriterion("accept_count is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptCountEqualTo(BigDecimal value) {
            addCriterion("accept_count =", value, "acceptCount");
            return (Criteria) this;
        }

        public Criteria andAcceptCountNotEqualTo(BigDecimal value) {
            addCriterion("accept_count <>", value, "acceptCount");
            return (Criteria) this;
        }

        public Criteria andAcceptCountGreaterThan(BigDecimal value) {
            addCriterion("accept_count >", value, "acceptCount");
            return (Criteria) this;
        }

        public Criteria andAcceptCountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("accept_count >=", value, "acceptCount");
            return (Criteria) this;
        }

        public Criteria andAcceptCountLessThan(BigDecimal value) {
            addCriterion("accept_count <", value, "acceptCount");
            return (Criteria) this;
        }

        public Criteria andAcceptCountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("accept_count <=", value, "acceptCount");
            return (Criteria) this;
        }

        public Criteria andAcceptCountIn(List<BigDecimal> values) {
            addCriterion("accept_count in", values, "acceptCount");
            return (Criteria) this;
        }

        public Criteria andAcceptCountNotIn(List<BigDecimal> values) {
            addCriterion("accept_count not in", values, "acceptCount");
            return (Criteria) this;
        }

        public Criteria andAcceptCountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("accept_count between", value1, value2, "acceptCount");
            return (Criteria) this;
        }

        public Criteria andAcceptCountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("accept_count not between", value1, value2, "acceptCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountIsNull() {
            addCriterion("deliver_count is null");
            return (Criteria) this;
        }

        public Criteria andDeliverCountIsNotNull() {
            addCriterion("deliver_count is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverCountEqualTo(BigDecimal value) {
            addCriterion("deliver_count =", value, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountNotEqualTo(BigDecimal value) {
            addCriterion("deliver_count <>", value, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountGreaterThan(BigDecimal value) {
            addCriterion("deliver_count >", value, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deliver_count >=", value, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountLessThan(BigDecimal value) {
            addCriterion("deliver_count <", value, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deliver_count <=", value, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountIn(List<BigDecimal> values) {
            addCriterion("deliver_count in", values, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountNotIn(List<BigDecimal> values) {
            addCriterion("deliver_count not in", values, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deliver_count between", value1, value2, "deliverCount");
            return (Criteria) this;
        }

        public Criteria andDeliverCountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deliver_count not between", value1, value2, "deliverCount");
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