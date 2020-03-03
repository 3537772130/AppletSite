package com.applet.common.entity;

import com.applet.common.util.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaleOrderTimelineExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public SaleOrderTimelineExample() {
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

        public Criteria andOrderTimelineIdIsNull() {
            addCriterion("order_timeline_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderTimelineIdIsNotNull() {
            addCriterion("order_timeline_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTimelineIdEqualTo(Integer value) {
            addCriterion("order_timeline_id =", value, "orderTimelineId");
            return (Criteria) this;
        }

        public Criteria andOrderTimelineIdNotEqualTo(Integer value) {
            addCriterion("order_timeline_id <>", value, "orderTimelineId");
            return (Criteria) this;
        }

        public Criteria andOrderTimelineIdGreaterThan(Integer value) {
            addCriterion("order_timeline_id >", value, "orderTimelineId");
            return (Criteria) this;
        }

        public Criteria andOrderTimelineIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_timeline_id >=", value, "orderTimelineId");
            return (Criteria) this;
        }

        public Criteria andOrderTimelineIdLessThan(Integer value) {
            addCriterion("order_timeline_id <", value, "orderTimelineId");
            return (Criteria) this;
        }

        public Criteria andOrderTimelineIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_timeline_id <=", value, "orderTimelineId");
            return (Criteria) this;
        }

        public Criteria andOrderTimelineIdIn(List<Integer> values) {
            addCriterion("order_timeline_id in", values, "orderTimelineId");
            return (Criteria) this;
        }

        public Criteria andOrderTimelineIdNotIn(List<Integer> values) {
            addCriterion("order_timeline_id not in", values, "orderTimelineId");
            return (Criteria) this;
        }

        public Criteria andOrderTimelineIdBetween(Integer value1, Integer value2) {
            addCriterion("order_timeline_id between", value1, value2, "orderTimelineId");
            return (Criteria) this;
        }

        public Criteria andOrderTimelineIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_timeline_id not between", value1, value2, "orderTimelineId");
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

        public Criteria andOrderStatusIsNull() {
            addCriterion("order_status is null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNotNull() {
            addCriterion("order_status is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusEqualTo(Byte value) {
            addCriterion("order_status =", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotEqualTo(Byte value) {
            addCriterion("order_status <>", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThan(Byte value) {
            addCriterion("order_status >", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("order_status >=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThan(Byte value) {
            addCriterion("order_status <", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThanOrEqualTo(Byte value) {
            addCriterion("order_status <=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIn(List<Byte> values) {
            addCriterion("order_status in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotIn(List<Byte> values) {
            addCriterion("order_status not in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusBetween(Byte value1, Byte value2) {
            addCriterion("order_status between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("order_status not between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusCnIsNull() {
            addCriterion("order_status_cn is null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusCnIsNotNull() {
            addCriterion("order_status_cn is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusCnEqualTo(String value) {
            addCriterion("order_status_cn =", value, "orderStatusCn");
            return (Criteria) this;
        }

        public Criteria andOrderStatusCnNotEqualTo(String value) {
            addCriterion("order_status_cn <>", value, "orderStatusCn");
            return (Criteria) this;
        }

        public Criteria andOrderStatusCnGreaterThan(String value) {
            addCriterion("order_status_cn >", value, "orderStatusCn");
            return (Criteria) this;
        }

        public Criteria andOrderStatusCnGreaterThanOrEqualTo(String value) {
            addCriterion("order_status_cn >=", value, "orderStatusCn");
            return (Criteria) this;
        }

        public Criteria andOrderStatusCnLessThan(String value) {
            addCriterion("order_status_cn <", value, "orderStatusCn");
            return (Criteria) this;
        }

        public Criteria andOrderStatusCnLessThanOrEqualTo(String value) {
            addCriterion("order_status_cn <=", value, "orderStatusCn");
            return (Criteria) this;
        }

        public Criteria andOrderStatusCnLike(String value) {
            addCriterion("order_status_cn like", value, "orderStatusCn");
            return (Criteria) this;
        }

        public Criteria andOrderStatusCnNotLike(String value) {
            addCriterion("order_status_cn not like", value, "orderStatusCn");
            return (Criteria) this;
        }

        public Criteria andOrderStatusCnIn(List<String> values) {
            addCriterion("order_status_cn in", values, "orderStatusCn");
            return (Criteria) this;
        }

        public Criteria andOrderStatusCnNotIn(List<String> values) {
            addCriterion("order_status_cn not in", values, "orderStatusCn");
            return (Criteria) this;
        }

        public Criteria andOrderStatusCnBetween(String value1, String value2) {
            addCriterion("order_status_cn between", value1, value2, "orderStatusCn");
            return (Criteria) this;
        }

        public Criteria andOrderStatusCnNotBetween(String value1, String value2) {
            addCriterion("order_status_cn not between", value1, value2, "orderStatusCn");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedIsNull() {
            addCriterion("gmt_created is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedIsNotNull() {
            addCriterion("gmt_created is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedEqualTo(Date value) {
            addCriterion("gmt_created =", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedNotEqualTo(Date value) {
            addCriterion("gmt_created <>", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedGreaterThan(Date value) {
            addCriterion("gmt_created >", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_created >=", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedLessThan(Date value) {
            addCriterion("gmt_created <", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_created <=", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedIn(List<Date> values) {
            addCriterion("gmt_created in", values, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedNotIn(List<Date> values) {
            addCriterion("gmt_created not in", values, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedBetween(Date value1, Date value2) {
            addCriterion("gmt_created between", value1, value2, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_created not between", value1, value2, "gmtCreated");
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