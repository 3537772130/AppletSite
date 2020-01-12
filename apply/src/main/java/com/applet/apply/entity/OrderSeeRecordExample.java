package com.applet.apply.entity;

import com.applet.common.util.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderSeeRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public OrderSeeRecordExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Integer value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Integer value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Integer value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Integer value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Integer> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Integer> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andUserSeeStatusIsNull() {
            addCriterion("user_see_status is null");
            return (Criteria) this;
        }

        public Criteria andUserSeeStatusIsNotNull() {
            addCriterion("user_see_status is not null");
            return (Criteria) this;
        }

        public Criteria andUserSeeStatusEqualTo(Boolean value) {
            addCriterion("user_see_status =", value, "userSeeStatus");
            return (Criteria) this;
        }

        public Criteria andUserSeeStatusNotEqualTo(Boolean value) {
            addCriterion("user_see_status <>", value, "userSeeStatus");
            return (Criteria) this;
        }

        public Criteria andUserSeeStatusGreaterThan(Boolean value) {
            addCriterion("user_see_status >", value, "userSeeStatus");
            return (Criteria) this;
        }

        public Criteria andUserSeeStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("user_see_status >=", value, "userSeeStatus");
            return (Criteria) this;
        }

        public Criteria andUserSeeStatusLessThan(Boolean value) {
            addCriterion("user_see_status <", value, "userSeeStatus");
            return (Criteria) this;
        }

        public Criteria andUserSeeStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("user_see_status <=", value, "userSeeStatus");
            return (Criteria) this;
        }

        public Criteria andUserSeeStatusIn(List<Boolean> values) {
            addCriterion("user_see_status in", values, "userSeeStatus");
            return (Criteria) this;
        }

        public Criteria andUserSeeStatusNotIn(List<Boolean> values) {
            addCriterion("user_see_status not in", values, "userSeeStatus");
            return (Criteria) this;
        }

        public Criteria andUserSeeStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("user_see_status between", value1, value2, "userSeeStatus");
            return (Criteria) this;
        }

        public Criteria andUserSeeStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("user_see_status not between", value1, value2, "userSeeStatus");
            return (Criteria) this;
        }

        public Criteria andUserSeeTimeIsNull() {
            addCriterion("user_see_time is null");
            return (Criteria) this;
        }

        public Criteria andUserSeeTimeIsNotNull() {
            addCriterion("user_see_time is not null");
            return (Criteria) this;
        }

        public Criteria andUserSeeTimeEqualTo(Date value) {
            addCriterion("user_see_time =", value, "userSeeTime");
            return (Criteria) this;
        }

        public Criteria andUserSeeTimeNotEqualTo(Date value) {
            addCriterion("user_see_time <>", value, "userSeeTime");
            return (Criteria) this;
        }

        public Criteria andUserSeeTimeGreaterThan(Date value) {
            addCriterion("user_see_time >", value, "userSeeTime");
            return (Criteria) this;
        }

        public Criteria andUserSeeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("user_see_time >=", value, "userSeeTime");
            return (Criteria) this;
        }

        public Criteria andUserSeeTimeLessThan(Date value) {
            addCriterion("user_see_time <", value, "userSeeTime");
            return (Criteria) this;
        }

        public Criteria andUserSeeTimeLessThanOrEqualTo(Date value) {
            addCriterion("user_see_time <=", value, "userSeeTime");
            return (Criteria) this;
        }

        public Criteria andUserSeeTimeIn(List<Date> values) {
            addCriterion("user_see_time in", values, "userSeeTime");
            return (Criteria) this;
        }

        public Criteria andUserSeeTimeNotIn(List<Date> values) {
            addCriterion("user_see_time not in", values, "userSeeTime");
            return (Criteria) this;
        }

        public Criteria andUserSeeTimeBetween(Date value1, Date value2) {
            addCriterion("user_see_time between", value1, value2, "userSeeTime");
            return (Criteria) this;
        }

        public Criteria andUserSeeTimeNotBetween(Date value1, Date value2) {
            addCriterion("user_see_time not between", value1, value2, "userSeeTime");
            return (Criteria) this;
        }

        public Criteria andStoreSeeStatusIsNull() {
            addCriterion("store_see_status is null");
            return (Criteria) this;
        }

        public Criteria andStoreSeeStatusIsNotNull() {
            addCriterion("store_see_status is not null");
            return (Criteria) this;
        }

        public Criteria andStoreSeeStatusEqualTo(Boolean value) {
            addCriterion("store_see_status =", value, "storeSeeStatus");
            return (Criteria) this;
        }

        public Criteria andStoreSeeStatusNotEqualTo(Boolean value) {
            addCriterion("store_see_status <>", value, "storeSeeStatus");
            return (Criteria) this;
        }

        public Criteria andStoreSeeStatusGreaterThan(Boolean value) {
            addCriterion("store_see_status >", value, "storeSeeStatus");
            return (Criteria) this;
        }

        public Criteria andStoreSeeStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("store_see_status >=", value, "storeSeeStatus");
            return (Criteria) this;
        }

        public Criteria andStoreSeeStatusLessThan(Boolean value) {
            addCriterion("store_see_status <", value, "storeSeeStatus");
            return (Criteria) this;
        }

        public Criteria andStoreSeeStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("store_see_status <=", value, "storeSeeStatus");
            return (Criteria) this;
        }

        public Criteria andStoreSeeStatusIn(List<Boolean> values) {
            addCriterion("store_see_status in", values, "storeSeeStatus");
            return (Criteria) this;
        }

        public Criteria andStoreSeeStatusNotIn(List<Boolean> values) {
            addCriterion("store_see_status not in", values, "storeSeeStatus");
            return (Criteria) this;
        }

        public Criteria andStoreSeeStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("store_see_status between", value1, value2, "storeSeeStatus");
            return (Criteria) this;
        }

        public Criteria andStoreSeeStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("store_see_status not between", value1, value2, "storeSeeStatus");
            return (Criteria) this;
        }

        public Criteria andStoreSeeTimeIsNull() {
            addCriterion("store_see_time is null");
            return (Criteria) this;
        }

        public Criteria andStoreSeeTimeIsNotNull() {
            addCriterion("store_see_time is not null");
            return (Criteria) this;
        }

        public Criteria andStoreSeeTimeEqualTo(Date value) {
            addCriterion("store_see_time =", value, "storeSeeTime");
            return (Criteria) this;
        }

        public Criteria andStoreSeeTimeNotEqualTo(Date value) {
            addCriterion("store_see_time <>", value, "storeSeeTime");
            return (Criteria) this;
        }

        public Criteria andStoreSeeTimeGreaterThan(Date value) {
            addCriterion("store_see_time >", value, "storeSeeTime");
            return (Criteria) this;
        }

        public Criteria andStoreSeeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("store_see_time >=", value, "storeSeeTime");
            return (Criteria) this;
        }

        public Criteria andStoreSeeTimeLessThan(Date value) {
            addCriterion("store_see_time <", value, "storeSeeTime");
            return (Criteria) this;
        }

        public Criteria andStoreSeeTimeLessThanOrEqualTo(Date value) {
            addCriterion("store_see_time <=", value, "storeSeeTime");
            return (Criteria) this;
        }

        public Criteria andStoreSeeTimeIn(List<Date> values) {
            addCriterion("store_see_time in", values, "storeSeeTime");
            return (Criteria) this;
        }

        public Criteria andStoreSeeTimeNotIn(List<Date> values) {
            addCriterion("store_see_time not in", values, "storeSeeTime");
            return (Criteria) this;
        }

        public Criteria andStoreSeeTimeBetween(Date value1, Date value2) {
            addCriterion("store_see_time between", value1, value2, "storeSeeTime");
            return (Criteria) this;
        }

        public Criteria andStoreSeeTimeNotBetween(Date value1, Date value2) {
            addCriterion("store_see_time not between", value1, value2, "storeSeeTime");
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