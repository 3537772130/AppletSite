package com.applet.common.entity;

import com.applet.common.util.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppletPageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public AppletPageExample() {
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

        public Criteria andFileIdIsNull() {
            addCriterion("file_id is null");
            return (Criteria) this;
        }

        public Criteria andFileIdIsNotNull() {
            addCriterion("file_id is not null");
            return (Criteria) this;
        }

        public Criteria andFileIdEqualTo(Integer value) {
            addCriterion("file_id =", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotEqualTo(Integer value) {
            addCriterion("file_id <>", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdGreaterThan(Integer value) {
            addCriterion("file_id >", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("file_id >=", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLessThan(Integer value) {
            addCriterion("file_id <", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLessThanOrEqualTo(Integer value) {
            addCriterion("file_id <=", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdIn(List<Integer> values) {
            addCriterion("file_id in", values, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotIn(List<Integer> values) {
            addCriterion("file_id not in", values, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdBetween(Integer value1, Integer value2) {
            addCriterion("file_id between", value1, value2, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotBetween(Integer value1, Integer value2) {
            addCriterion("file_id not between", value1, value2, "fileId");
            return (Criteria) this;
        }

        public Criteria andPageLogoIsNull() {
            addCriterion("page_logo is null");
            return (Criteria) this;
        }

        public Criteria andPageLogoIsNotNull() {
            addCriterion("page_logo is not null");
            return (Criteria) this;
        }

        public Criteria andPageLogoEqualTo(String value) {
            addCriterion("page_logo =", value, "pageLogo");
            return (Criteria) this;
        }

        public Criteria andPageLogoNotEqualTo(String value) {
            addCriterion("page_logo <>", value, "pageLogo");
            return (Criteria) this;
        }

        public Criteria andPageLogoGreaterThan(String value) {
            addCriterion("page_logo >", value, "pageLogo");
            return (Criteria) this;
        }

        public Criteria andPageLogoGreaterThanOrEqualTo(String value) {
            addCriterion("page_logo >=", value, "pageLogo");
            return (Criteria) this;
        }

        public Criteria andPageLogoLessThan(String value) {
            addCriterion("page_logo <", value, "pageLogo");
            return (Criteria) this;
        }

        public Criteria andPageLogoLessThanOrEqualTo(String value) {
            addCriterion("page_logo <=", value, "pageLogo");
            return (Criteria) this;
        }

        public Criteria andPageLogoLike(String value) {
            addCriterion("page_logo like", value, "pageLogo");
            return (Criteria) this;
        }

        public Criteria andPageLogoNotLike(String value) {
            addCriterion("page_logo not like", value, "pageLogo");
            return (Criteria) this;
        }

        public Criteria andPageLogoIn(List<String> values) {
            addCriterion("page_logo in", values, "pageLogo");
            return (Criteria) this;
        }

        public Criteria andPageLogoNotIn(List<String> values) {
            addCriterion("page_logo not in", values, "pageLogo");
            return (Criteria) this;
        }

        public Criteria andPageLogoBetween(String value1, String value2) {
            addCriterion("page_logo between", value1, value2, "pageLogo");
            return (Criteria) this;
        }

        public Criteria andPageLogoNotBetween(String value1, String value2) {
            addCriterion("page_logo not between", value1, value2, "pageLogo");
            return (Criteria) this;
        }

        public Criteria andPageNameIsNull() {
            addCriterion("page_name is null");
            return (Criteria) this;
        }

        public Criteria andPageNameIsNotNull() {
            addCriterion("page_name is not null");
            return (Criteria) this;
        }

        public Criteria andPageNameEqualTo(String value) {
            addCriterion("page_name =", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameNotEqualTo(String value) {
            addCriterion("page_name <>", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameGreaterThan(String value) {
            addCriterion("page_name >", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameGreaterThanOrEqualTo(String value) {
            addCriterion("page_name >=", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameLessThan(String value) {
            addCriterion("page_name <", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameLessThanOrEqualTo(String value) {
            addCriterion("page_name <=", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameLike(String value) {
            addCriterion("page_name like", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameNotLike(String value) {
            addCriterion("page_name not like", value, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameIn(List<String> values) {
            addCriterion("page_name in", values, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameNotIn(List<String> values) {
            addCriterion("page_name not in", values, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameBetween(String value1, String value2) {
            addCriterion("page_name between", value1, value2, "pageName");
            return (Criteria) this;
        }

        public Criteria andPageNameNotBetween(String value1, String value2) {
            addCriterion("page_name not between", value1, value2, "pageName");
            return (Criteria) this;
        }

        public Criteria andIfEditIsNull() {
            addCriterion("if_edit is null");
            return (Criteria) this;
        }

        public Criteria andIfEditIsNotNull() {
            addCriterion("if_edit is not null");
            return (Criteria) this;
        }

        public Criteria andIfEditEqualTo(Boolean value) {
            addCriterion("if_edit =", value, "ifEdit");
            return (Criteria) this;
        }

        public Criteria andIfEditNotEqualTo(Boolean value) {
            addCriterion("if_edit <>", value, "ifEdit");
            return (Criteria) this;
        }

        public Criteria andIfEditGreaterThan(Boolean value) {
            addCriterion("if_edit >", value, "ifEdit");
            return (Criteria) this;
        }

        public Criteria andIfEditGreaterThanOrEqualTo(Boolean value) {
            addCriterion("if_edit >=", value, "ifEdit");
            return (Criteria) this;
        }

        public Criteria andIfEditLessThan(Boolean value) {
            addCriterion("if_edit <", value, "ifEdit");
            return (Criteria) this;
        }

        public Criteria andIfEditLessThanOrEqualTo(Boolean value) {
            addCriterion("if_edit <=", value, "ifEdit");
            return (Criteria) this;
        }

        public Criteria andIfEditIn(List<Boolean> values) {
            addCriterion("if_edit in", values, "ifEdit");
            return (Criteria) this;
        }

        public Criteria andIfEditNotIn(List<Boolean> values) {
            addCriterion("if_edit not in", values, "ifEdit");
            return (Criteria) this;
        }

        public Criteria andIfEditBetween(Boolean value1, Boolean value2) {
            addCriterion("if_edit between", value1, value2, "ifEdit");
            return (Criteria) this;
        }

        public Criteria andIfEditNotBetween(Boolean value1, Boolean value2) {
            addCriterion("if_edit not between", value1, value2, "ifEdit");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andPageStatusIsNull() {
            addCriterion("page_status is null");
            return (Criteria) this;
        }

        public Criteria andPageStatusIsNotNull() {
            addCriterion("page_status is not null");
            return (Criteria) this;
        }

        public Criteria andPageStatusEqualTo(Boolean value) {
            addCriterion("page_status =", value, "pageStatus");
            return (Criteria) this;
        }

        public Criteria andPageStatusNotEqualTo(Boolean value) {
            addCriterion("page_status <>", value, "pageStatus");
            return (Criteria) this;
        }

        public Criteria andPageStatusGreaterThan(Boolean value) {
            addCriterion("page_status >", value, "pageStatus");
            return (Criteria) this;
        }

        public Criteria andPageStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("page_status >=", value, "pageStatus");
            return (Criteria) this;
        }

        public Criteria andPageStatusLessThan(Boolean value) {
            addCriterion("page_status <", value, "pageStatus");
            return (Criteria) this;
        }

        public Criteria andPageStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("page_status <=", value, "pageStatus");
            return (Criteria) this;
        }

        public Criteria andPageStatusIn(List<Boolean> values) {
            addCriterion("page_status in", values, "pageStatus");
            return (Criteria) this;
        }

        public Criteria andPageStatusNotIn(List<Boolean> values) {
            addCriterion("page_status not in", values, "pageStatus");
            return (Criteria) this;
        }

        public Criteria andPageStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("page_status between", value1, value2, "pageStatus");
            return (Criteria) this;
        }

        public Criteria andPageStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("page_status not between", value1, value2, "pageStatus");
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