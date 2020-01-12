package com.applet.apply.entity;

import com.applet.common.util.Page;
import java.math.BigDecimal;
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

        public Criteria andNickNameIsNull() {
            addCriterion("nick_name is null");
            return (Criteria) this;
        }

        public Criteria andNickNameIsNotNull() {
            addCriterion("nick_name is not null");
            return (Criteria) this;
        }

        public Criteria andNickNameEqualTo(String value) {
            addCriterion("nick_name =", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotEqualTo(String value) {
            addCriterion("nick_name <>", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameGreaterThan(String value) {
            addCriterion("nick_name >", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameGreaterThanOrEqualTo(String value) {
            addCriterion("nick_name >=", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLessThan(String value) {
            addCriterion("nick_name <", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLessThanOrEqualTo(String value) {
            addCriterion("nick_name <=", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLike(String value) {
            addCriterion("nick_name like", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotLike(String value) {
            addCriterion("nick_name not like", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameIn(List<String> values) {
            addCriterion("nick_name in", values, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotIn(List<String> values) {
            addCriterion("nick_name not in", values, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameBetween(String value1, String value2) {
            addCriterion("nick_name between", value1, value2, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotBetween(String value1, String value2) {
            addCriterion("nick_name not between", value1, value2, "nickName");
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

        public Criteria andReceiverPhoneIsNull() {
            addCriterion("receiver_phone is null");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneIsNotNull() {
            addCriterion("receiver_phone is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneEqualTo(String value) {
            addCriterion("receiver_phone =", value, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneNotEqualTo(String value) {
            addCriterion("receiver_phone <>", value, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneGreaterThan(String value) {
            addCriterion("receiver_phone >", value, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_phone >=", value, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneLessThan(String value) {
            addCriterion("receiver_phone <", value, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneLessThanOrEqualTo(String value) {
            addCriterion("receiver_phone <=", value, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneLike(String value) {
            addCriterion("receiver_phone like", value, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneNotLike(String value) {
            addCriterion("receiver_phone not like", value, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneIn(List<String> values) {
            addCriterion("receiver_phone in", values, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneNotIn(List<String> values) {
            addCriterion("receiver_phone not in", values, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneBetween(String value1, String value2) {
            addCriterion("receiver_phone between", value1, value2, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andReceiverPhoneNotBetween(String value1, String value2) {
            addCriterion("receiver_phone not between", value1, value2, "receiverPhone");
            return (Criteria) this;
        }

        public Criteria andDetailAddrIsNull() {
            addCriterion("detail_addr is null");
            return (Criteria) this;
        }

        public Criteria andDetailAddrIsNotNull() {
            addCriterion("detail_addr is not null");
            return (Criteria) this;
        }

        public Criteria andDetailAddrEqualTo(String value) {
            addCriterion("detail_addr =", value, "detailAddr");
            return (Criteria) this;
        }

        public Criteria andDetailAddrNotEqualTo(String value) {
            addCriterion("detail_addr <>", value, "detailAddr");
            return (Criteria) this;
        }

        public Criteria andDetailAddrGreaterThan(String value) {
            addCriterion("detail_addr >", value, "detailAddr");
            return (Criteria) this;
        }

        public Criteria andDetailAddrGreaterThanOrEqualTo(String value) {
            addCriterion("detail_addr >=", value, "detailAddr");
            return (Criteria) this;
        }

        public Criteria andDetailAddrLessThan(String value) {
            addCriterion("detail_addr <", value, "detailAddr");
            return (Criteria) this;
        }

        public Criteria andDetailAddrLessThanOrEqualTo(String value) {
            addCriterion("detail_addr <=", value, "detailAddr");
            return (Criteria) this;
        }

        public Criteria andDetailAddrLike(String value) {
            addCriterion("detail_addr like", value, "detailAddr");
            return (Criteria) this;
        }

        public Criteria andDetailAddrNotLike(String value) {
            addCriterion("detail_addr not like", value, "detailAddr");
            return (Criteria) this;
        }

        public Criteria andDetailAddrIn(List<String> values) {
            addCriterion("detail_addr in", values, "detailAddr");
            return (Criteria) this;
        }

        public Criteria andDetailAddrNotIn(List<String> values) {
            addCriterion("detail_addr not in", values, "detailAddr");
            return (Criteria) this;
        }

        public Criteria andDetailAddrBetween(String value1, String value2) {
            addCriterion("detail_addr between", value1, value2, "detailAddr");
            return (Criteria) this;
        }

        public Criteria andDetailAddrNotBetween(String value1, String value2) {
            addCriterion("detail_addr not between", value1, value2, "detailAddr");
            return (Criteria) this;
        }

        public Criteria andLatIsNull() {
            addCriterion("lat is null");
            return (Criteria) this;
        }

        public Criteria andLatIsNotNull() {
            addCriterion("lat is not null");
            return (Criteria) this;
        }

        public Criteria andLatEqualTo(Double value) {
            addCriterion("lat =", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotEqualTo(Double value) {
            addCriterion("lat <>", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThan(Double value) {
            addCriterion("lat >", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThanOrEqualTo(Double value) {
            addCriterion("lat >=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThan(Double value) {
            addCriterion("lat <", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThanOrEqualTo(Double value) {
            addCriterion("lat <=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatIn(List<Double> values) {
            addCriterion("lat in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotIn(List<Double> values) {
            addCriterion("lat not in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatBetween(Double value1, Double value2) {
            addCriterion("lat between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotBetween(Double value1, Double value2) {
            addCriterion("lat not between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andLonIsNull() {
            addCriterion("lon is null");
            return (Criteria) this;
        }

        public Criteria andLonIsNotNull() {
            addCriterion("lon is not null");
            return (Criteria) this;
        }

        public Criteria andLonEqualTo(Double value) {
            addCriterion("lon =", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonNotEqualTo(Double value) {
            addCriterion("lon <>", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonGreaterThan(Double value) {
            addCriterion("lon >", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonGreaterThanOrEqualTo(Double value) {
            addCriterion("lon >=", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonLessThan(Double value) {
            addCriterion("lon <", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonLessThanOrEqualTo(Double value) {
            addCriterion("lon <=", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonIn(List<Double> values) {
            addCriterion("lon in", values, "lon");
            return (Criteria) this;
        }

        public Criteria andLonNotIn(List<Double> values) {
            addCriterion("lon not in", values, "lon");
            return (Criteria) this;
        }

        public Criteria andLonBetween(Double value1, Double value2) {
            addCriterion("lon between", value1, value2, "lon");
            return (Criteria) this;
        }

        public Criteria andLonNotBetween(Double value1, Double value2) {
            addCriterion("lon not between", value1, value2, "lon");
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

        public Criteria andTicketAmountIsNull() {
            addCriterion("ticket_amount is null");
            return (Criteria) this;
        }

        public Criteria andTicketAmountIsNotNull() {
            addCriterion("ticket_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTicketAmountEqualTo(BigDecimal value) {
            addCriterion("ticket_amount =", value, "ticketAmount");
            return (Criteria) this;
        }

        public Criteria andTicketAmountNotEqualTo(BigDecimal value) {
            addCriterion("ticket_amount <>", value, "ticketAmount");
            return (Criteria) this;
        }

        public Criteria andTicketAmountGreaterThan(BigDecimal value) {
            addCriterion("ticket_amount >", value, "ticketAmount");
            return (Criteria) this;
        }

        public Criteria andTicketAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ticket_amount >=", value, "ticketAmount");
            return (Criteria) this;
        }

        public Criteria andTicketAmountLessThan(BigDecimal value) {
            addCriterion("ticket_amount <", value, "ticketAmount");
            return (Criteria) this;
        }

        public Criteria andTicketAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ticket_amount <=", value, "ticketAmount");
            return (Criteria) this;
        }

        public Criteria andTicketAmountIn(List<BigDecimal> values) {
            addCriterion("ticket_amount in", values, "ticketAmount");
            return (Criteria) this;
        }

        public Criteria andTicketAmountNotIn(List<BigDecimal> values) {
            addCriterion("ticket_amount not in", values, "ticketAmount");
            return (Criteria) this;
        }

        public Criteria andTicketAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ticket_amount between", value1, value2, "ticketAmount");
            return (Criteria) this;
        }

        public Criteria andTicketAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ticket_amount not between", value1, value2, "ticketAmount");
            return (Criteria) this;
        }

        public Criteria andCarriersFeeIsNull() {
            addCriterion("carriers_fee is null");
            return (Criteria) this;
        }

        public Criteria andCarriersFeeIsNotNull() {
            addCriterion("carriers_fee is not null");
            return (Criteria) this;
        }

        public Criteria andCarriersFeeEqualTo(BigDecimal value) {
            addCriterion("carriers_fee =", value, "carriersFee");
            return (Criteria) this;
        }

        public Criteria andCarriersFeeNotEqualTo(BigDecimal value) {
            addCriterion("carriers_fee <>", value, "carriersFee");
            return (Criteria) this;
        }

        public Criteria andCarriersFeeGreaterThan(BigDecimal value) {
            addCriterion("carriers_fee >", value, "carriersFee");
            return (Criteria) this;
        }

        public Criteria andCarriersFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("carriers_fee >=", value, "carriersFee");
            return (Criteria) this;
        }

        public Criteria andCarriersFeeLessThan(BigDecimal value) {
            addCriterion("carriers_fee <", value, "carriersFee");
            return (Criteria) this;
        }

        public Criteria andCarriersFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("carriers_fee <=", value, "carriersFee");
            return (Criteria) this;
        }

        public Criteria andCarriersFeeIn(List<BigDecimal> values) {
            addCriterion("carriers_fee in", values, "carriersFee");
            return (Criteria) this;
        }

        public Criteria andCarriersFeeNotIn(List<BigDecimal> values) {
            addCriterion("carriers_fee not in", values, "carriersFee");
            return (Criteria) this;
        }

        public Criteria andCarriersFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("carriers_fee between", value1, value2, "carriersFee");
            return (Criteria) this;
        }

        public Criteria andCarriersFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("carriers_fee not between", value1, value2, "carriersFee");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNull() {
            addCriterion("total_amount is null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNotNull() {
            addCriterion("total_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountEqualTo(BigDecimal value) {
            addCriterion("total_amount =", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotEqualTo(BigDecimal value) {
            addCriterion("total_amount <>", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThan(BigDecimal value) {
            addCriterion("total_amount >", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_amount >=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThan(BigDecimal value) {
            addCriterion("total_amount <", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_amount <=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIn(List<BigDecimal> values) {
            addCriterion("total_amount in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotIn(List<BigDecimal> values) {
            addCriterion("total_amount not in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_amount between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_amount not between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkIsNull() {
            addCriterion("order_remark is null");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkIsNotNull() {
            addCriterion("order_remark is not null");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkEqualTo(String value) {
            addCriterion("order_remark =", value, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkNotEqualTo(String value) {
            addCriterion("order_remark <>", value, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkGreaterThan(String value) {
            addCriterion("order_remark >", value, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("order_remark >=", value, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkLessThan(String value) {
            addCriterion("order_remark <", value, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkLessThanOrEqualTo(String value) {
            addCriterion("order_remark <=", value, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkLike(String value) {
            addCriterion("order_remark like", value, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkNotLike(String value) {
            addCriterion("order_remark not like", value, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkIn(List<String> values) {
            addCriterion("order_remark in", values, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkNotIn(List<String> values) {
            addCriterion("order_remark not in", values, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkBetween(String value1, String value2) {
            addCriterion("order_remark between", value1, value2, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkNotBetween(String value1, String value2) {
            addCriterion("order_remark not between", value1, value2, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
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

        public Criteria andPayTypeEqualTo(Byte value) {
            addCriterion("pay_type =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(Byte value) {
            addCriterion("pay_type <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(Byte value) {
            addCriterion("pay_type >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("pay_type >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(Byte value) {
            addCriterion("pay_type <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(Byte value) {
            addCriterion("pay_type <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<Byte> values) {
            addCriterion("pay_type in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<Byte> values) {
            addCriterion("pay_type not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(Byte value1, Byte value2) {
            addCriterion("pay_type between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("pay_type not between", value1, value2, "payType");
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