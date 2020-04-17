package com.applet.common.entity;

import com.applet.common.util.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppletInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public AppletInfoExample() {
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

        public Criteria andTypeIdIsNull() {
            addCriterion("type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdEqualTo(Integer value) {
            addCriterion("type_id =", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotEqualTo(Integer value) {
            addCriterion("type_id <>", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThan(Integer value) {
            addCriterion("type_id >", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("type_id >=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThan(Integer value) {
            addCriterion("type_id <", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("type_id <=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIn(List<Integer> values) {
            addCriterion("type_id in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotIn(List<Integer> values) {
            addCriterion("type_id not in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("type_id between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("type_id not between", value1, value2, "typeId");
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

        public Criteria andAppletCodeIsNull() {
            addCriterion("applet_code is null");
            return (Criteria) this;
        }

        public Criteria andAppletCodeIsNotNull() {
            addCriterion("applet_code is not null");
            return (Criteria) this;
        }

        public Criteria andAppletCodeEqualTo(String value) {
            addCriterion("applet_code =", value, "appletCode");
            return (Criteria) this;
        }

        public Criteria andAppletCodeNotEqualTo(String value) {
            addCriterion("applet_code <>", value, "appletCode");
            return (Criteria) this;
        }

        public Criteria andAppletCodeGreaterThan(String value) {
            addCriterion("applet_code >", value, "appletCode");
            return (Criteria) this;
        }

        public Criteria andAppletCodeGreaterThanOrEqualTo(String value) {
            addCriterion("applet_code >=", value, "appletCode");
            return (Criteria) this;
        }

        public Criteria andAppletCodeLessThan(String value) {
            addCriterion("applet_code <", value, "appletCode");
            return (Criteria) this;
        }

        public Criteria andAppletCodeLessThanOrEqualTo(String value) {
            addCriterion("applet_code <=", value, "appletCode");
            return (Criteria) this;
        }

        public Criteria andAppletCodeLike(String value) {
            addCriterion("applet_code like", value, "appletCode");
            return (Criteria) this;
        }

        public Criteria andAppletCodeNotLike(String value) {
            addCriterion("applet_code not like", value, "appletCode");
            return (Criteria) this;
        }

        public Criteria andAppletCodeIn(List<String> values) {
            addCriterion("applet_code in", values, "appletCode");
            return (Criteria) this;
        }

        public Criteria andAppletCodeNotIn(List<String> values) {
            addCriterion("applet_code not in", values, "appletCode");
            return (Criteria) this;
        }

        public Criteria andAppletCodeBetween(String value1, String value2) {
            addCriterion("applet_code between", value1, value2, "appletCode");
            return (Criteria) this;
        }

        public Criteria andAppletCodeNotBetween(String value1, String value2) {
            addCriterion("applet_code not between", value1, value2, "appletCode");
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

        public Criteria andAppletSimpleIsNull() {
            addCriterion("applet_simple is null");
            return (Criteria) this;
        }

        public Criteria andAppletSimpleIsNotNull() {
            addCriterion("applet_simple is not null");
            return (Criteria) this;
        }

        public Criteria andAppletSimpleEqualTo(String value) {
            addCriterion("applet_simple =", value, "appletSimple");
            return (Criteria) this;
        }

        public Criteria andAppletSimpleNotEqualTo(String value) {
            addCriterion("applet_simple <>", value, "appletSimple");
            return (Criteria) this;
        }

        public Criteria andAppletSimpleGreaterThan(String value) {
            addCriterion("applet_simple >", value, "appletSimple");
            return (Criteria) this;
        }

        public Criteria andAppletSimpleGreaterThanOrEqualTo(String value) {
            addCriterion("applet_simple >=", value, "appletSimple");
            return (Criteria) this;
        }

        public Criteria andAppletSimpleLessThan(String value) {
            addCriterion("applet_simple <", value, "appletSimple");
            return (Criteria) this;
        }

        public Criteria andAppletSimpleLessThanOrEqualTo(String value) {
            addCriterion("applet_simple <=", value, "appletSimple");
            return (Criteria) this;
        }

        public Criteria andAppletSimpleLike(String value) {
            addCriterion("applet_simple like", value, "appletSimple");
            return (Criteria) this;
        }

        public Criteria andAppletSimpleNotLike(String value) {
            addCriterion("applet_simple not like", value, "appletSimple");
            return (Criteria) this;
        }

        public Criteria andAppletSimpleIn(List<String> values) {
            addCriterion("applet_simple in", values, "appletSimple");
            return (Criteria) this;
        }

        public Criteria andAppletSimpleNotIn(List<String> values) {
            addCriterion("applet_simple not in", values, "appletSimple");
            return (Criteria) this;
        }

        public Criteria andAppletSimpleBetween(String value1, String value2) {
            addCriterion("applet_simple between", value1, value2, "appletSimple");
            return (Criteria) this;
        }

        public Criteria andAppletSimpleNotBetween(String value1, String value2) {
            addCriterion("applet_simple not between", value1, value2, "appletSimple");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeIsNull() {
            addCriterion("license_code is null");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeIsNotNull() {
            addCriterion("license_code is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeEqualTo(String value) {
            addCriterion("license_code =", value, "licenseCode");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeNotEqualTo(String value) {
            addCriterion("license_code <>", value, "licenseCode");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeGreaterThan(String value) {
            addCriterion("license_code >", value, "licenseCode");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeGreaterThanOrEqualTo(String value) {
            addCriterion("license_code >=", value, "licenseCode");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeLessThan(String value) {
            addCriterion("license_code <", value, "licenseCode");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeLessThanOrEqualTo(String value) {
            addCriterion("license_code <=", value, "licenseCode");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeLike(String value) {
            addCriterion("license_code like", value, "licenseCode");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeNotLike(String value) {
            addCriterion("license_code not like", value, "licenseCode");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeIn(List<String> values) {
            addCriterion("license_code in", values, "licenseCode");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeNotIn(List<String> values) {
            addCriterion("license_code not in", values, "licenseCode");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeBetween(String value1, String value2) {
            addCriterion("license_code between", value1, value2, "licenseCode");
            return (Criteria) this;
        }

        public Criteria andLicenseCodeNotBetween(String value1, String value2) {
            addCriterion("license_code not between", value1, value2, "licenseCode");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeIsNull() {
            addCriterion("business_scope is null");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeIsNotNull() {
            addCriterion("business_scope is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeEqualTo(String value) {
            addCriterion("business_scope =", value, "businessScope");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeNotEqualTo(String value) {
            addCriterion("business_scope <>", value, "businessScope");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeGreaterThan(String value) {
            addCriterion("business_scope >", value, "businessScope");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeGreaterThanOrEqualTo(String value) {
            addCriterion("business_scope >=", value, "businessScope");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeLessThan(String value) {
            addCriterion("business_scope <", value, "businessScope");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeLessThanOrEqualTo(String value) {
            addCriterion("business_scope <=", value, "businessScope");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeLike(String value) {
            addCriterion("business_scope like", value, "businessScope");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeNotLike(String value) {
            addCriterion("business_scope not like", value, "businessScope");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeIn(List<String> values) {
            addCriterion("business_scope in", values, "businessScope");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeNotIn(List<String> values) {
            addCriterion("business_scope not in", values, "businessScope");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeBetween(String value1, String value2) {
            addCriterion("business_scope between", value1, value2, "businessScope");
            return (Criteria) this;
        }

        public Criteria andBusinessScopeNotBetween(String value1, String value2) {
            addCriterion("business_scope not between", value1, value2, "businessScope");
            return (Criteria) this;
        }

        public Criteria andLicenseSrcIsNull() {
            addCriterion("license_src is null");
            return (Criteria) this;
        }

        public Criteria andLicenseSrcIsNotNull() {
            addCriterion("license_src is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseSrcEqualTo(String value) {
            addCriterion("license_src =", value, "licenseSrc");
            return (Criteria) this;
        }

        public Criteria andLicenseSrcNotEqualTo(String value) {
            addCriterion("license_src <>", value, "licenseSrc");
            return (Criteria) this;
        }

        public Criteria andLicenseSrcGreaterThan(String value) {
            addCriterion("license_src >", value, "licenseSrc");
            return (Criteria) this;
        }

        public Criteria andLicenseSrcGreaterThanOrEqualTo(String value) {
            addCriterion("license_src >=", value, "licenseSrc");
            return (Criteria) this;
        }

        public Criteria andLicenseSrcLessThan(String value) {
            addCriterion("license_src <", value, "licenseSrc");
            return (Criteria) this;
        }

        public Criteria andLicenseSrcLessThanOrEqualTo(String value) {
            addCriterion("license_src <=", value, "licenseSrc");
            return (Criteria) this;
        }

        public Criteria andLicenseSrcLike(String value) {
            addCriterion("license_src like", value, "licenseSrc");
            return (Criteria) this;
        }

        public Criteria andLicenseSrcNotLike(String value) {
            addCriterion("license_src not like", value, "licenseSrc");
            return (Criteria) this;
        }

        public Criteria andLicenseSrcIn(List<String> values) {
            addCriterion("license_src in", values, "licenseSrc");
            return (Criteria) this;
        }

        public Criteria andLicenseSrcNotIn(List<String> values) {
            addCriterion("license_src not in", values, "licenseSrc");
            return (Criteria) this;
        }

        public Criteria andLicenseSrcBetween(String value1, String value2) {
            addCriterion("license_src between", value1, value2, "licenseSrc");
            return (Criteria) this;
        }

        public Criteria andLicenseSrcNotBetween(String value1, String value2) {
            addCriterion("license_src not between", value1, value2, "licenseSrc");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNull() {
            addCriterion("telephone is null");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNotNull() {
            addCriterion("telephone is not null");
            return (Criteria) this;
        }

        public Criteria andTelephoneEqualTo(String value) {
            addCriterion("telephone =", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotEqualTo(String value) {
            addCriterion("telephone <>", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThan(String value) {
            addCriterion("telephone >", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("telephone >=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThan(String value) {
            addCriterion("telephone <", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThanOrEqualTo(String value) {
            addCriterion("telephone <=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLike(String value) {
            addCriterion("telephone like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotLike(String value) {
            addCriterion("telephone not like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneIn(List<String> values) {
            addCriterion("telephone in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotIn(List<String> values) {
            addCriterion("telephone not in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneBetween(String value1, String value2) {
            addCriterion("telephone between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotBetween(String value1, String value2) {
            addCriterion("telephone not between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andManagerAccountIsNull() {
            addCriterion("manager_account is null");
            return (Criteria) this;
        }

        public Criteria andManagerAccountIsNotNull() {
            addCriterion("manager_account is not null");
            return (Criteria) this;
        }

        public Criteria andManagerAccountEqualTo(String value) {
            addCriterion("manager_account =", value, "managerAccount");
            return (Criteria) this;
        }

        public Criteria andManagerAccountNotEqualTo(String value) {
            addCriterion("manager_account <>", value, "managerAccount");
            return (Criteria) this;
        }

        public Criteria andManagerAccountGreaterThan(String value) {
            addCriterion("manager_account >", value, "managerAccount");
            return (Criteria) this;
        }

        public Criteria andManagerAccountGreaterThanOrEqualTo(String value) {
            addCriterion("manager_account >=", value, "managerAccount");
            return (Criteria) this;
        }

        public Criteria andManagerAccountLessThan(String value) {
            addCriterion("manager_account <", value, "managerAccount");
            return (Criteria) this;
        }

        public Criteria andManagerAccountLessThanOrEqualTo(String value) {
            addCriterion("manager_account <=", value, "managerAccount");
            return (Criteria) this;
        }

        public Criteria andManagerAccountLike(String value) {
            addCriterion("manager_account like", value, "managerAccount");
            return (Criteria) this;
        }

        public Criteria andManagerAccountNotLike(String value) {
            addCriterion("manager_account not like", value, "managerAccount");
            return (Criteria) this;
        }

        public Criteria andManagerAccountIn(List<String> values) {
            addCriterion("manager_account in", values, "managerAccount");
            return (Criteria) this;
        }

        public Criteria andManagerAccountNotIn(List<String> values) {
            addCriterion("manager_account not in", values, "managerAccount");
            return (Criteria) this;
        }

        public Criteria andManagerAccountBetween(String value1, String value2) {
            addCriterion("manager_account between", value1, value2, "managerAccount");
            return (Criteria) this;
        }

        public Criteria andManagerAccountNotBetween(String value1, String value2) {
            addCriterion("manager_account not between", value1, value2, "managerAccount");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordIsNull() {
            addCriterion("manager_password is null");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordIsNotNull() {
            addCriterion("manager_password is not null");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordEqualTo(String value) {
            addCriterion("manager_password =", value, "managerPassword");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordNotEqualTo(String value) {
            addCriterion("manager_password <>", value, "managerPassword");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordGreaterThan(String value) {
            addCriterion("manager_password >", value, "managerPassword");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("manager_password >=", value, "managerPassword");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordLessThan(String value) {
            addCriterion("manager_password <", value, "managerPassword");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordLessThanOrEqualTo(String value) {
            addCriterion("manager_password <=", value, "managerPassword");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordLike(String value) {
            addCriterion("manager_password like", value, "managerPassword");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordNotLike(String value) {
            addCriterion("manager_password not like", value, "managerPassword");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordIn(List<String> values) {
            addCriterion("manager_password in", values, "managerPassword");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordNotIn(List<String> values) {
            addCriterion("manager_password not in", values, "managerPassword");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordBetween(String value1, String value2) {
            addCriterion("manager_password between", value1, value2, "managerPassword");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordNotBetween(String value1, String value2) {
            addCriterion("manager_password not between", value1, value2, "managerPassword");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNull() {
            addCriterion("app_id is null");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNotNull() {
            addCriterion("app_id is not null");
            return (Criteria) this;
        }

        public Criteria andAppIdEqualTo(String value) {
            addCriterion("app_id =", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotEqualTo(String value) {
            addCriterion("app_id <>", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThan(String value) {
            addCriterion("app_id >", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThanOrEqualTo(String value) {
            addCriterion("app_id >=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThan(String value) {
            addCriterion("app_id <", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThanOrEqualTo(String value) {
            addCriterion("app_id <=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLike(String value) {
            addCriterion("app_id like", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotLike(String value) {
            addCriterion("app_id not like", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdIn(List<String> values) {
            addCriterion("app_id in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotIn(List<String> values) {
            addCriterion("app_id not in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdBetween(String value1, String value2) {
            addCriterion("app_id between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotBetween(String value1, String value2) {
            addCriterion("app_id not between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppSecretIsNull() {
            addCriterion("app_secret is null");
            return (Criteria) this;
        }

        public Criteria andAppSecretIsNotNull() {
            addCriterion("app_secret is not null");
            return (Criteria) this;
        }

        public Criteria andAppSecretEqualTo(String value) {
            addCriterion("app_secret =", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotEqualTo(String value) {
            addCriterion("app_secret <>", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretGreaterThan(String value) {
            addCriterion("app_secret >", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretGreaterThanOrEqualTo(String value) {
            addCriterion("app_secret >=", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLessThan(String value) {
            addCriterion("app_secret <", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLessThanOrEqualTo(String value) {
            addCriterion("app_secret <=", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLike(String value) {
            addCriterion("app_secret like", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotLike(String value) {
            addCriterion("app_secret not like", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretIn(List<String> values) {
            addCriterion("app_secret in", values, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotIn(List<String> values) {
            addCriterion("app_secret not in", values, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretBetween(String value1, String value2) {
            addCriterion("app_secret between", value1, value2, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotBetween(String value1, String value2) {
            addCriterion("app_secret not between", value1, value2, "appSecret");
            return (Criteria) this;
        }

        public Criteria andMchIdIsNull() {
            addCriterion("mch_id is null");
            return (Criteria) this;
        }

        public Criteria andMchIdIsNotNull() {
            addCriterion("mch_id is not null");
            return (Criteria) this;
        }

        public Criteria andMchIdEqualTo(String value) {
            addCriterion("mch_id =", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotEqualTo(String value) {
            addCriterion("mch_id <>", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdGreaterThan(String value) {
            addCriterion("mch_id >", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdGreaterThanOrEqualTo(String value) {
            addCriterion("mch_id >=", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdLessThan(String value) {
            addCriterion("mch_id <", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdLessThanOrEqualTo(String value) {
            addCriterion("mch_id <=", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdLike(String value) {
            addCriterion("mch_id like", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotLike(String value) {
            addCriterion("mch_id not like", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdIn(List<String> values) {
            addCriterion("mch_id in", values, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotIn(List<String> values) {
            addCriterion("mch_id not in", values, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdBetween(String value1, String value2) {
            addCriterion("mch_id between", value1, value2, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotBetween(String value1, String value2) {
            addCriterion("mch_id not between", value1, value2, "mchId");
            return (Criteria) this;
        }

        public Criteria andPayKeyIsNull() {
            addCriterion("pay_key is null");
            return (Criteria) this;
        }

        public Criteria andPayKeyIsNotNull() {
            addCriterion("pay_key is not null");
            return (Criteria) this;
        }

        public Criteria andPayKeyEqualTo(String value) {
            addCriterion("pay_key =", value, "payKey");
            return (Criteria) this;
        }

        public Criteria andPayKeyNotEqualTo(String value) {
            addCriterion("pay_key <>", value, "payKey");
            return (Criteria) this;
        }

        public Criteria andPayKeyGreaterThan(String value) {
            addCriterion("pay_key >", value, "payKey");
            return (Criteria) this;
        }

        public Criteria andPayKeyGreaterThanOrEqualTo(String value) {
            addCriterion("pay_key >=", value, "payKey");
            return (Criteria) this;
        }

        public Criteria andPayKeyLessThan(String value) {
            addCriterion("pay_key <", value, "payKey");
            return (Criteria) this;
        }

        public Criteria andPayKeyLessThanOrEqualTo(String value) {
            addCriterion("pay_key <=", value, "payKey");
            return (Criteria) this;
        }

        public Criteria andPayKeyLike(String value) {
            addCriterion("pay_key like", value, "payKey");
            return (Criteria) this;
        }

        public Criteria andPayKeyNotLike(String value) {
            addCriterion("pay_key not like", value, "payKey");
            return (Criteria) this;
        }

        public Criteria andPayKeyIn(List<String> values) {
            addCriterion("pay_key in", values, "payKey");
            return (Criteria) this;
        }

        public Criteria andPayKeyNotIn(List<String> values) {
            addCriterion("pay_key not in", values, "payKey");
            return (Criteria) this;
        }

        public Criteria andPayKeyBetween(String value1, String value2) {
            addCriterion("pay_key between", value1, value2, "payKey");
            return (Criteria) this;
        }

        public Criteria andPayKeyNotBetween(String value1, String value2) {
            addCriterion("pay_key not between", value1, value2, "payKey");
            return (Criteria) this;
        }

        public Criteria andIfOpenPayIsNull() {
            addCriterion("if_open_pay is null");
            return (Criteria) this;
        }

        public Criteria andIfOpenPayIsNotNull() {
            addCriterion("if_open_pay is not null");
            return (Criteria) this;
        }

        public Criteria andIfOpenPayEqualTo(Boolean value) {
            addCriterion("if_open_pay =", value, "ifOpenPay");
            return (Criteria) this;
        }

        public Criteria andIfOpenPayNotEqualTo(Boolean value) {
            addCriterion("if_open_pay <>", value, "ifOpenPay");
            return (Criteria) this;
        }

        public Criteria andIfOpenPayGreaterThan(Boolean value) {
            addCriterion("if_open_pay >", value, "ifOpenPay");
            return (Criteria) this;
        }

        public Criteria andIfOpenPayGreaterThanOrEqualTo(Boolean value) {
            addCriterion("if_open_pay >=", value, "ifOpenPay");
            return (Criteria) this;
        }

        public Criteria andIfOpenPayLessThan(Boolean value) {
            addCriterion("if_open_pay <", value, "ifOpenPay");
            return (Criteria) this;
        }

        public Criteria andIfOpenPayLessThanOrEqualTo(Boolean value) {
            addCriterion("if_open_pay <=", value, "ifOpenPay");
            return (Criteria) this;
        }

        public Criteria andIfOpenPayIn(List<Boolean> values) {
            addCriterion("if_open_pay in", values, "ifOpenPay");
            return (Criteria) this;
        }

        public Criteria andIfOpenPayNotIn(List<Boolean> values) {
            addCriterion("if_open_pay not in", values, "ifOpenPay");
            return (Criteria) this;
        }

        public Criteria andIfOpenPayBetween(Boolean value1, Boolean value2) {
            addCriterion("if_open_pay between", value1, value2, "ifOpenPay");
            return (Criteria) this;
        }

        public Criteria andIfOpenPayNotBetween(Boolean value1, Boolean value2) {
            addCriterion("if_open_pay not between", value1, value2, "ifOpenPay");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCountyIsNull() {
            addCriterion("county is null");
            return (Criteria) this;
        }

        public Criteria andCountyIsNotNull() {
            addCriterion("county is not null");
            return (Criteria) this;
        }

        public Criteria andCountyEqualTo(String value) {
            addCriterion("county =", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotEqualTo(String value) {
            addCriterion("county <>", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyGreaterThan(String value) {
            addCriterion("county >", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyGreaterThanOrEqualTo(String value) {
            addCriterion("county >=", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLessThan(String value) {
            addCriterion("county <", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLessThanOrEqualTo(String value) {
            addCriterion("county <=", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLike(String value) {
            addCriterion("county like", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotLike(String value) {
            addCriterion("county not like", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyIn(List<String> values) {
            addCriterion("county in", values, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotIn(List<String> values) {
            addCriterion("county not in", values, "county");
            return (Criteria) this;
        }

        public Criteria andCountyBetween(String value1, String value2) {
            addCriterion("county between", value1, value2, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotBetween(String value1, String value2) {
            addCriterion("county not between", value1, value2, "county");
            return (Criteria) this;
        }

        public Criteria andAddressSimpleIsNull() {
            addCriterion("address_simple is null");
            return (Criteria) this;
        }

        public Criteria andAddressSimpleIsNotNull() {
            addCriterion("address_simple is not null");
            return (Criteria) this;
        }

        public Criteria andAddressSimpleEqualTo(String value) {
            addCriterion("address_simple =", value, "addressSimple");
            return (Criteria) this;
        }

        public Criteria andAddressSimpleNotEqualTo(String value) {
            addCriterion("address_simple <>", value, "addressSimple");
            return (Criteria) this;
        }

        public Criteria andAddressSimpleGreaterThan(String value) {
            addCriterion("address_simple >", value, "addressSimple");
            return (Criteria) this;
        }

        public Criteria andAddressSimpleGreaterThanOrEqualTo(String value) {
            addCriterion("address_simple >=", value, "addressSimple");
            return (Criteria) this;
        }

        public Criteria andAddressSimpleLessThan(String value) {
            addCriterion("address_simple <", value, "addressSimple");
            return (Criteria) this;
        }

        public Criteria andAddressSimpleLessThanOrEqualTo(String value) {
            addCriterion("address_simple <=", value, "addressSimple");
            return (Criteria) this;
        }

        public Criteria andAddressSimpleLike(String value) {
            addCriterion("address_simple like", value, "addressSimple");
            return (Criteria) this;
        }

        public Criteria andAddressSimpleNotLike(String value) {
            addCriterion("address_simple not like", value, "addressSimple");
            return (Criteria) this;
        }

        public Criteria andAddressSimpleIn(List<String> values) {
            addCriterion("address_simple in", values, "addressSimple");
            return (Criteria) this;
        }

        public Criteria andAddressSimpleNotIn(List<String> values) {
            addCriterion("address_simple not in", values, "addressSimple");
            return (Criteria) this;
        }

        public Criteria andAddressSimpleBetween(String value1, String value2) {
            addCriterion("address_simple between", value1, value2, "addressSimple");
            return (Criteria) this;
        }

        public Criteria andAddressSimpleNotBetween(String value1, String value2) {
            addCriterion("address_simple not between", value1, value2, "addressSimple");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsIsNull() {
            addCriterion("address_details is null");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsIsNotNull() {
            addCriterion("address_details is not null");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsEqualTo(String value) {
            addCriterion("address_details =", value, "addressDetails");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsNotEqualTo(String value) {
            addCriterion("address_details <>", value, "addressDetails");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsGreaterThan(String value) {
            addCriterion("address_details >", value, "addressDetails");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsGreaterThanOrEqualTo(String value) {
            addCriterion("address_details >=", value, "addressDetails");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsLessThan(String value) {
            addCriterion("address_details <", value, "addressDetails");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsLessThanOrEqualTo(String value) {
            addCriterion("address_details <=", value, "addressDetails");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsLike(String value) {
            addCriterion("address_details like", value, "addressDetails");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsNotLike(String value) {
            addCriterion("address_details not like", value, "addressDetails");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsIn(List<String> values) {
            addCriterion("address_details in", values, "addressDetails");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsNotIn(List<String> values) {
            addCriterion("address_details not in", values, "addressDetails");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsBetween(String value1, String value2) {
            addCriterion("address_details between", value1, value2, "addressDetails");
            return (Criteria) this;
        }

        public Criteria andAddressDetailsNotBetween(String value1, String value2) {
            addCriterion("address_details not between", value1, value2, "addressDetails");
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

        public Criteria andSystemColorIsNull() {
            addCriterion("system_color is null");
            return (Criteria) this;
        }

        public Criteria andSystemColorIsNotNull() {
            addCriterion("system_color is not null");
            return (Criteria) this;
        }

        public Criteria andSystemColorEqualTo(String value) {
            addCriterion("system_color =", value, "systemColor");
            return (Criteria) this;
        }

        public Criteria andSystemColorNotEqualTo(String value) {
            addCriterion("system_color <>", value, "systemColor");
            return (Criteria) this;
        }

        public Criteria andSystemColorGreaterThan(String value) {
            addCriterion("system_color >", value, "systemColor");
            return (Criteria) this;
        }

        public Criteria andSystemColorGreaterThanOrEqualTo(String value) {
            addCriterion("system_color >=", value, "systemColor");
            return (Criteria) this;
        }

        public Criteria andSystemColorLessThan(String value) {
            addCriterion("system_color <", value, "systemColor");
            return (Criteria) this;
        }

        public Criteria andSystemColorLessThanOrEqualTo(String value) {
            addCriterion("system_color <=", value, "systemColor");
            return (Criteria) this;
        }

        public Criteria andSystemColorLike(String value) {
            addCriterion("system_color like", value, "systemColor");
            return (Criteria) this;
        }

        public Criteria andSystemColorNotLike(String value) {
            addCriterion("system_color not like", value, "systemColor");
            return (Criteria) this;
        }

        public Criteria andSystemColorIn(List<String> values) {
            addCriterion("system_color in", values, "systemColor");
            return (Criteria) this;
        }

        public Criteria andSystemColorNotIn(List<String> values) {
            addCriterion("system_color not in", values, "systemColor");
            return (Criteria) this;
        }

        public Criteria andSystemColorBetween(String value1, String value2) {
            addCriterion("system_color between", value1, value2, "systemColor");
            return (Criteria) this;
        }

        public Criteria andSystemColorNotBetween(String value1, String value2) {
            addCriterion("system_color not between", value1, value2, "systemColor");
            return (Criteria) this;
        }

        public Criteria andIfRetailIsNull() {
            addCriterion("if_retail is null");
            return (Criteria) this;
        }

        public Criteria andIfRetailIsNotNull() {
            addCriterion("if_retail is not null");
            return (Criteria) this;
        }

        public Criteria andIfRetailEqualTo(Boolean value) {
            addCriterion("if_retail =", value, "ifRetail");
            return (Criteria) this;
        }

        public Criteria andIfRetailNotEqualTo(Boolean value) {
            addCriterion("if_retail <>", value, "ifRetail");
            return (Criteria) this;
        }

        public Criteria andIfRetailGreaterThan(Boolean value) {
            addCriterion("if_retail >", value, "ifRetail");
            return (Criteria) this;
        }

        public Criteria andIfRetailGreaterThanOrEqualTo(Boolean value) {
            addCriterion("if_retail >=", value, "ifRetail");
            return (Criteria) this;
        }

        public Criteria andIfRetailLessThan(Boolean value) {
            addCriterion("if_retail <", value, "ifRetail");
            return (Criteria) this;
        }

        public Criteria andIfRetailLessThanOrEqualTo(Boolean value) {
            addCriterion("if_retail <=", value, "ifRetail");
            return (Criteria) this;
        }

        public Criteria andIfRetailIn(List<Boolean> values) {
            addCriterion("if_retail in", values, "ifRetail");
            return (Criteria) this;
        }

        public Criteria andIfRetailNotIn(List<Boolean> values) {
            addCriterion("if_retail not in", values, "ifRetail");
            return (Criteria) this;
        }

        public Criteria andIfRetailBetween(Boolean value1, Boolean value2) {
            addCriterion("if_retail between", value1, value2, "ifRetail");
            return (Criteria) this;
        }

        public Criteria andIfRetailNotBetween(Boolean value1, Boolean value2) {
            addCriterion("if_retail not between", value1, value2, "ifRetail");
            return (Criteria) this;
        }

        public Criteria andIfSellingIsNull() {
            addCriterion("if_selling is null");
            return (Criteria) this;
        }

        public Criteria andIfSellingIsNotNull() {
            addCriterion("if_selling is not null");
            return (Criteria) this;
        }

        public Criteria andIfSellingEqualTo(Boolean value) {
            addCriterion("if_selling =", value, "ifSelling");
            return (Criteria) this;
        }

        public Criteria andIfSellingNotEqualTo(Boolean value) {
            addCriterion("if_selling <>", value, "ifSelling");
            return (Criteria) this;
        }

        public Criteria andIfSellingGreaterThan(Boolean value) {
            addCriterion("if_selling >", value, "ifSelling");
            return (Criteria) this;
        }

        public Criteria andIfSellingGreaterThanOrEqualTo(Boolean value) {
            addCriterion("if_selling >=", value, "ifSelling");
            return (Criteria) this;
        }

        public Criteria andIfSellingLessThan(Boolean value) {
            addCriterion("if_selling <", value, "ifSelling");
            return (Criteria) this;
        }

        public Criteria andIfSellingLessThanOrEqualTo(Boolean value) {
            addCriterion("if_selling <=", value, "ifSelling");
            return (Criteria) this;
        }

        public Criteria andIfSellingIn(List<Boolean> values) {
            addCriterion("if_selling in", values, "ifSelling");
            return (Criteria) this;
        }

        public Criteria andIfSellingNotIn(List<Boolean> values) {
            addCriterion("if_selling not in", values, "ifSelling");
            return (Criteria) this;
        }

        public Criteria andIfSellingBetween(Boolean value1, Boolean value2) {
            addCriterion("if_selling between", value1, value2, "ifSelling");
            return (Criteria) this;
        }

        public Criteria andIfSellingNotBetween(Boolean value1, Boolean value2) {
            addCriterion("if_selling not between", value1, value2, "ifSelling");
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