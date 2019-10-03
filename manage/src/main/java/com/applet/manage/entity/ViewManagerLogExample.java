package com.applet.manage.entity;

import com.applet.manage.util.Page;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewManagerLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public ViewManagerLogExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
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

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
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

        public Criteria andManagerIdIsNull() {
            addCriterion("manager_id is null");
            return (Criteria) this;
        }

        public Criteria andManagerIdIsNotNull() {
            addCriterion("manager_id is not null");
            return (Criteria) this;
        }

        public Criteria andManagerIdEqualTo(Integer value) {
            addCriterion("manager_id =", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotEqualTo(Integer value) {
            addCriterion("manager_id <>", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdGreaterThan(Integer value) {
            addCriterion("manager_id >", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("manager_id >=", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdLessThan(Integer value) {
            addCriterion("manager_id <", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdLessThanOrEqualTo(Integer value) {
            addCriterion("manager_id <=", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdIn(List<Integer> values) {
            addCriterion("manager_id in", values, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotIn(List<Integer> values) {
            addCriterion("manager_id not in", values, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdBetween(Integer value1, Integer value2) {
            addCriterion("manager_id between", value1, value2, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("manager_id not between", value1, value2, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerUserNameIsNull() {
            addCriterion("manager_user_name is null");
            return (Criteria) this;
        }

        public Criteria andManagerUserNameIsNotNull() {
            addCriterion("manager_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andManagerUserNameEqualTo(String value) {
            addCriterion("manager_user_name =", value, "managerUserName");
            return (Criteria) this;
        }

        public Criteria andManagerUserNameNotEqualTo(String value) {
            addCriterion("manager_user_name <>", value, "managerUserName");
            return (Criteria) this;
        }

        public Criteria andManagerUserNameGreaterThan(String value) {
            addCriterion("manager_user_name >", value, "managerUserName");
            return (Criteria) this;
        }

        public Criteria andManagerUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("manager_user_name >=", value, "managerUserName");
            return (Criteria) this;
        }

        public Criteria andManagerUserNameLessThan(String value) {
            addCriterion("manager_user_name <", value, "managerUserName");
            return (Criteria) this;
        }

        public Criteria andManagerUserNameLessThanOrEqualTo(String value) {
            addCriterion("manager_user_name <=", value, "managerUserName");
            return (Criteria) this;
        }

        public Criteria andManagerUserNameLike(String value) {
            addCriterion("manager_user_name like", value, "managerUserName");
            return (Criteria) this;
        }

        public Criteria andManagerUserNameNotLike(String value) {
            addCriterion("manager_user_name not like", value, "managerUserName");
            return (Criteria) this;
        }

        public Criteria andManagerUserNameIn(List<String> values) {
            addCriterion("manager_user_name in", values, "managerUserName");
            return (Criteria) this;
        }

        public Criteria andManagerUserNameNotIn(List<String> values) {
            addCriterion("manager_user_name not in", values, "managerUserName");
            return (Criteria) this;
        }

        public Criteria andManagerUserNameBetween(String value1, String value2) {
            addCriterion("manager_user_name between", value1, value2, "managerUserName");
            return (Criteria) this;
        }

        public Criteria andManagerUserNameNotBetween(String value1, String value2) {
            addCriterion("manager_user_name not between", value1, value2, "managerUserName");
            return (Criteria) this;
        }

        public Criteria andManagerNickNameIsNull() {
            addCriterion("manager_nick_name is null");
            return (Criteria) this;
        }

        public Criteria andManagerNickNameIsNotNull() {
            addCriterion("manager_nick_name is not null");
            return (Criteria) this;
        }

        public Criteria andManagerNickNameEqualTo(String value) {
            addCriterion("manager_nick_name =", value, "managerNickName");
            return (Criteria) this;
        }

        public Criteria andManagerNickNameNotEqualTo(String value) {
            addCriterion("manager_nick_name <>", value, "managerNickName");
            return (Criteria) this;
        }

        public Criteria andManagerNickNameGreaterThan(String value) {
            addCriterion("manager_nick_name >", value, "managerNickName");
            return (Criteria) this;
        }

        public Criteria andManagerNickNameGreaterThanOrEqualTo(String value) {
            addCriterion("manager_nick_name >=", value, "managerNickName");
            return (Criteria) this;
        }

        public Criteria andManagerNickNameLessThan(String value) {
            addCriterion("manager_nick_name <", value, "managerNickName");
            return (Criteria) this;
        }

        public Criteria andManagerNickNameLessThanOrEqualTo(String value) {
            addCriterion("manager_nick_name <=", value, "managerNickName");
            return (Criteria) this;
        }

        public Criteria andManagerNickNameLike(String value) {
            addCriterion("manager_nick_name like", value, "managerNickName");
            return (Criteria) this;
        }

        public Criteria andManagerNickNameNotLike(String value) {
            addCriterion("manager_nick_name not like", value, "managerNickName");
            return (Criteria) this;
        }

        public Criteria andManagerNickNameIn(List<String> values) {
            addCriterion("manager_nick_name in", values, "managerNickName");
            return (Criteria) this;
        }

        public Criteria andManagerNickNameNotIn(List<String> values) {
            addCriterion("manager_nick_name not in", values, "managerNickName");
            return (Criteria) this;
        }

        public Criteria andManagerNickNameBetween(String value1, String value2) {
            addCriterion("manager_nick_name between", value1, value2, "managerNickName");
            return (Criteria) this;
        }

        public Criteria andManagerNickNameNotBetween(String value1, String value2) {
            addCriterion("manager_nick_name not between", value1, value2, "managerNickName");
            return (Criteria) this;
        }

        public Criteria andBeforeJsonIsNull() {
            addCriterion("before_json is null");
            return (Criteria) this;
        }

        public Criteria andBeforeJsonIsNotNull() {
            addCriterion("before_json is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeJsonEqualTo(String value) {
            addCriterion("before_json =", value, "beforeJson");
            return (Criteria) this;
        }

        public Criteria andBeforeJsonNotEqualTo(String value) {
            addCriterion("before_json <>", value, "beforeJson");
            return (Criteria) this;
        }

        public Criteria andBeforeJsonGreaterThan(String value) {
            addCriterion("before_json >", value, "beforeJson");
            return (Criteria) this;
        }

        public Criteria andBeforeJsonGreaterThanOrEqualTo(String value) {
            addCriterion("before_json >=", value, "beforeJson");
            return (Criteria) this;
        }

        public Criteria andBeforeJsonLessThan(String value) {
            addCriterion("before_json <", value, "beforeJson");
            return (Criteria) this;
        }

        public Criteria andBeforeJsonLessThanOrEqualTo(String value) {
            addCriterion("before_json <=", value, "beforeJson");
            return (Criteria) this;
        }

        public Criteria andBeforeJsonLike(String value) {
            addCriterion("before_json like", value, "beforeJson");
            return (Criteria) this;
        }

        public Criteria andBeforeJsonNotLike(String value) {
            addCriterion("before_json not like", value, "beforeJson");
            return (Criteria) this;
        }

        public Criteria andBeforeJsonIn(List<String> values) {
            addCriterion("before_json in", values, "beforeJson");
            return (Criteria) this;
        }

        public Criteria andBeforeJsonNotIn(List<String> values) {
            addCriterion("before_json not in", values, "beforeJson");
            return (Criteria) this;
        }

        public Criteria andBeforeJsonBetween(String value1, String value2) {
            addCriterion("before_json between", value1, value2, "beforeJson");
            return (Criteria) this;
        }

        public Criteria andBeforeJsonNotBetween(String value1, String value2) {
            addCriterion("before_json not between", value1, value2, "beforeJson");
            return (Criteria) this;
        }

        public Criteria andAfterJsonIsNull() {
            addCriterion("after_json is null");
            return (Criteria) this;
        }

        public Criteria andAfterJsonIsNotNull() {
            addCriterion("after_json is not null");
            return (Criteria) this;
        }

        public Criteria andAfterJsonEqualTo(String value) {
            addCriterion("after_json =", value, "afterJson");
            return (Criteria) this;
        }

        public Criteria andAfterJsonNotEqualTo(String value) {
            addCriterion("after_json <>", value, "afterJson");
            return (Criteria) this;
        }

        public Criteria andAfterJsonGreaterThan(String value) {
            addCriterion("after_json >", value, "afterJson");
            return (Criteria) this;
        }

        public Criteria andAfterJsonGreaterThanOrEqualTo(String value) {
            addCriterion("after_json >=", value, "afterJson");
            return (Criteria) this;
        }

        public Criteria andAfterJsonLessThan(String value) {
            addCriterion("after_json <", value, "afterJson");
            return (Criteria) this;
        }

        public Criteria andAfterJsonLessThanOrEqualTo(String value) {
            addCriterion("after_json <=", value, "afterJson");
            return (Criteria) this;
        }

        public Criteria andAfterJsonLike(String value) {
            addCriterion("after_json like", value, "afterJson");
            return (Criteria) this;
        }

        public Criteria andAfterJsonNotLike(String value) {
            addCriterion("after_json not like", value, "afterJson");
            return (Criteria) this;
        }

        public Criteria andAfterJsonIn(List<String> values) {
            addCriterion("after_json in", values, "afterJson");
            return (Criteria) this;
        }

        public Criteria andAfterJsonNotIn(List<String> values) {
            addCriterion("after_json not in", values, "afterJson");
            return (Criteria) this;
        }

        public Criteria andAfterJsonBetween(String value1, String value2) {
            addCriterion("after_json between", value1, value2, "afterJson");
            return (Criteria) this;
        }

        public Criteria andAfterJsonNotBetween(String value1, String value2) {
            addCriterion("after_json not between", value1, value2, "afterJson");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNull() {
            addCriterion("operator_id is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNotNull() {
            addCriterion("operator_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdEqualTo(Integer value) {
            addCriterion("operator_id =", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotEqualTo(Integer value) {
            addCriterion("operator_id <>", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThan(Integer value) {
            addCriterion("operator_id >", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("operator_id >=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThan(Integer value) {
            addCriterion("operator_id <", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThanOrEqualTo(Integer value) {
            addCriterion("operator_id <=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIn(List<Integer> values) {
            addCriterion("operator_id in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotIn(List<Integer> values) {
            addCriterion("operator_id not in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdBetween(Integer value1, Integer value2) {
            addCriterion("operator_id between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("operator_id not between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorUserNameIsNull() {
            addCriterion("operator_user_name is null");
            return (Criteria) this;
        }

        public Criteria andOperatorUserNameIsNotNull() {
            addCriterion("operator_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorUserNameEqualTo(String value) {
            addCriterion("operator_user_name =", value, "operatorUserName");
            return (Criteria) this;
        }

        public Criteria andOperatorUserNameNotEqualTo(String value) {
            addCriterion("operator_user_name <>", value, "operatorUserName");
            return (Criteria) this;
        }

        public Criteria andOperatorUserNameGreaterThan(String value) {
            addCriterion("operator_user_name >", value, "operatorUserName");
            return (Criteria) this;
        }

        public Criteria andOperatorUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("operator_user_name >=", value, "operatorUserName");
            return (Criteria) this;
        }

        public Criteria andOperatorUserNameLessThan(String value) {
            addCriterion("operator_user_name <", value, "operatorUserName");
            return (Criteria) this;
        }

        public Criteria andOperatorUserNameLessThanOrEqualTo(String value) {
            addCriterion("operator_user_name <=", value, "operatorUserName");
            return (Criteria) this;
        }

        public Criteria andOperatorUserNameLike(String value) {
            addCriterion("operator_user_name like", value, "operatorUserName");
            return (Criteria) this;
        }

        public Criteria andOperatorUserNameNotLike(String value) {
            addCriterion("operator_user_name not like", value, "operatorUserName");
            return (Criteria) this;
        }

        public Criteria andOperatorUserNameIn(List<String> values) {
            addCriterion("operator_user_name in", values, "operatorUserName");
            return (Criteria) this;
        }

        public Criteria andOperatorUserNameNotIn(List<String> values) {
            addCriterion("operator_user_name not in", values, "operatorUserName");
            return (Criteria) this;
        }

        public Criteria andOperatorUserNameBetween(String value1, String value2) {
            addCriterion("operator_user_name between", value1, value2, "operatorUserName");
            return (Criteria) this;
        }

        public Criteria andOperatorUserNameNotBetween(String value1, String value2) {
            addCriterion("operator_user_name not between", value1, value2, "operatorUserName");
            return (Criteria) this;
        }

        public Criteria andOperatorNickNameIsNull() {
            addCriterion("operator_nick_name is null");
            return (Criteria) this;
        }

        public Criteria andOperatorNickNameIsNotNull() {
            addCriterion("operator_nick_name is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorNickNameEqualTo(String value) {
            addCriterion("operator_nick_name =", value, "operatorNickName");
            return (Criteria) this;
        }

        public Criteria andOperatorNickNameNotEqualTo(String value) {
            addCriterion("operator_nick_name <>", value, "operatorNickName");
            return (Criteria) this;
        }

        public Criteria andOperatorNickNameGreaterThan(String value) {
            addCriterion("operator_nick_name >", value, "operatorNickName");
            return (Criteria) this;
        }

        public Criteria andOperatorNickNameGreaterThanOrEqualTo(String value) {
            addCriterion("operator_nick_name >=", value, "operatorNickName");
            return (Criteria) this;
        }

        public Criteria andOperatorNickNameLessThan(String value) {
            addCriterion("operator_nick_name <", value, "operatorNickName");
            return (Criteria) this;
        }

        public Criteria andOperatorNickNameLessThanOrEqualTo(String value) {
            addCriterion("operator_nick_name <=", value, "operatorNickName");
            return (Criteria) this;
        }

        public Criteria andOperatorNickNameLike(String value) {
            addCriterion("operator_nick_name like", value, "operatorNickName");
            return (Criteria) this;
        }

        public Criteria andOperatorNickNameNotLike(String value) {
            addCriterion("operator_nick_name not like", value, "operatorNickName");
            return (Criteria) this;
        }

        public Criteria andOperatorNickNameIn(List<String> values) {
            addCriterion("operator_nick_name in", values, "operatorNickName");
            return (Criteria) this;
        }

        public Criteria andOperatorNickNameNotIn(List<String> values) {
            addCriterion("operator_nick_name not in", values, "operatorNickName");
            return (Criteria) this;
        }

        public Criteria andOperatorNickNameBetween(String value1, String value2) {
            addCriterion("operator_nick_name between", value1, value2, "operatorNickName");
            return (Criteria) this;
        }

        public Criteria andOperatorNickNameNotBetween(String value1, String value2) {
            addCriterion("operator_nick_name not between", value1, value2, "operatorNickName");
            return (Criteria) this;
        }

        public Criteria andOperatorIpIsNull() {
            addCriterion("operator_IP is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIpIsNotNull() {
            addCriterion("operator_IP is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorIpEqualTo(String value) {
            addCriterion("operator_IP =", value, "operatorIp");
            return (Criteria) this;
        }

        public Criteria andOperatorIpNotEqualTo(String value) {
            addCriterion("operator_IP <>", value, "operatorIp");
            return (Criteria) this;
        }

        public Criteria andOperatorIpGreaterThan(String value) {
            addCriterion("operator_IP >", value, "operatorIp");
            return (Criteria) this;
        }

        public Criteria andOperatorIpGreaterThanOrEqualTo(String value) {
            addCriterion("operator_IP >=", value, "operatorIp");
            return (Criteria) this;
        }

        public Criteria andOperatorIpLessThan(String value) {
            addCriterion("operator_IP <", value, "operatorIp");
            return (Criteria) this;
        }

        public Criteria andOperatorIpLessThanOrEqualTo(String value) {
            addCriterion("operator_IP <=", value, "operatorIp");
            return (Criteria) this;
        }

        public Criteria andOperatorIpLike(String value) {
            addCriterion("operator_IP like", value, "operatorIp");
            return (Criteria) this;
        }

        public Criteria andOperatorIpNotLike(String value) {
            addCriterion("operator_IP not like", value, "operatorIp");
            return (Criteria) this;
        }

        public Criteria andOperatorIpIn(List<String> values) {
            addCriterion("operator_IP in", values, "operatorIp");
            return (Criteria) this;
        }

        public Criteria andOperatorIpNotIn(List<String> values) {
            addCriterion("operator_IP not in", values, "operatorIp");
            return (Criteria) this;
        }

        public Criteria andOperatorIpBetween(String value1, String value2) {
            addCriterion("operator_IP between", value1, value2, "operatorIp");
            return (Criteria) this;
        }

        public Criteria andOperatorIpNotBetween(String value1, String value2) {
            addCriterion("operator_IP not between", value1, value2, "operatorIp");
            return (Criteria) this;
        }

        public Criteria andOperateTimeIsNull() {
            addCriterion("operate_time is null");
            return (Criteria) this;
        }

        public Criteria andOperateTimeIsNotNull() {
            addCriterion("operate_time is not null");
            return (Criteria) this;
        }

        public Criteria andOperateTimeEqualTo(Date value) {
            addCriterion("operate_time =", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeNotEqualTo(Date value) {
            addCriterion("operate_time <>", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeGreaterThan(Date value) {
            addCriterion("operate_time >", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("operate_time >=", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeLessThan(Date value) {
            addCriterion("operate_time <", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeLessThanOrEqualTo(Date value) {
            addCriterion("operate_time <=", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeIn(List<Date> values) {
            addCriterion("operate_time in", values, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeNotIn(List<Date> values) {
            addCriterion("operate_time not in", values, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeBetween(Date value1, Date value2) {
            addCriterion("operate_time between", value1, value2, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeNotBetween(Date value1, Date value2) {
            addCriterion("operate_time not between", value1, value2, "operateTime");
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
    }
}
