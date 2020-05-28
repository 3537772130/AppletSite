package com.applet.common.entity;

import com.applet.common.util.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewOrderInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public ViewOrderInfoExample() {
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

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andCourierNoIsNull() {
            addCriterion("courier_no is null");
            return (Criteria) this;
        }

        public Criteria andCourierNoIsNotNull() {
            addCriterion("courier_no is not null");
            return (Criteria) this;
        }

        public Criteria andCourierNoEqualTo(String value) {
            addCriterion("courier_no =", value, "courierNo");
            return (Criteria) this;
        }

        public Criteria andCourierNoNotEqualTo(String value) {
            addCriterion("courier_no <>", value, "courierNo");
            return (Criteria) this;
        }

        public Criteria andCourierNoGreaterThan(String value) {
            addCriterion("courier_no >", value, "courierNo");
            return (Criteria) this;
        }

        public Criteria andCourierNoGreaterThanOrEqualTo(String value) {
            addCriterion("courier_no >=", value, "courierNo");
            return (Criteria) this;
        }

        public Criteria andCourierNoLessThan(String value) {
            addCriterion("courier_no <", value, "courierNo");
            return (Criteria) this;
        }

        public Criteria andCourierNoLessThanOrEqualTo(String value) {
            addCriterion("courier_no <=", value, "courierNo");
            return (Criteria) this;
        }

        public Criteria andCourierNoLike(String value) {
            addCriterion("courier_no like", value, "courierNo");
            return (Criteria) this;
        }

        public Criteria andCourierNoNotLike(String value) {
            addCriterion("courier_no not like", value, "courierNo");
            return (Criteria) this;
        }

        public Criteria andCourierNoIn(List<String> values) {
            addCriterion("courier_no in", values, "courierNo");
            return (Criteria) this;
        }

        public Criteria andCourierNoNotIn(List<String> values) {
            addCriterion("courier_no not in", values, "courierNo");
            return (Criteria) this;
        }

        public Criteria andCourierNoBetween(String value1, String value2) {
            addCriterion("courier_no between", value1, value2, "courierNo");
            return (Criteria) this;
        }

        public Criteria andCourierNoNotBetween(String value1, String value2) {
            addCriterion("courier_no not between", value1, value2, "courierNo");
            return (Criteria) this;
        }

        public Criteria andAppletIdIsNull() {
            addCriterion("applet_id is null");
            return (Criteria) this;
        }

        public Criteria andAppletIdIsNotNull() {
            addCriterion("applet_id is not null");
            return (Criteria) this;
        }

        public Criteria andAppletIdEqualTo(Integer value) {
            addCriterion("applet_id =", value, "appletId");
            return (Criteria) this;
        }

        public Criteria andAppletIdNotEqualTo(Integer value) {
            addCriterion("applet_id <>", value, "appletId");
            return (Criteria) this;
        }

        public Criteria andAppletIdGreaterThan(Integer value) {
            addCriterion("applet_id >", value, "appletId");
            return (Criteria) this;
        }

        public Criteria andAppletIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("applet_id >=", value, "appletId");
            return (Criteria) this;
        }

        public Criteria andAppletIdLessThan(Integer value) {
            addCriterion("applet_id <", value, "appletId");
            return (Criteria) this;
        }

        public Criteria andAppletIdLessThanOrEqualTo(Integer value) {
            addCriterion("applet_id <=", value, "appletId");
            return (Criteria) this;
        }

        public Criteria andAppletIdIn(List<Integer> values) {
            addCriterion("applet_id in", values, "appletId");
            return (Criteria) this;
        }

        public Criteria andAppletIdNotIn(List<Integer> values) {
            addCriterion("applet_id not in", values, "appletId");
            return (Criteria) this;
        }

        public Criteria andAppletIdBetween(Integer value1, Integer value2) {
            addCriterion("applet_id between", value1, value2, "appletId");
            return (Criteria) this;
        }

        public Criteria andAppletIdNotBetween(Integer value1, Integer value2) {
            addCriterion("applet_id not between", value1, value2, "appletId");
            return (Criteria) this;
        }

        public Criteria andAppletNameIsNull() {
            addCriterion("applet_name is null");
            return (Criteria) this;
        }

        public Criteria andAppletNameIsNotNull() {
            addCriterion("applet_name is not null");
            return (Criteria) this;
        }

        public Criteria andAppletNameEqualTo(String value) {
            addCriterion("applet_name =", value, "appletName");
            return (Criteria) this;
        }

        public Criteria andAppletNameNotEqualTo(String value) {
            addCriterion("applet_name <>", value, "appletName");
            return (Criteria) this;
        }

        public Criteria andAppletNameGreaterThan(String value) {
            addCriterion("applet_name >", value, "appletName");
            return (Criteria) this;
        }

        public Criteria andAppletNameGreaterThanOrEqualTo(String value) {
            addCriterion("applet_name >=", value, "appletName");
            return (Criteria) this;
        }

        public Criteria andAppletNameLessThan(String value) {
            addCriterion("applet_name <", value, "appletName");
            return (Criteria) this;
        }

        public Criteria andAppletNameLessThanOrEqualTo(String value) {
            addCriterion("applet_name <=", value, "appletName");
            return (Criteria) this;
        }

        public Criteria andAppletNameLike(String value) {
            addCriterion("applet_name like", value, "appletName");
            return (Criteria) this;
        }

        public Criteria andAppletNameNotLike(String value) {
            addCriterion("applet_name not like", value, "appletName");
            return (Criteria) this;
        }

        public Criteria andAppletNameIn(List<String> values) {
            addCriterion("applet_name in", values, "appletName");
            return (Criteria) this;
        }

        public Criteria andAppletNameNotIn(List<String> values) {
            addCriterion("applet_name not in", values, "appletName");
            return (Criteria) this;
        }

        public Criteria andAppletNameBetween(String value1, String value2) {
            addCriterion("applet_name between", value1, value2, "appletName");
            return (Criteria) this;
        }

        public Criteria andAppletNameNotBetween(String value1, String value2) {
            addCriterion("applet_name not between", value1, value2, "appletName");
            return (Criteria) this;
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

        public Criteria andAppletLogoIsNull() {
            addCriterion("applet_logo is null");
            return (Criteria) this;
        }

        public Criteria andAppletLogoIsNotNull() {
            addCriterion("applet_logo is not null");
            return (Criteria) this;
        }

        public Criteria andAppletLogoEqualTo(String value) {
            addCriterion("applet_logo =", value, "appletLogo");
            return (Criteria) this;
        }

        public Criteria andAppletLogoNotEqualTo(String value) {
            addCriterion("applet_logo <>", value, "appletLogo");
            return (Criteria) this;
        }

        public Criteria andAppletLogoGreaterThan(String value) {
            addCriterion("applet_logo >", value, "appletLogo");
            return (Criteria) this;
        }

        public Criteria andAppletLogoGreaterThanOrEqualTo(String value) {
            addCriterion("applet_logo >=", value, "appletLogo");
            return (Criteria) this;
        }

        public Criteria andAppletLogoLessThan(String value) {
            addCriterion("applet_logo <", value, "appletLogo");
            return (Criteria) this;
        }

        public Criteria andAppletLogoLessThanOrEqualTo(String value) {
            addCriterion("applet_logo <=", value, "appletLogo");
            return (Criteria) this;
        }

        public Criteria andAppletLogoLike(String value) {
            addCriterion("applet_logo like", value, "appletLogo");
            return (Criteria) this;
        }

        public Criteria andAppletLogoNotLike(String value) {
            addCriterion("applet_logo not like", value, "appletLogo");
            return (Criteria) this;
        }

        public Criteria andAppletLogoIn(List<String> values) {
            addCriterion("applet_logo in", values, "appletLogo");
            return (Criteria) this;
        }

        public Criteria andAppletLogoNotIn(List<String> values) {
            addCriterion("applet_logo not in", values, "appletLogo");
            return (Criteria) this;
        }

        public Criteria andAppletLogoBetween(String value1, String value2) {
            addCriterion("applet_logo between", value1, value2, "appletLogo");
            return (Criteria) this;
        }

        public Criteria andAppletLogoNotBetween(String value1, String value2) {
            addCriterion("applet_logo not between", value1, value2, "appletLogo");
            return (Criteria) this;
        }

        public Criteria andAppletLatIsNull() {
            addCriterion("applet_lat is null");
            return (Criteria) this;
        }

        public Criteria andAppletLatIsNotNull() {
            addCriterion("applet_lat is not null");
            return (Criteria) this;
        }

        public Criteria andAppletLatEqualTo(Double value) {
            addCriterion("applet_lat =", value, "appletLat");
            return (Criteria) this;
        }

        public Criteria andAppletLatNotEqualTo(Double value) {
            addCriterion("applet_lat <>", value, "appletLat");
            return (Criteria) this;
        }

        public Criteria andAppletLatGreaterThan(Double value) {
            addCriterion("applet_lat >", value, "appletLat");
            return (Criteria) this;
        }

        public Criteria andAppletLatGreaterThanOrEqualTo(Double value) {
            addCriterion("applet_lat >=", value, "appletLat");
            return (Criteria) this;
        }

        public Criteria andAppletLatLessThan(Double value) {
            addCriterion("applet_lat <", value, "appletLat");
            return (Criteria) this;
        }

        public Criteria andAppletLatLessThanOrEqualTo(Double value) {
            addCriterion("applet_lat <=", value, "appletLat");
            return (Criteria) this;
        }

        public Criteria andAppletLatIn(List<Double> values) {
            addCriterion("applet_lat in", values, "appletLat");
            return (Criteria) this;
        }

        public Criteria andAppletLatNotIn(List<Double> values) {
            addCriterion("applet_lat not in", values, "appletLat");
            return (Criteria) this;
        }

        public Criteria andAppletLatBetween(Double value1, Double value2) {
            addCriterion("applet_lat between", value1, value2, "appletLat");
            return (Criteria) this;
        }

        public Criteria andAppletLatNotBetween(Double value1, Double value2) {
            addCriterion("applet_lat not between", value1, value2, "appletLat");
            return (Criteria) this;
        }

        public Criteria andAppletLonIsNull() {
            addCriterion("applet_lon is null");
            return (Criteria) this;
        }

        public Criteria andAppletLonIsNotNull() {
            addCriterion("applet_lon is not null");
            return (Criteria) this;
        }

        public Criteria andAppletLonEqualTo(Double value) {
            addCriterion("applet_lon =", value, "appletLon");
            return (Criteria) this;
        }

        public Criteria andAppletLonNotEqualTo(Double value) {
            addCriterion("applet_lon <>", value, "appletLon");
            return (Criteria) this;
        }

        public Criteria andAppletLonGreaterThan(Double value) {
            addCriterion("applet_lon >", value, "appletLon");
            return (Criteria) this;
        }

        public Criteria andAppletLonGreaterThanOrEqualTo(Double value) {
            addCriterion("applet_lon >=", value, "appletLon");
            return (Criteria) this;
        }

        public Criteria andAppletLonLessThan(Double value) {
            addCriterion("applet_lon <", value, "appletLon");
            return (Criteria) this;
        }

        public Criteria andAppletLonLessThanOrEqualTo(Double value) {
            addCriterion("applet_lon <=", value, "appletLon");
            return (Criteria) this;
        }

        public Criteria andAppletLonIn(List<Double> values) {
            addCriterion("applet_lon in", values, "appletLon");
            return (Criteria) this;
        }

        public Criteria andAppletLonNotIn(List<Double> values) {
            addCriterion("applet_lon not in", values, "appletLon");
            return (Criteria) this;
        }

        public Criteria andAppletLonBetween(Double value1, Double value2) {
            addCriterion("applet_lon between", value1, value2, "appletLon");
            return (Criteria) this;
        }

        public Criteria andAppletLonNotBetween(Double value1, Double value2) {
            addCriterion("applet_lon not between", value1, value2, "appletLon");
            return (Criteria) this;
        }

        public Criteria andAppletTelephoneIsNull() {
            addCriterion("applet_telephone is null");
            return (Criteria) this;
        }

        public Criteria andAppletTelephoneIsNotNull() {
            addCriterion("applet_telephone is not null");
            return (Criteria) this;
        }

        public Criteria andAppletTelephoneEqualTo(String value) {
            addCriterion("applet_telephone =", value, "appletTelephone");
            return (Criteria) this;
        }

        public Criteria andAppletTelephoneNotEqualTo(String value) {
            addCriterion("applet_telephone <>", value, "appletTelephone");
            return (Criteria) this;
        }

        public Criteria andAppletTelephoneGreaterThan(String value) {
            addCriterion("applet_telephone >", value, "appletTelephone");
            return (Criteria) this;
        }

        public Criteria andAppletTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("applet_telephone >=", value, "appletTelephone");
            return (Criteria) this;
        }

        public Criteria andAppletTelephoneLessThan(String value) {
            addCriterion("applet_telephone <", value, "appletTelephone");
            return (Criteria) this;
        }

        public Criteria andAppletTelephoneLessThanOrEqualTo(String value) {
            addCriterion("applet_telephone <=", value, "appletTelephone");
            return (Criteria) this;
        }

        public Criteria andAppletTelephoneLike(String value) {
            addCriterion("applet_telephone like", value, "appletTelephone");
            return (Criteria) this;
        }

        public Criteria andAppletTelephoneNotLike(String value) {
            addCriterion("applet_telephone not like", value, "appletTelephone");
            return (Criteria) this;
        }

        public Criteria andAppletTelephoneIn(List<String> values) {
            addCriterion("applet_telephone in", values, "appletTelephone");
            return (Criteria) this;
        }

        public Criteria andAppletTelephoneNotIn(List<String> values) {
            addCriterion("applet_telephone not in", values, "appletTelephone");
            return (Criteria) this;
        }

        public Criteria andAppletTelephoneBetween(String value1, String value2) {
            addCriterion("applet_telephone between", value1, value2, "appletTelephone");
            return (Criteria) this;
        }

        public Criteria andAppletTelephoneNotBetween(String value1, String value2) {
            addCriterion("applet_telephone not between", value1, value2, "appletTelephone");
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

        public Criteria andUserMobileIsNull() {
            addCriterion("user_mobile is null");
            return (Criteria) this;
        }

        public Criteria andUserMobileIsNotNull() {
            addCriterion("user_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andUserMobileEqualTo(String value) {
            addCriterion("user_mobile =", value, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileNotEqualTo(String value) {
            addCriterion("user_mobile <>", value, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileGreaterThan(String value) {
            addCriterion("user_mobile >", value, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileGreaterThanOrEqualTo(String value) {
            addCriterion("user_mobile >=", value, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileLessThan(String value) {
            addCriterion("user_mobile <", value, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileLessThanOrEqualTo(String value) {
            addCriterion("user_mobile <=", value, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileLike(String value) {
            addCriterion("user_mobile like", value, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileNotLike(String value) {
            addCriterion("user_mobile not like", value, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileIn(List<String> values) {
            addCriterion("user_mobile in", values, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileNotIn(List<String> values) {
            addCriterion("user_mobile not in", values, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileBetween(String value1, String value2) {
            addCriterion("user_mobile between", value1, value2, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileNotBetween(String value1, String value2) {
            addCriterion("user_mobile not between", value1, value2, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserNickNameIsNull() {
            addCriterion("user_nick_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNickNameIsNotNull() {
            addCriterion("user_nick_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNickNameEqualTo(String value) {
            addCriterion("user_nick_name =", value, "userNickName");
            return (Criteria) this;
        }

        public Criteria andUserNickNameNotEqualTo(String value) {
            addCriterion("user_nick_name <>", value, "userNickName");
            return (Criteria) this;
        }

        public Criteria andUserNickNameGreaterThan(String value) {
            addCriterion("user_nick_name >", value, "userNickName");
            return (Criteria) this;
        }

        public Criteria andUserNickNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_nick_name >=", value, "userNickName");
            return (Criteria) this;
        }

        public Criteria andUserNickNameLessThan(String value) {
            addCriterion("user_nick_name <", value, "userNickName");
            return (Criteria) this;
        }

        public Criteria andUserNickNameLessThanOrEqualTo(String value) {
            addCriterion("user_nick_name <=", value, "userNickName");
            return (Criteria) this;
        }

        public Criteria andUserNickNameLike(String value) {
            addCriterion("user_nick_name like", value, "userNickName");
            return (Criteria) this;
        }

        public Criteria andUserNickNameNotLike(String value) {
            addCriterion("user_nick_name not like", value, "userNickName");
            return (Criteria) this;
        }

        public Criteria andUserNickNameIn(List<String> values) {
            addCriterion("user_nick_name in", values, "userNickName");
            return (Criteria) this;
        }

        public Criteria andUserNickNameNotIn(List<String> values) {
            addCriterion("user_nick_name not in", values, "userNickName");
            return (Criteria) this;
        }

        public Criteria andUserNickNameBetween(String value1, String value2) {
            addCriterion("user_nick_name between", value1, value2, "userNickName");
            return (Criteria) this;
        }

        public Criteria andUserNickNameNotBetween(String value1, String value2) {
            addCriterion("user_nick_name not between", value1, value2, "userNickName");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlIsNull() {
            addCriterion("avatar_url is null");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlIsNotNull() {
            addCriterion("avatar_url is not null");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlEqualTo(String value) {
            addCriterion("avatar_url =", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlNotEqualTo(String value) {
            addCriterion("avatar_url <>", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlGreaterThan(String value) {
            addCriterion("avatar_url >", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlGreaterThanOrEqualTo(String value) {
            addCriterion("avatar_url >=", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlLessThan(String value) {
            addCriterion("avatar_url <", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlLessThanOrEqualTo(String value) {
            addCriterion("avatar_url <=", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlLike(String value) {
            addCriterion("avatar_url like", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlNotLike(String value) {
            addCriterion("avatar_url not like", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlIn(List<String> values) {
            addCriterion("avatar_url in", values, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlNotIn(List<String> values) {
            addCriterion("avatar_url not in", values, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlBetween(String value1, String value2) {
            addCriterion("avatar_url between", value1, value2, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlNotBetween(String value1, String value2) {
            addCriterion("avatar_url not between", value1, value2, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andWxIdIsNull() {
            addCriterion("wx_id is null");
            return (Criteria) this;
        }

        public Criteria andWxIdIsNotNull() {
            addCriterion("wx_id is not null");
            return (Criteria) this;
        }

        public Criteria andWxIdEqualTo(Integer value) {
            addCriterion("wx_id =", value, "wxId");
            return (Criteria) this;
        }

        public Criteria andWxIdNotEqualTo(Integer value) {
            addCriterion("wx_id <>", value, "wxId");
            return (Criteria) this;
        }

        public Criteria andWxIdGreaterThan(Integer value) {
            addCriterion("wx_id >", value, "wxId");
            return (Criteria) this;
        }

        public Criteria andWxIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("wx_id >=", value, "wxId");
            return (Criteria) this;
        }

        public Criteria andWxIdLessThan(Integer value) {
            addCriterion("wx_id <", value, "wxId");
            return (Criteria) this;
        }

        public Criteria andWxIdLessThanOrEqualTo(Integer value) {
            addCriterion("wx_id <=", value, "wxId");
            return (Criteria) this;
        }

        public Criteria andWxIdIn(List<Integer> values) {
            addCriterion("wx_id in", values, "wxId");
            return (Criteria) this;
        }

        public Criteria andWxIdNotIn(List<Integer> values) {
            addCriterion("wx_id not in", values, "wxId");
            return (Criteria) this;
        }

        public Criteria andWxIdBetween(Integer value1, Integer value2) {
            addCriterion("wx_id between", value1, value2, "wxId");
            return (Criteria) this;
        }

        public Criteria andWxIdNotBetween(Integer value1, Integer value2) {
            addCriterion("wx_id not between", value1, value2, "wxId");
            return (Criteria) this;
        }

        public Criteria andWxNickNameIsNull() {
            addCriterion("wx_nick_name is null");
            return (Criteria) this;
        }

        public Criteria andWxNickNameIsNotNull() {
            addCriterion("wx_nick_name is not null");
            return (Criteria) this;
        }

        public Criteria andWxNickNameEqualTo(String value) {
            addCriterion("wx_nick_name =", value, "wxNickName");
            return (Criteria) this;
        }

        public Criteria andWxNickNameNotEqualTo(String value) {
            addCriterion("wx_nick_name <>", value, "wxNickName");
            return (Criteria) this;
        }

        public Criteria andWxNickNameGreaterThan(String value) {
            addCriterion("wx_nick_name >", value, "wxNickName");
            return (Criteria) this;
        }

        public Criteria andWxNickNameGreaterThanOrEqualTo(String value) {
            addCriterion("wx_nick_name >=", value, "wxNickName");
            return (Criteria) this;
        }

        public Criteria andWxNickNameLessThan(String value) {
            addCriterion("wx_nick_name <", value, "wxNickName");
            return (Criteria) this;
        }

        public Criteria andWxNickNameLessThanOrEqualTo(String value) {
            addCriterion("wx_nick_name <=", value, "wxNickName");
            return (Criteria) this;
        }

        public Criteria andWxNickNameLike(String value) {
            addCriterion("wx_nick_name like", value, "wxNickName");
            return (Criteria) this;
        }

        public Criteria andWxNickNameNotLike(String value) {
            addCriterion("wx_nick_name not like", value, "wxNickName");
            return (Criteria) this;
        }

        public Criteria andWxNickNameIn(List<String> values) {
            addCriterion("wx_nick_name in", values, "wxNickName");
            return (Criteria) this;
        }

        public Criteria andWxNickNameNotIn(List<String> values) {
            addCriterion("wx_nick_name not in", values, "wxNickName");
            return (Criteria) this;
        }

        public Criteria andWxNickNameBetween(String value1, String value2) {
            addCriterion("wx_nick_name between", value1, value2, "wxNickName");
            return (Criteria) this;
        }

        public Criteria andWxNickNameNotBetween(String value1, String value2) {
            addCriterion("wx_nick_name not between", value1, value2, "wxNickName");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdIsNull() {
            addCriterion("user_coupon_id is null");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdIsNotNull() {
            addCriterion("user_coupon_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdEqualTo(Integer value) {
            addCriterion("user_coupon_id =", value, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdNotEqualTo(Integer value) {
            addCriterion("user_coupon_id <>", value, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdGreaterThan(Integer value) {
            addCriterion("user_coupon_id >", value, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_coupon_id >=", value, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdLessThan(Integer value) {
            addCriterion("user_coupon_id <", value, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_coupon_id <=", value, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdIn(List<Integer> values) {
            addCriterion("user_coupon_id in", values, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdNotIn(List<Integer> values) {
            addCriterion("user_coupon_id not in", values, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdBetween(Integer value1, Integer value2) {
            addCriterion("user_coupon_id between", value1, value2, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andUserCouponIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_coupon_id not between", value1, value2, "userCouponId");
            return (Criteria) this;
        }

        public Criteria andActualAmountIsNull() {
            addCriterion("actual_amount is null");
            return (Criteria) this;
        }

        public Criteria andActualAmountIsNotNull() {
            addCriterion("actual_amount is not null");
            return (Criteria) this;
        }

        public Criteria andActualAmountEqualTo(Double value) {
            addCriterion("actual_amount =", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountNotEqualTo(Double value) {
            addCriterion("actual_amount <>", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountGreaterThan(Double value) {
            addCriterion("actual_amount >", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("actual_amount >=", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountLessThan(Double value) {
            addCriterion("actual_amount <", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountLessThanOrEqualTo(Double value) {
            addCriterion("actual_amount <=", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountIn(List<Double> values) {
            addCriterion("actual_amount in", values, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountNotIn(List<Double> values) {
            addCriterion("actual_amount not in", values, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountBetween(Double value1, Double value2) {
            addCriterion("actual_amount between", value1, value2, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountNotBetween(Double value1, Double value2) {
            addCriterion("actual_amount not between", value1, value2, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andReceiverNameIsNull() {
            addCriterion("receiver_name is null");
            return (Criteria) this;
        }

        public Criteria andReceiverNameIsNotNull() {
            addCriterion("receiver_name is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverNameEqualTo(String value) {
            addCriterion("receiver_name =", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotEqualTo(String value) {
            addCriterion("receiver_name <>", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameGreaterThan(String value) {
            addCriterion("receiver_name >", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_name >=", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLessThan(String value) {
            addCriterion("receiver_name <", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLessThanOrEqualTo(String value) {
            addCriterion("receiver_name <=", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLike(String value) {
            addCriterion("receiver_name like", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotLike(String value) {
            addCriterion("receiver_name not like", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameIn(List<String> values) {
            addCriterion("receiver_name in", values, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotIn(List<String> values) {
            addCriterion("receiver_name not in", values, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameBetween(String value1, String value2) {
            addCriterion("receiver_name between", value1, value2, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotBetween(String value1, String value2) {
            addCriterion("receiver_name not between", value1, value2, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileIsNull() {
            addCriterion("receiver_mobile is null");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileIsNotNull() {
            addCriterion("receiver_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileEqualTo(String value) {
            addCriterion("receiver_mobile =", value, "receiverMobile");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileNotEqualTo(String value) {
            addCriterion("receiver_mobile <>", value, "receiverMobile");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileGreaterThan(String value) {
            addCriterion("receiver_mobile >", value, "receiverMobile");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_mobile >=", value, "receiverMobile");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileLessThan(String value) {
            addCriterion("receiver_mobile <", value, "receiverMobile");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileLessThanOrEqualTo(String value) {
            addCriterion("receiver_mobile <=", value, "receiverMobile");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileLike(String value) {
            addCriterion("receiver_mobile like", value, "receiverMobile");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileNotLike(String value) {
            addCriterion("receiver_mobile not like", value, "receiverMobile");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileIn(List<String> values) {
            addCriterion("receiver_mobile in", values, "receiverMobile");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileNotIn(List<String> values) {
            addCriterion("receiver_mobile not in", values, "receiverMobile");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileBetween(String value1, String value2) {
            addCriterion("receiver_mobile between", value1, value2, "receiverMobile");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileNotBetween(String value1, String value2) {
            addCriterion("receiver_mobile not between", value1, value2, "receiverMobile");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceIsNull() {
            addCriterion("receiver_province is null");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceIsNotNull() {
            addCriterion("receiver_province is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceEqualTo(String value) {
            addCriterion("receiver_province =", value, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceNotEqualTo(String value) {
            addCriterion("receiver_province <>", value, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceGreaterThan(String value) {
            addCriterion("receiver_province >", value, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_province >=", value, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceLessThan(String value) {
            addCriterion("receiver_province <", value, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceLessThanOrEqualTo(String value) {
            addCriterion("receiver_province <=", value, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceLike(String value) {
            addCriterion("receiver_province like", value, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceNotLike(String value) {
            addCriterion("receiver_province not like", value, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceIn(List<String> values) {
            addCriterion("receiver_province in", values, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceNotIn(List<String> values) {
            addCriterion("receiver_province not in", values, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceBetween(String value1, String value2) {
            addCriterion("receiver_province between", value1, value2, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverProvinceNotBetween(String value1, String value2) {
            addCriterion("receiver_province not between", value1, value2, "receiverProvince");
            return (Criteria) this;
        }

        public Criteria andReceiverCityIsNull() {
            addCriterion("receiver_city is null");
            return (Criteria) this;
        }

        public Criteria andReceiverCityIsNotNull() {
            addCriterion("receiver_city is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverCityEqualTo(String value) {
            addCriterion("receiver_city =", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityNotEqualTo(String value) {
            addCriterion("receiver_city <>", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityGreaterThan(String value) {
            addCriterion("receiver_city >", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_city >=", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityLessThan(String value) {
            addCriterion("receiver_city <", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityLessThanOrEqualTo(String value) {
            addCriterion("receiver_city <=", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityLike(String value) {
            addCriterion("receiver_city like", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityNotLike(String value) {
            addCriterion("receiver_city not like", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityIn(List<String> values) {
            addCriterion("receiver_city in", values, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityNotIn(List<String> values) {
            addCriterion("receiver_city not in", values, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityBetween(String value1, String value2) {
            addCriterion("receiver_city between", value1, value2, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityNotBetween(String value1, String value2) {
            addCriterion("receiver_city not between", value1, value2, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCountyIsNull() {
            addCriterion("receiver_county is null");
            return (Criteria) this;
        }

        public Criteria andReceiverCountyIsNotNull() {
            addCriterion("receiver_county is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverCountyEqualTo(String value) {
            addCriterion("receiver_county =", value, "receiverCounty");
            return (Criteria) this;
        }

        public Criteria andReceiverCountyNotEqualTo(String value) {
            addCriterion("receiver_county <>", value, "receiverCounty");
            return (Criteria) this;
        }

        public Criteria andReceiverCountyGreaterThan(String value) {
            addCriterion("receiver_county >", value, "receiverCounty");
            return (Criteria) this;
        }

        public Criteria andReceiverCountyGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_county >=", value, "receiverCounty");
            return (Criteria) this;
        }

        public Criteria andReceiverCountyLessThan(String value) {
            addCriterion("receiver_county <", value, "receiverCounty");
            return (Criteria) this;
        }

        public Criteria andReceiverCountyLessThanOrEqualTo(String value) {
            addCriterion("receiver_county <=", value, "receiverCounty");
            return (Criteria) this;
        }

        public Criteria andReceiverCountyLike(String value) {
            addCriterion("receiver_county like", value, "receiverCounty");
            return (Criteria) this;
        }

        public Criteria andReceiverCountyNotLike(String value) {
            addCriterion("receiver_county not like", value, "receiverCounty");
            return (Criteria) this;
        }

        public Criteria andReceiverCountyIn(List<String> values) {
            addCriterion("receiver_county in", values, "receiverCounty");
            return (Criteria) this;
        }

        public Criteria andReceiverCountyNotIn(List<String> values) {
            addCriterion("receiver_county not in", values, "receiverCounty");
            return (Criteria) this;
        }

        public Criteria andReceiverCountyBetween(String value1, String value2) {
            addCriterion("receiver_county between", value1, value2, "receiverCounty");
            return (Criteria) this;
        }

        public Criteria andReceiverCountyNotBetween(String value1, String value2) {
            addCriterion("receiver_county not between", value1, value2, "receiverCounty");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressIsNull() {
            addCriterion("receiver_address is null");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressIsNotNull() {
            addCriterion("receiver_address is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressEqualTo(String value) {
            addCriterion("receiver_address =", value, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressNotEqualTo(String value) {
            addCriterion("receiver_address <>", value, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressGreaterThan(String value) {
            addCriterion("receiver_address >", value, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_address >=", value, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressLessThan(String value) {
            addCriterion("receiver_address <", value, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressLessThanOrEqualTo(String value) {
            addCriterion("receiver_address <=", value, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressLike(String value) {
            addCriterion("receiver_address like", value, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressNotLike(String value) {
            addCriterion("receiver_address not like", value, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressIn(List<String> values) {
            addCriterion("receiver_address in", values, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressNotIn(List<String> values) {
            addCriterion("receiver_address not in", values, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressBetween(String value1, String value2) {
            addCriterion("receiver_address between", value1, value2, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressNotBetween(String value1, String value2) {
            addCriterion("receiver_address not between", value1, value2, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverLatIsNull() {
            addCriterion("receiver_lat is null");
            return (Criteria) this;
        }

        public Criteria andReceiverLatIsNotNull() {
            addCriterion("receiver_lat is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverLatEqualTo(String value) {
            addCriterion("receiver_lat =", value, "receiverLat");
            return (Criteria) this;
        }

        public Criteria andReceiverLatNotEqualTo(String value) {
            addCriterion("receiver_lat <>", value, "receiverLat");
            return (Criteria) this;
        }

        public Criteria andReceiverLatGreaterThan(String value) {
            addCriterion("receiver_lat >", value, "receiverLat");
            return (Criteria) this;
        }

        public Criteria andReceiverLatGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_lat >=", value, "receiverLat");
            return (Criteria) this;
        }

        public Criteria andReceiverLatLessThan(String value) {
            addCriterion("receiver_lat <", value, "receiverLat");
            return (Criteria) this;
        }

        public Criteria andReceiverLatLessThanOrEqualTo(String value) {
            addCriterion("receiver_lat <=", value, "receiverLat");
            return (Criteria) this;
        }

        public Criteria andReceiverLatLike(String value) {
            addCriterion("receiver_lat like", value, "receiverLat");
            return (Criteria) this;
        }

        public Criteria andReceiverLatNotLike(String value) {
            addCriterion("receiver_lat not like", value, "receiverLat");
            return (Criteria) this;
        }

        public Criteria andReceiverLatIn(List<String> values) {
            addCriterion("receiver_lat in", values, "receiverLat");
            return (Criteria) this;
        }

        public Criteria andReceiverLatNotIn(List<String> values) {
            addCriterion("receiver_lat not in", values, "receiverLat");
            return (Criteria) this;
        }

        public Criteria andReceiverLatBetween(String value1, String value2) {
            addCriterion("receiver_lat between", value1, value2, "receiverLat");
            return (Criteria) this;
        }

        public Criteria andReceiverLatNotBetween(String value1, String value2) {
            addCriterion("receiver_lat not between", value1, value2, "receiverLat");
            return (Criteria) this;
        }

        public Criteria andReceiverLonIsNull() {
            addCriterion("receiver_lon is null");
            return (Criteria) this;
        }

        public Criteria andReceiverLonIsNotNull() {
            addCriterion("receiver_lon is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverLonEqualTo(String value) {
            addCriterion("receiver_lon =", value, "receiverLon");
            return (Criteria) this;
        }

        public Criteria andReceiverLonNotEqualTo(String value) {
            addCriterion("receiver_lon <>", value, "receiverLon");
            return (Criteria) this;
        }

        public Criteria andReceiverLonGreaterThan(String value) {
            addCriterion("receiver_lon >", value, "receiverLon");
            return (Criteria) this;
        }

        public Criteria andReceiverLonGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_lon >=", value, "receiverLon");
            return (Criteria) this;
        }

        public Criteria andReceiverLonLessThan(String value) {
            addCriterion("receiver_lon <", value, "receiverLon");
            return (Criteria) this;
        }

        public Criteria andReceiverLonLessThanOrEqualTo(String value) {
            addCriterion("receiver_lon <=", value, "receiverLon");
            return (Criteria) this;
        }

        public Criteria andReceiverLonLike(String value) {
            addCriterion("receiver_lon like", value, "receiverLon");
            return (Criteria) this;
        }

        public Criteria andReceiverLonNotLike(String value) {
            addCriterion("receiver_lon not like", value, "receiverLon");
            return (Criteria) this;
        }

        public Criteria andReceiverLonIn(List<String> values) {
            addCriterion("receiver_lon in", values, "receiverLon");
            return (Criteria) this;
        }

        public Criteria andReceiverLonNotIn(List<String> values) {
            addCriterion("receiver_lon not in", values, "receiverLon");
            return (Criteria) this;
        }

        public Criteria andReceiverLonBetween(String value1, String value2) {
            addCriterion("receiver_lon between", value1, value2, "receiverLon");
            return (Criteria) this;
        }

        public Criteria andReceiverLonNotBetween(String value1, String value2) {
            addCriterion("receiver_lon not between", value1, value2, "receiverLon");
            return (Criteria) this;
        }

        public Criteria andOperateIdIsNull() {
            addCriterion("operate_id is null");
            return (Criteria) this;
        }

        public Criteria andOperateIdIsNotNull() {
            addCriterion("operate_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperateIdEqualTo(Integer value) {
            addCriterion("operate_id =", value, "operateId");
            return (Criteria) this;
        }

        public Criteria andOperateIdNotEqualTo(Integer value) {
            addCriterion("operate_id <>", value, "operateId");
            return (Criteria) this;
        }

        public Criteria andOperateIdGreaterThan(Integer value) {
            addCriterion("operate_id >", value, "operateId");
            return (Criteria) this;
        }

        public Criteria andOperateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("operate_id >=", value, "operateId");
            return (Criteria) this;
        }

        public Criteria andOperateIdLessThan(Integer value) {
            addCriterion("operate_id <", value, "operateId");
            return (Criteria) this;
        }

        public Criteria andOperateIdLessThanOrEqualTo(Integer value) {
            addCriterion("operate_id <=", value, "operateId");
            return (Criteria) this;
        }

        public Criteria andOperateIdIn(List<Integer> values) {
            addCriterion("operate_id in", values, "operateId");
            return (Criteria) this;
        }

        public Criteria andOperateIdNotIn(List<Integer> values) {
            addCriterion("operate_id not in", values, "operateId");
            return (Criteria) this;
        }

        public Criteria andOperateIdBetween(Integer value1, Integer value2) {
            addCriterion("operate_id between", value1, value2, "operateId");
            return (Criteria) this;
        }

        public Criteria andOperateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("operate_id not between", value1, value2, "operateId");
            return (Criteria) this;
        }

        public Criteria andOperateStatusIsNull() {
            addCriterion("operate_status is null");
            return (Criteria) this;
        }

        public Criteria andOperateStatusIsNotNull() {
            addCriterion("operate_status is not null");
            return (Criteria) this;
        }

        public Criteria andOperateStatusEqualTo(Integer value) {
            addCriterion("operate_status =", value, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusNotEqualTo(Integer value) {
            addCriterion("operate_status <>", value, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusGreaterThan(Integer value) {
            addCriterion("operate_status >", value, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("operate_status >=", value, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusLessThan(Integer value) {
            addCriterion("operate_status <", value, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusLessThanOrEqualTo(Integer value) {
            addCriterion("operate_status <=", value, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusIn(List<Integer> values) {
            addCriterion("operate_status in", values, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusNotIn(List<Integer> values) {
            addCriterion("operate_status not in", values, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusBetween(Integer value1, Integer value2) {
            addCriterion("operate_status between", value1, value2, "operateStatus");
            return (Criteria) this;
        }

        public Criteria andOperateStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("operate_status not between", value1, value2, "operateStatus");
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

        public Criteria andOrderStatusEqualTo(Integer value) {
            addCriterion("order_status =", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotEqualTo(Integer value) {
            addCriterion("order_status <>", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThan(Integer value) {
            addCriterion("order_status >", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_status >=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThan(Integer value) {
            addCriterion("order_status <", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThanOrEqualTo(Integer value) {
            addCriterion("order_status <=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIn(List<Integer> values) {
            addCriterion("order_status in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotIn(List<Integer> values) {
            addCriterion("order_status not in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusBetween(Integer value1, Integer value2) {
            addCriterion("order_status between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("order_status not between", value1, value2, "orderStatus");
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

        public Criteria andPayTypeIsNull() {
            addCriterion("pay_type is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("pay_type is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(Integer value) {
            addCriterion("pay_type =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(Integer value) {
            addCriterion("pay_type <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(Integer value) {
            addCriterion("pay_type >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_type >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(Integer value) {
            addCriterion("pay_type <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(Integer value) {
            addCriterion("pay_type <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<Integer> values) {
            addCriterion("pay_type in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<Integer> values) {
            addCriterion("pay_type not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(Integer value1, Integer value2) {
            addCriterion("pay_type between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_type not between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNull() {
            addCriterion("pay_status is null");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNotNull() {
            addCriterion("pay_status is not null");
            return (Criteria) this;
        }

        public Criteria andPayStatusEqualTo(Integer value) {
            addCriterion("pay_status =", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotEqualTo(Integer value) {
            addCriterion("pay_status <>", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThan(Integer value) {
            addCriterion("pay_status >", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_status >=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThan(Integer value) {
            addCriterion("pay_status <", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThanOrEqualTo(Integer value) {
            addCriterion("pay_status <=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusIn(List<Integer> values) {
            addCriterion("pay_status in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotIn(List<Integer> values) {
            addCriterion("pay_status not in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusBetween(Integer value1, Integer value2) {
            addCriterion("pay_status between", value1, value2, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_status not between", value1, value2, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayChannelIsNull() {
            addCriterion("pay_channel is null");
            return (Criteria) this;
        }

        public Criteria andPayChannelIsNotNull() {
            addCriterion("pay_channel is not null");
            return (Criteria) this;
        }

        public Criteria andPayChannelEqualTo(String value) {
            addCriterion("pay_channel =", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelNotEqualTo(String value) {
            addCriterion("pay_channel <>", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelGreaterThan(String value) {
            addCriterion("pay_channel >", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelGreaterThanOrEqualTo(String value) {
            addCriterion("pay_channel >=", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelLessThan(String value) {
            addCriterion("pay_channel <", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelLessThanOrEqualTo(String value) {
            addCriterion("pay_channel <=", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelLike(String value) {
            addCriterion("pay_channel like", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelNotLike(String value) {
            addCriterion("pay_channel not like", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelIn(List<String> values) {
            addCriterion("pay_channel in", values, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelNotIn(List<String> values) {
            addCriterion("pay_channel not in", values, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelBetween(String value1, String value2) {
            addCriterion("pay_channel between", value1, value2, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelNotBetween(String value1, String value2) {
            addCriterion("pay_channel not between", value1, value2, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayRelationIdIsNull() {
            addCriterion("pay_relation_id is null");
            return (Criteria) this;
        }

        public Criteria andPayRelationIdIsNotNull() {
            addCriterion("pay_relation_id is not null");
            return (Criteria) this;
        }

        public Criteria andPayRelationIdEqualTo(String value) {
            addCriterion("pay_relation_id =", value, "payRelationId");
            return (Criteria) this;
        }

        public Criteria andPayRelationIdNotEqualTo(String value) {
            addCriterion("pay_relation_id <>", value, "payRelationId");
            return (Criteria) this;
        }

        public Criteria andPayRelationIdGreaterThan(String value) {
            addCriterion("pay_relation_id >", value, "payRelationId");
            return (Criteria) this;
        }

        public Criteria andPayRelationIdGreaterThanOrEqualTo(String value) {
            addCriterion("pay_relation_id >=", value, "payRelationId");
            return (Criteria) this;
        }

        public Criteria andPayRelationIdLessThan(String value) {
            addCriterion("pay_relation_id <", value, "payRelationId");
            return (Criteria) this;
        }

        public Criteria andPayRelationIdLessThanOrEqualTo(String value) {
            addCriterion("pay_relation_id <=", value, "payRelationId");
            return (Criteria) this;
        }

        public Criteria andPayRelationIdLike(String value) {
            addCriterion("pay_relation_id like", value, "payRelationId");
            return (Criteria) this;
        }

        public Criteria andPayRelationIdNotLike(String value) {
            addCriterion("pay_relation_id not like", value, "payRelationId");
            return (Criteria) this;
        }

        public Criteria andPayRelationIdIn(List<String> values) {
            addCriterion("pay_relation_id in", values, "payRelationId");
            return (Criteria) this;
        }

        public Criteria andPayRelationIdNotIn(List<String> values) {
            addCriterion("pay_relation_id not in", values, "payRelationId");
            return (Criteria) this;
        }

        public Criteria andPayRelationIdBetween(String value1, String value2) {
            addCriterion("pay_relation_id between", value1, value2, "payRelationId");
            return (Criteria) this;
        }

        public Criteria andPayRelationIdNotBetween(String value1, String value2) {
            addCriterion("pay_relation_id not between", value1, value2, "payRelationId");
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