package com.applet.common.entity;

import com.applet.common.util.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppletAdvertRelationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public AppletAdvertRelationExample() {
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

        public Criteria andAppletTypeIdIsNull() {
            addCriterion("applet_type_id is null");
            return (Criteria) this;
        }

        public Criteria andAppletTypeIdIsNotNull() {
            addCriterion("applet_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andAppletTypeIdEqualTo(Integer value) {
            addCriterion("applet_type_id =", value, "appletTypeId");
            return (Criteria) this;
        }

        public Criteria andAppletTypeIdNotEqualTo(Integer value) {
            addCriterion("applet_type_id <>", value, "appletTypeId");
            return (Criteria) this;
        }

        public Criteria andAppletTypeIdGreaterThan(Integer value) {
            addCriterion("applet_type_id >", value, "appletTypeId");
            return (Criteria) this;
        }

        public Criteria andAppletTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("applet_type_id >=", value, "appletTypeId");
            return (Criteria) this;
        }

        public Criteria andAppletTypeIdLessThan(Integer value) {
            addCriterion("applet_type_id <", value, "appletTypeId");
            return (Criteria) this;
        }

        public Criteria andAppletTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("applet_type_id <=", value, "appletTypeId");
            return (Criteria) this;
        }

        public Criteria andAppletTypeIdIn(List<Integer> values) {
            addCriterion("applet_type_id in", values, "appletTypeId");
            return (Criteria) this;
        }

        public Criteria andAppletTypeIdNotIn(List<Integer> values) {
            addCriterion("applet_type_id not in", values, "appletTypeId");
            return (Criteria) this;
        }

        public Criteria andAppletTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("applet_type_id between", value1, value2, "appletTypeId");
            return (Criteria) this;
        }

        public Criteria andAppletTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("applet_type_id not between", value1, value2, "appletTypeId");
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

        public Criteria andRelationTypeIsNull() {
            addCriterion("relation_type is null");
            return (Criteria) this;
        }

        public Criteria andRelationTypeIsNotNull() {
            addCriterion("relation_type is not null");
            return (Criteria) this;
        }

        public Criteria andRelationTypeEqualTo(Integer value) {
            addCriterion("relation_type =", value, "relationType");
            return (Criteria) this;
        }

        public Criteria andRelationTypeNotEqualTo(Integer value) {
            addCriterion("relation_type <>", value, "relationType");
            return (Criteria) this;
        }

        public Criteria andRelationTypeGreaterThan(Integer value) {
            addCriterion("relation_type >", value, "relationType");
            return (Criteria) this;
        }

        public Criteria andRelationTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("relation_type >=", value, "relationType");
            return (Criteria) this;
        }

        public Criteria andRelationTypeLessThan(Integer value) {
            addCriterion("relation_type <", value, "relationType");
            return (Criteria) this;
        }

        public Criteria andRelationTypeLessThanOrEqualTo(Integer value) {
            addCriterion("relation_type <=", value, "relationType");
            return (Criteria) this;
        }

        public Criteria andRelationTypeIn(List<Integer> values) {
            addCriterion("relation_type in", values, "relationType");
            return (Criteria) this;
        }

        public Criteria andRelationTypeNotIn(List<Integer> values) {
            addCriterion("relation_type not in", values, "relationType");
            return (Criteria) this;
        }

        public Criteria andRelationTypeBetween(Integer value1, Integer value2) {
            addCriterion("relation_type between", value1, value2, "relationType");
            return (Criteria) this;
        }

        public Criteria andRelationTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("relation_type not between", value1, value2, "relationType");
            return (Criteria) this;
        }

        public Criteria andRelationImageIsNull() {
            addCriterion("relation_image is null");
            return (Criteria) this;
        }

        public Criteria andRelationImageIsNotNull() {
            addCriterion("relation_image is not null");
            return (Criteria) this;
        }

        public Criteria andRelationImageEqualTo(String value) {
            addCriterion("relation_image =", value, "relationImage");
            return (Criteria) this;
        }

        public Criteria andRelationImageNotEqualTo(String value) {
            addCriterion("relation_image <>", value, "relationImage");
            return (Criteria) this;
        }

        public Criteria andRelationImageGreaterThan(String value) {
            addCriterion("relation_image >", value, "relationImage");
            return (Criteria) this;
        }

        public Criteria andRelationImageGreaterThanOrEqualTo(String value) {
            addCriterion("relation_image >=", value, "relationImage");
            return (Criteria) this;
        }

        public Criteria andRelationImageLessThan(String value) {
            addCriterion("relation_image <", value, "relationImage");
            return (Criteria) this;
        }

        public Criteria andRelationImageLessThanOrEqualTo(String value) {
            addCriterion("relation_image <=", value, "relationImage");
            return (Criteria) this;
        }

        public Criteria andRelationImageLike(String value) {
            addCriterion("relation_image like", value, "relationImage");
            return (Criteria) this;
        }

        public Criteria andRelationImageNotLike(String value) {
            addCriterion("relation_image not like", value, "relationImage");
            return (Criteria) this;
        }

        public Criteria andRelationImageIn(List<String> values) {
            addCriterion("relation_image in", values, "relationImage");
            return (Criteria) this;
        }

        public Criteria andRelationImageNotIn(List<String> values) {
            addCriterion("relation_image not in", values, "relationImage");
            return (Criteria) this;
        }

        public Criteria andRelationImageBetween(String value1, String value2) {
            addCriterion("relation_image between", value1, value2, "relationImage");
            return (Criteria) this;
        }

        public Criteria andRelationImageNotBetween(String value1, String value2) {
            addCriterion("relation_image not between", value1, value2, "relationImage");
            return (Criteria) this;
        }

        public Criteria andRelationWebsiteIsNull() {
            addCriterion("relation_website is null");
            return (Criteria) this;
        }

        public Criteria andRelationWebsiteIsNotNull() {
            addCriterion("relation_website is not null");
            return (Criteria) this;
        }

        public Criteria andRelationWebsiteEqualTo(String value) {
            addCriterion("relation_website =", value, "relationWebsite");
            return (Criteria) this;
        }

        public Criteria andRelationWebsiteNotEqualTo(String value) {
            addCriterion("relation_website <>", value, "relationWebsite");
            return (Criteria) this;
        }

        public Criteria andRelationWebsiteGreaterThan(String value) {
            addCriterion("relation_website >", value, "relationWebsite");
            return (Criteria) this;
        }

        public Criteria andRelationWebsiteGreaterThanOrEqualTo(String value) {
            addCriterion("relation_website >=", value, "relationWebsite");
            return (Criteria) this;
        }

        public Criteria andRelationWebsiteLessThan(String value) {
            addCriterion("relation_website <", value, "relationWebsite");
            return (Criteria) this;
        }

        public Criteria andRelationWebsiteLessThanOrEqualTo(String value) {
            addCriterion("relation_website <=", value, "relationWebsite");
            return (Criteria) this;
        }

        public Criteria andRelationWebsiteLike(String value) {
            addCriterion("relation_website like", value, "relationWebsite");
            return (Criteria) this;
        }

        public Criteria andRelationWebsiteNotLike(String value) {
            addCriterion("relation_website not like", value, "relationWebsite");
            return (Criteria) this;
        }

        public Criteria andRelationWebsiteIn(List<String> values) {
            addCriterion("relation_website in", values, "relationWebsite");
            return (Criteria) this;
        }

        public Criteria andRelationWebsiteNotIn(List<String> values) {
            addCriterion("relation_website not in", values, "relationWebsite");
            return (Criteria) this;
        }

        public Criteria andRelationWebsiteBetween(String value1, String value2) {
            addCriterion("relation_website between", value1, value2, "relationWebsite");
            return (Criteria) this;
        }

        public Criteria andRelationWebsiteNotBetween(String value1, String value2) {
            addCriterion("relation_website not between", value1, value2, "relationWebsite");
            return (Criteria) this;
        }

        public Criteria andRelationNameIsNull() {
            addCriterion("relation_name is null");
            return (Criteria) this;
        }

        public Criteria andRelationNameIsNotNull() {
            addCriterion("relation_name is not null");
            return (Criteria) this;
        }

        public Criteria andRelationNameEqualTo(String value) {
            addCriterion("relation_name =", value, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameNotEqualTo(String value) {
            addCriterion("relation_name <>", value, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameGreaterThan(String value) {
            addCriterion("relation_name >", value, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameGreaterThanOrEqualTo(String value) {
            addCriterion("relation_name >=", value, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameLessThan(String value) {
            addCriterion("relation_name <", value, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameLessThanOrEqualTo(String value) {
            addCriterion("relation_name <=", value, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameLike(String value) {
            addCriterion("relation_name like", value, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameNotLike(String value) {
            addCriterion("relation_name not like", value, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameIn(List<String> values) {
            addCriterion("relation_name in", values, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameNotIn(List<String> values) {
            addCriterion("relation_name not in", values, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameBetween(String value1, String value2) {
            addCriterion("relation_name between", value1, value2, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameNotBetween(String value1, String value2) {
            addCriterion("relation_name not between", value1, value2, "relationName");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIsNull() {
            addCriterion("expire_time is null");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIsNotNull() {
            addCriterion("expire_time is not null");
            return (Criteria) this;
        }

        public Criteria andExpireTimeEqualTo(Date value) {
            addCriterion("expire_time =", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotEqualTo(Date value) {
            addCriterion("expire_time <>", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeGreaterThan(Date value) {
            addCriterion("expire_time >", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("expire_time >=", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeLessThan(Date value) {
            addCriterion("expire_time <", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeLessThanOrEqualTo(Date value) {
            addCriterion("expire_time <=", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIn(List<Date> values) {
            addCriterion("expire_time in", values, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotIn(List<Date> values) {
            addCriterion("expire_time not in", values, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeBetween(Date value1, Date value2) {
            addCriterion("expire_time between", value1, value2, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotBetween(Date value1, Date value2) {
            addCriterion("expire_time not between", value1, value2, "expireTime");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIsNull() {
            addCriterion("is_default is null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIsNotNull() {
            addCriterion("is_default is not null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultEqualTo(Boolean value) {
            addCriterion("is_default =", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotEqualTo(Boolean value) {
            addCriterion("is_default <>", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultGreaterThan(Boolean value) {
            addCriterion("is_default >", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_default >=", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLessThan(Boolean value) {
            addCriterion("is_default <", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLessThanOrEqualTo(Boolean value) {
            addCriterion("is_default <=", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIn(List<Boolean> values) {
            addCriterion("is_default in", values, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotIn(List<Boolean> values) {
            addCriterion("is_default not in", values, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultBetween(Boolean value1, Boolean value2) {
            addCriterion("is_default between", value1, value2, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_default not between", value1, value2, "isDefault");
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

        public Criteria andRelationStatusIsNull() {
            addCriterion("relation_status is null");
            return (Criteria) this;
        }

        public Criteria andRelationStatusIsNotNull() {
            addCriterion("relation_status is not null");
            return (Criteria) this;
        }

        public Criteria andRelationStatusEqualTo(Boolean value) {
            addCriterion("relation_status =", value, "relationStatus");
            return (Criteria) this;
        }

        public Criteria andRelationStatusNotEqualTo(Boolean value) {
            addCriterion("relation_status <>", value, "relationStatus");
            return (Criteria) this;
        }

        public Criteria andRelationStatusGreaterThan(Boolean value) {
            addCriterion("relation_status >", value, "relationStatus");
            return (Criteria) this;
        }

        public Criteria andRelationStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("relation_status >=", value, "relationStatus");
            return (Criteria) this;
        }

        public Criteria andRelationStatusLessThan(Boolean value) {
            addCriterion("relation_status <", value, "relationStatus");
            return (Criteria) this;
        }

        public Criteria andRelationStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("relation_status <=", value, "relationStatus");
            return (Criteria) this;
        }

        public Criteria andRelationStatusIn(List<Boolean> values) {
            addCriterion("relation_status in", values, "relationStatus");
            return (Criteria) this;
        }

        public Criteria andRelationStatusNotIn(List<Boolean> values) {
            addCriterion("relation_status not in", values, "relationStatus");
            return (Criteria) this;
        }

        public Criteria andRelationStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("relation_status between", value1, value2, "relationStatus");
            return (Criteria) this;
        }

        public Criteria andRelationStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("relation_status not between", value1, value2, "relationStatus");
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