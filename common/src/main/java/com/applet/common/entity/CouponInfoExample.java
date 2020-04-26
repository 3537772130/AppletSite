package com.applet.common.entity;

import com.applet.common.util.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CouponInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public CouponInfoExample() {
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

        public Criteria andCouponNameIsNull() {
            addCriterion("coupon_name is null");
            return (Criteria) this;
        }

        public Criteria andCouponNameIsNotNull() {
            addCriterion("coupon_name is not null");
            return (Criteria) this;
        }

        public Criteria andCouponNameEqualTo(String value) {
            addCriterion("coupon_name =", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameNotEqualTo(String value) {
            addCriterion("coupon_name <>", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameGreaterThan(String value) {
            addCriterion("coupon_name >", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_name >=", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameLessThan(String value) {
            addCriterion("coupon_name <", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameLessThanOrEqualTo(String value) {
            addCriterion("coupon_name <=", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameLike(String value) {
            addCriterion("coupon_name like", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameNotLike(String value) {
            addCriterion("coupon_name not like", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameIn(List<String> values) {
            addCriterion("coupon_name in", values, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameNotIn(List<String> values) {
            addCriterion("coupon_name not in", values, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameBetween(String value1, String value2) {
            addCriterion("coupon_name between", value1, value2, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameNotBetween(String value1, String value2) {
            addCriterion("coupon_name not between", value1, value2, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIsNull() {
            addCriterion("coupon_type is null");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIsNotNull() {
            addCriterion("coupon_type is not null");
            return (Criteria) this;
        }

        public Criteria andCouponTypeEqualTo(Integer value) {
            addCriterion("coupon_type =", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNotEqualTo(Integer value) {
            addCriterion("coupon_type <>", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeGreaterThan(Integer value) {
            addCriterion("coupon_type >", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_type >=", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeLessThan(Integer value) {
            addCriterion("coupon_type <", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_type <=", value, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeIn(List<Integer> values) {
            addCriterion("coupon_type in", values, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNotIn(List<Integer> values) {
            addCriterion("coupon_type not in", values, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeBetween(Integer value1, Integer value2) {
            addCriterion("coupon_type between", value1, value2, "couponType");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_type not between", value1, value2, "couponType");
            return (Criteria) this;
        }

        public Criteria andGainTypeIsNull() {
            addCriterion("gain_type is null");
            return (Criteria) this;
        }

        public Criteria andGainTypeIsNotNull() {
            addCriterion("gain_type is not null");
            return (Criteria) this;
        }

        public Criteria andGainTypeEqualTo(Integer value) {
            addCriterion("gain_type =", value, "gainType");
            return (Criteria) this;
        }

        public Criteria andGainTypeNotEqualTo(Integer value) {
            addCriterion("gain_type <>", value, "gainType");
            return (Criteria) this;
        }

        public Criteria andGainTypeGreaterThan(Integer value) {
            addCriterion("gain_type >", value, "gainType");
            return (Criteria) this;
        }

        public Criteria andGainTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("gain_type >=", value, "gainType");
            return (Criteria) this;
        }

        public Criteria andGainTypeLessThan(Integer value) {
            addCriterion("gain_type <", value, "gainType");
            return (Criteria) this;
        }

        public Criteria andGainTypeLessThanOrEqualTo(Integer value) {
            addCriterion("gain_type <=", value, "gainType");
            return (Criteria) this;
        }

        public Criteria andGainTypeIn(List<Integer> values) {
            addCriterion("gain_type in", values, "gainType");
            return (Criteria) this;
        }

        public Criteria andGainTypeNotIn(List<Integer> values) {
            addCriterion("gain_type not in", values, "gainType");
            return (Criteria) this;
        }

        public Criteria andGainTypeBetween(Integer value1, Integer value2) {
            addCriterion("gain_type between", value1, value2, "gainType");
            return (Criteria) this;
        }

        public Criteria andGainTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("gain_type not between", value1, value2, "gainType");
            return (Criteria) this;
        }

        public Criteria andGainAppletIdIsNull() {
            addCriterion("gain_applet_id is null");
            return (Criteria) this;
        }

        public Criteria andGainAppletIdIsNotNull() {
            addCriterion("gain_applet_id is not null");
            return (Criteria) this;
        }

        public Criteria andGainAppletIdEqualTo(Integer value) {
            addCriterion("gain_applet_id =", value, "gainAppletId");
            return (Criteria) this;
        }

        public Criteria andGainAppletIdNotEqualTo(Integer value) {
            addCriterion("gain_applet_id <>", value, "gainAppletId");
            return (Criteria) this;
        }

        public Criteria andGainAppletIdGreaterThan(Integer value) {
            addCriterion("gain_applet_id >", value, "gainAppletId");
            return (Criteria) this;
        }

        public Criteria andGainAppletIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("gain_applet_id >=", value, "gainAppletId");
            return (Criteria) this;
        }

        public Criteria andGainAppletIdLessThan(Integer value) {
            addCriterion("gain_applet_id <", value, "gainAppletId");
            return (Criteria) this;
        }

        public Criteria andGainAppletIdLessThanOrEqualTo(Integer value) {
            addCriterion("gain_applet_id <=", value, "gainAppletId");
            return (Criteria) this;
        }

        public Criteria andGainAppletIdIn(List<Integer> values) {
            addCriterion("gain_applet_id in", values, "gainAppletId");
            return (Criteria) this;
        }

        public Criteria andGainAppletIdNotIn(List<Integer> values) {
            addCriterion("gain_applet_id not in", values, "gainAppletId");
            return (Criteria) this;
        }

        public Criteria andGainAppletIdBetween(Integer value1, Integer value2) {
            addCriterion("gain_applet_id between", value1, value2, "gainAppletId");
            return (Criteria) this;
        }

        public Criteria andGainAppletIdNotBetween(Integer value1, Integer value2) {
            addCriterion("gain_applet_id not between", value1, value2, "gainAppletId");
            return (Criteria) this;
        }

        public Criteria andGainPriceIsNull() {
            addCriterion("gain_price is null");
            return (Criteria) this;
        }

        public Criteria andGainPriceIsNotNull() {
            addCriterion("gain_price is not null");
            return (Criteria) this;
        }

        public Criteria andGainPriceEqualTo(Double value) {
            addCriterion("gain_price =", value, "gainPrice");
            return (Criteria) this;
        }

        public Criteria andGainPriceNotEqualTo(Double value) {
            addCriterion("gain_price <>", value, "gainPrice");
            return (Criteria) this;
        }

        public Criteria andGainPriceGreaterThan(Double value) {
            addCriterion("gain_price >", value, "gainPrice");
            return (Criteria) this;
        }

        public Criteria andGainPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("gain_price >=", value, "gainPrice");
            return (Criteria) this;
        }

        public Criteria andGainPriceLessThan(Double value) {
            addCriterion("gain_price <", value, "gainPrice");
            return (Criteria) this;
        }

        public Criteria andGainPriceLessThanOrEqualTo(Double value) {
            addCriterion("gain_price <=", value, "gainPrice");
            return (Criteria) this;
        }

        public Criteria andGainPriceIn(List<Double> values) {
            addCriterion("gain_price in", values, "gainPrice");
            return (Criteria) this;
        }

        public Criteria andGainPriceNotIn(List<Double> values) {
            addCriterion("gain_price not in", values, "gainPrice");
            return (Criteria) this;
        }

        public Criteria andGainPriceBetween(Double value1, Double value2) {
            addCriterion("gain_price between", value1, value2, "gainPrice");
            return (Criteria) this;
        }

        public Criteria andGainPriceNotBetween(Double value1, Double value2) {
            addCriterion("gain_price not between", value1, value2, "gainPrice");
            return (Criteria) this;
        }

        public Criteria andUseAppletIdIsNull() {
            addCriterion("use_applet_id is null");
            return (Criteria) this;
        }

        public Criteria andUseAppletIdIsNotNull() {
            addCriterion("use_applet_id is not null");
            return (Criteria) this;
        }

        public Criteria andUseAppletIdEqualTo(Integer value) {
            addCriterion("use_applet_id =", value, "useAppletId");
            return (Criteria) this;
        }

        public Criteria andUseAppletIdNotEqualTo(Integer value) {
            addCriterion("use_applet_id <>", value, "useAppletId");
            return (Criteria) this;
        }

        public Criteria andUseAppletIdGreaterThan(Integer value) {
            addCriterion("use_applet_id >", value, "useAppletId");
            return (Criteria) this;
        }

        public Criteria andUseAppletIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("use_applet_id >=", value, "useAppletId");
            return (Criteria) this;
        }

        public Criteria andUseAppletIdLessThan(Integer value) {
            addCriterion("use_applet_id <", value, "useAppletId");
            return (Criteria) this;
        }

        public Criteria andUseAppletIdLessThanOrEqualTo(Integer value) {
            addCriterion("use_applet_id <=", value, "useAppletId");
            return (Criteria) this;
        }

        public Criteria andUseAppletIdIn(List<Integer> values) {
            addCriterion("use_applet_id in", values, "useAppletId");
            return (Criteria) this;
        }

        public Criteria andUseAppletIdNotIn(List<Integer> values) {
            addCriterion("use_applet_id not in", values, "useAppletId");
            return (Criteria) this;
        }

        public Criteria andUseAppletIdBetween(Integer value1, Integer value2) {
            addCriterion("use_applet_id between", value1, value2, "useAppletId");
            return (Criteria) this;
        }

        public Criteria andUseAppletIdNotBetween(Integer value1, Integer value2) {
            addCriterion("use_applet_id not between", value1, value2, "useAppletId");
            return (Criteria) this;
        }

        public Criteria andUsePriceIsNull() {
            addCriterion("use_price is null");
            return (Criteria) this;
        }

        public Criteria andUsePriceIsNotNull() {
            addCriterion("use_price is not null");
            return (Criteria) this;
        }

        public Criteria andUsePriceEqualTo(Double value) {
            addCriterion("use_price =", value, "usePrice");
            return (Criteria) this;
        }

        public Criteria andUsePriceNotEqualTo(Double value) {
            addCriterion("use_price <>", value, "usePrice");
            return (Criteria) this;
        }

        public Criteria andUsePriceGreaterThan(Double value) {
            addCriterion("use_price >", value, "usePrice");
            return (Criteria) this;
        }

        public Criteria andUsePriceGreaterThanOrEqualTo(Double value) {
            addCriterion("use_price >=", value, "usePrice");
            return (Criteria) this;
        }

        public Criteria andUsePriceLessThan(Double value) {
            addCriterion("use_price <", value, "usePrice");
            return (Criteria) this;
        }

        public Criteria andUsePriceLessThanOrEqualTo(Double value) {
            addCriterion("use_price <=", value, "usePrice");
            return (Criteria) this;
        }

        public Criteria andUsePriceIn(List<Double> values) {
            addCriterion("use_price in", values, "usePrice");
            return (Criteria) this;
        }

        public Criteria andUsePriceNotIn(List<Double> values) {
            addCriterion("use_price not in", values, "usePrice");
            return (Criteria) this;
        }

        public Criteria andUsePriceBetween(Double value1, Double value2) {
            addCriterion("use_price between", value1, value2, "usePrice");
            return (Criteria) this;
        }

        public Criteria andUsePriceNotBetween(Double value1, Double value2) {
            addCriterion("use_price not between", value1, value2, "usePrice");
            return (Criteria) this;
        }

        public Criteria andDenominationIsNull() {
            addCriterion("denomination is null");
            return (Criteria) this;
        }

        public Criteria andDenominationIsNotNull() {
            addCriterion("denomination is not null");
            return (Criteria) this;
        }

        public Criteria andDenominationEqualTo(Double value) {
            addCriterion("denomination =", value, "denomination");
            return (Criteria) this;
        }

        public Criteria andDenominationNotEqualTo(Double value) {
            addCriterion("denomination <>", value, "denomination");
            return (Criteria) this;
        }

        public Criteria andDenominationGreaterThan(Double value) {
            addCriterion("denomination >", value, "denomination");
            return (Criteria) this;
        }

        public Criteria andDenominationGreaterThanOrEqualTo(Double value) {
            addCriterion("denomination >=", value, "denomination");
            return (Criteria) this;
        }

        public Criteria andDenominationLessThan(Double value) {
            addCriterion("denomination <", value, "denomination");
            return (Criteria) this;
        }

        public Criteria andDenominationLessThanOrEqualTo(Double value) {
            addCriterion("denomination <=", value, "denomination");
            return (Criteria) this;
        }

        public Criteria andDenominationIn(List<Double> values) {
            addCriterion("denomination in", values, "denomination");
            return (Criteria) this;
        }

        public Criteria andDenominationNotIn(List<Double> values) {
            addCriterion("denomination not in", values, "denomination");
            return (Criteria) this;
        }

        public Criteria andDenominationBetween(Double value1, Double value2) {
            addCriterion("denomination between", value1, value2, "denomination");
            return (Criteria) this;
        }

        public Criteria andDenominationNotBetween(Double value1, Double value2) {
            addCriterion("denomination not between", value1, value2, "denomination");
            return (Criteria) this;
        }

        public Criteria andMakeIssueNumIsNull() {
            addCriterion("make_issue_num is null");
            return (Criteria) this;
        }

        public Criteria andMakeIssueNumIsNotNull() {
            addCriterion("make_issue_num is not null");
            return (Criteria) this;
        }

        public Criteria andMakeIssueNumEqualTo(Integer value) {
            addCriterion("make_issue_num =", value, "makeIssueNum");
            return (Criteria) this;
        }

        public Criteria andMakeIssueNumNotEqualTo(Integer value) {
            addCriterion("make_issue_num <>", value, "makeIssueNum");
            return (Criteria) this;
        }

        public Criteria andMakeIssueNumGreaterThan(Integer value) {
            addCriterion("make_issue_num >", value, "makeIssueNum");
            return (Criteria) this;
        }

        public Criteria andMakeIssueNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("make_issue_num >=", value, "makeIssueNum");
            return (Criteria) this;
        }

        public Criteria andMakeIssueNumLessThan(Integer value) {
            addCriterion("make_issue_num <", value, "makeIssueNum");
            return (Criteria) this;
        }

        public Criteria andMakeIssueNumLessThanOrEqualTo(Integer value) {
            addCriterion("make_issue_num <=", value, "makeIssueNum");
            return (Criteria) this;
        }

        public Criteria andMakeIssueNumIn(List<Integer> values) {
            addCriterion("make_issue_num in", values, "makeIssueNum");
            return (Criteria) this;
        }

        public Criteria andMakeIssueNumNotIn(List<Integer> values) {
            addCriterion("make_issue_num not in", values, "makeIssueNum");
            return (Criteria) this;
        }

        public Criteria andMakeIssueNumBetween(Integer value1, Integer value2) {
            addCriterion("make_issue_num between", value1, value2, "makeIssueNum");
            return (Criteria) this;
        }

        public Criteria andMakeIssueNumNotBetween(Integer value1, Integer value2) {
            addCriterion("make_issue_num not between", value1, value2, "makeIssueNum");
            return (Criteria) this;
        }

        public Criteria andAlreadyIssueNumIsNull() {
            addCriterion("already_issue_num is null");
            return (Criteria) this;
        }

        public Criteria andAlreadyIssueNumIsNotNull() {
            addCriterion("already_issue_num is not null");
            return (Criteria) this;
        }

        public Criteria andAlreadyIssueNumEqualTo(Integer value) {
            addCriterion("already_issue_num =", value, "alreadyIssueNum");
            return (Criteria) this;
        }

        public Criteria andAlreadyIssueNumNotEqualTo(Integer value) {
            addCriterion("already_issue_num <>", value, "alreadyIssueNum");
            return (Criteria) this;
        }

        public Criteria andAlreadyIssueNumGreaterThan(Integer value) {
            addCriterion("already_issue_num >", value, "alreadyIssueNum");
            return (Criteria) this;
        }

        public Criteria andAlreadyIssueNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("already_issue_num >=", value, "alreadyIssueNum");
            return (Criteria) this;
        }

        public Criteria andAlreadyIssueNumLessThan(Integer value) {
            addCriterion("already_issue_num <", value, "alreadyIssueNum");
            return (Criteria) this;
        }

        public Criteria andAlreadyIssueNumLessThanOrEqualTo(Integer value) {
            addCriterion("already_issue_num <=", value, "alreadyIssueNum");
            return (Criteria) this;
        }

        public Criteria andAlreadyIssueNumIn(List<Integer> values) {
            addCriterion("already_issue_num in", values, "alreadyIssueNum");
            return (Criteria) this;
        }

        public Criteria andAlreadyIssueNumNotIn(List<Integer> values) {
            addCriterion("already_issue_num not in", values, "alreadyIssueNum");
            return (Criteria) this;
        }

        public Criteria andAlreadyIssueNumBetween(Integer value1, Integer value2) {
            addCriterion("already_issue_num between", value1, value2, "alreadyIssueNum");
            return (Criteria) this;
        }

        public Criteria andAlreadyIssueNumNotBetween(Integer value1, Integer value2) {
            addCriterion("already_issue_num not between", value1, value2, "alreadyIssueNum");
            return (Criteria) this;
        }

        public Criteria andActivityStartIsNull() {
            addCriterion("activity_start is null");
            return (Criteria) this;
        }

        public Criteria andActivityStartIsNotNull() {
            addCriterion("activity_start is not null");
            return (Criteria) this;
        }

        public Criteria andActivityStartEqualTo(Date value) {
            addCriterion("activity_start =", value, "activityStart");
            return (Criteria) this;
        }

        public Criteria andActivityStartNotEqualTo(Date value) {
            addCriterion("activity_start <>", value, "activityStart");
            return (Criteria) this;
        }

        public Criteria andActivityStartGreaterThan(Date value) {
            addCriterion("activity_start >", value, "activityStart");
            return (Criteria) this;
        }

        public Criteria andActivityStartGreaterThanOrEqualTo(Date value) {
            addCriterion("activity_start >=", value, "activityStart");
            return (Criteria) this;
        }

        public Criteria andActivityStartLessThan(Date value) {
            addCriterion("activity_start <", value, "activityStart");
            return (Criteria) this;
        }

        public Criteria andActivityStartLessThanOrEqualTo(Date value) {
            addCriterion("activity_start <=", value, "activityStart");
            return (Criteria) this;
        }

        public Criteria andActivityStartIn(List<Date> values) {
            addCriterion("activity_start in", values, "activityStart");
            return (Criteria) this;
        }

        public Criteria andActivityStartNotIn(List<Date> values) {
            addCriterion("activity_start not in", values, "activityStart");
            return (Criteria) this;
        }

        public Criteria andActivityStartBetween(Date value1, Date value2) {
            addCriterion("activity_start between", value1, value2, "activityStart");
            return (Criteria) this;
        }

        public Criteria andActivityStartNotBetween(Date value1, Date value2) {
            addCriterion("activity_start not between", value1, value2, "activityStart");
            return (Criteria) this;
        }

        public Criteria andActivityOverIsNull() {
            addCriterion("activity_over is null");
            return (Criteria) this;
        }

        public Criteria andActivityOverIsNotNull() {
            addCriterion("activity_over is not null");
            return (Criteria) this;
        }

        public Criteria andActivityOverEqualTo(Date value) {
            addCriterion("activity_over =", value, "activityOver");
            return (Criteria) this;
        }

        public Criteria andActivityOverNotEqualTo(Date value) {
            addCriterion("activity_over <>", value, "activityOver");
            return (Criteria) this;
        }

        public Criteria andActivityOverGreaterThan(Date value) {
            addCriterion("activity_over >", value, "activityOver");
            return (Criteria) this;
        }

        public Criteria andActivityOverGreaterThanOrEqualTo(Date value) {
            addCriterion("activity_over >=", value, "activityOver");
            return (Criteria) this;
        }

        public Criteria andActivityOverLessThan(Date value) {
            addCriterion("activity_over <", value, "activityOver");
            return (Criteria) this;
        }

        public Criteria andActivityOverLessThanOrEqualTo(Date value) {
            addCriterion("activity_over <=", value, "activityOver");
            return (Criteria) this;
        }

        public Criteria andActivityOverIn(List<Date> values) {
            addCriterion("activity_over in", values, "activityOver");
            return (Criteria) this;
        }

        public Criteria andActivityOverNotIn(List<Date> values) {
            addCriterion("activity_over not in", values, "activityOver");
            return (Criteria) this;
        }

        public Criteria andActivityOverBetween(Date value1, Date value2) {
            addCriterion("activity_over between", value1, value2, "activityOver");
            return (Criteria) this;
        }

        public Criteria andActivityOverNotBetween(Date value1, Date value2) {
            addCriterion("activity_over not between", value1, value2, "activityOver");
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