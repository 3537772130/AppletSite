package com.applet.manage.entity;

import com.applet.manage.util.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewAppletPageElementContentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public ViewAppletPageElementContentExample() {
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

        public Criteria andVersionNumberIsNull() {
            addCriterion("version_number is null");
            return (Criteria) this;
        }

        public Criteria andVersionNumberIsNotNull() {
            addCriterion("version_number is not null");
            return (Criteria) this;
        }

        public Criteria andVersionNumberEqualTo(String value) {
            addCriterion("version_number =", value, "versionNumber");
            return (Criteria) this;
        }

        public Criteria andVersionNumberNotEqualTo(String value) {
            addCriterion("version_number <>", value, "versionNumber");
            return (Criteria) this;
        }

        public Criteria andVersionNumberGreaterThan(String value) {
            addCriterion("version_number >", value, "versionNumber");
            return (Criteria) this;
        }

        public Criteria andVersionNumberGreaterThanOrEqualTo(String value) {
            addCriterion("version_number >=", value, "versionNumber");
            return (Criteria) this;
        }

        public Criteria andVersionNumberLessThan(String value) {
            addCriterion("version_number <", value, "versionNumber");
            return (Criteria) this;
        }

        public Criteria andVersionNumberLessThanOrEqualTo(String value) {
            addCriterion("version_number <=", value, "versionNumber");
            return (Criteria) this;
        }

        public Criteria andVersionNumberLike(String value) {
            addCriterion("version_number like", value, "versionNumber");
            return (Criteria) this;
        }

        public Criteria andVersionNumberNotLike(String value) {
            addCriterion("version_number not like", value, "versionNumber");
            return (Criteria) this;
        }

        public Criteria andVersionNumberIn(List<String> values) {
            addCriterion("version_number in", values, "versionNumber");
            return (Criteria) this;
        }

        public Criteria andVersionNumberNotIn(List<String> values) {
            addCriterion("version_number not in", values, "versionNumber");
            return (Criteria) this;
        }

        public Criteria andVersionNumberBetween(String value1, String value2) {
            addCriterion("version_number between", value1, value2, "versionNumber");
            return (Criteria) this;
        }

        public Criteria andVersionNumberNotBetween(String value1, String value2) {
            addCriterion("version_number not between", value1, value2, "versionNumber");
            return (Criteria) this;
        }

        public Criteria andPageIdIsNull() {
            addCriterion("page_id is null");
            return (Criteria) this;
        }

        public Criteria andPageIdIsNotNull() {
            addCriterion("page_id is not null");
            return (Criteria) this;
        }

        public Criteria andPageIdEqualTo(Integer value) {
            addCriterion("page_id =", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdNotEqualTo(Integer value) {
            addCriterion("page_id <>", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdGreaterThan(Integer value) {
            addCriterion("page_id >", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("page_id >=", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdLessThan(Integer value) {
            addCriterion("page_id <", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdLessThanOrEqualTo(Integer value) {
            addCriterion("page_id <=", value, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdIn(List<Integer> values) {
            addCriterion("page_id in", values, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdNotIn(List<Integer> values) {
            addCriterion("page_id not in", values, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdBetween(Integer value1, Integer value2) {
            addCriterion("page_id between", value1, value2, "pageId");
            return (Criteria) this;
        }

        public Criteria andPageIdNotBetween(Integer value1, Integer value2) {
            addCriterion("page_id not between", value1, value2, "pageId");
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

        public Criteria andElementIdIsNull() {
            addCriterion("element_id is null");
            return (Criteria) this;
        }

        public Criteria andElementIdIsNotNull() {
            addCriterion("element_id is not null");
            return (Criteria) this;
        }

        public Criteria andElementIdEqualTo(Integer value) {
            addCriterion("element_id =", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdNotEqualTo(Integer value) {
            addCriterion("element_id <>", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdGreaterThan(Integer value) {
            addCriterion("element_id >", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("element_id >=", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdLessThan(Integer value) {
            addCriterion("element_id <", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdLessThanOrEqualTo(Integer value) {
            addCriterion("element_id <=", value, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdIn(List<Integer> values) {
            addCriterion("element_id in", values, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdNotIn(List<Integer> values) {
            addCriterion("element_id not in", values, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdBetween(Integer value1, Integer value2) {
            addCriterion("element_id between", value1, value2, "elementId");
            return (Criteria) this;
        }

        public Criteria andElementIdNotBetween(Integer value1, Integer value2) {
            addCriterion("element_id not between", value1, value2, "elementId");
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

        public Criteria andTypeNameIsNull() {
            addCriterion("type_name is null");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNotNull() {
            addCriterion("type_name is not null");
            return (Criteria) this;
        }

        public Criteria andTypeNameEqualTo(String value) {
            addCriterion("type_name =", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotEqualTo(String value) {
            addCriterion("type_name <>", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThan(String value) {
            addCriterion("type_name >", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("type_name >=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThan(String value) {
            addCriterion("type_name <", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThanOrEqualTo(String value) {
            addCriterion("type_name <=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLike(String value) {
            addCriterion("type_name like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotLike(String value) {
            addCriterion("type_name not like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameIn(List<String> values) {
            addCriterion("type_name in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotIn(List<String> values) {
            addCriterion("type_name not in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameBetween(String value1, String value2) {
            addCriterion("type_name between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotBetween(String value1, String value2) {
            addCriterion("type_name not between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andElementLogoIsNull() {
            addCriterion("element_logo is null");
            return (Criteria) this;
        }

        public Criteria andElementLogoIsNotNull() {
            addCriterion("element_logo is not null");
            return (Criteria) this;
        }

        public Criteria andElementLogoEqualTo(String value) {
            addCriterion("element_logo =", value, "elementLogo");
            return (Criteria) this;
        }

        public Criteria andElementLogoNotEqualTo(String value) {
            addCriterion("element_logo <>", value, "elementLogo");
            return (Criteria) this;
        }

        public Criteria andElementLogoGreaterThan(String value) {
            addCriterion("element_logo >", value, "elementLogo");
            return (Criteria) this;
        }

        public Criteria andElementLogoGreaterThanOrEqualTo(String value) {
            addCriterion("element_logo >=", value, "elementLogo");
            return (Criteria) this;
        }

        public Criteria andElementLogoLessThan(String value) {
            addCriterion("element_logo <", value, "elementLogo");
            return (Criteria) this;
        }

        public Criteria andElementLogoLessThanOrEqualTo(String value) {
            addCriterion("element_logo <=", value, "elementLogo");
            return (Criteria) this;
        }

        public Criteria andElementLogoLike(String value) {
            addCriterion("element_logo like", value, "elementLogo");
            return (Criteria) this;
        }

        public Criteria andElementLogoNotLike(String value) {
            addCriterion("element_logo not like", value, "elementLogo");
            return (Criteria) this;
        }

        public Criteria andElementLogoIn(List<String> values) {
            addCriterion("element_logo in", values, "elementLogo");
            return (Criteria) this;
        }

        public Criteria andElementLogoNotIn(List<String> values) {
            addCriterion("element_logo not in", values, "elementLogo");
            return (Criteria) this;
        }

        public Criteria andElementLogoBetween(String value1, String value2) {
            addCriterion("element_logo between", value1, value2, "elementLogo");
            return (Criteria) this;
        }

        public Criteria andElementLogoNotBetween(String value1, String value2) {
            addCriterion("element_logo not between", value1, value2, "elementLogo");
            return (Criteria) this;
        }

        public Criteria andElementNameIsNull() {
            addCriterion("element_name is null");
            return (Criteria) this;
        }

        public Criteria andElementNameIsNotNull() {
            addCriterion("element_name is not null");
            return (Criteria) this;
        }

        public Criteria andElementNameEqualTo(String value) {
            addCriterion("element_name =", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameNotEqualTo(String value) {
            addCriterion("element_name <>", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameGreaterThan(String value) {
            addCriterion("element_name >", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameGreaterThanOrEqualTo(String value) {
            addCriterion("element_name >=", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameLessThan(String value) {
            addCriterion("element_name <", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameLessThanOrEqualTo(String value) {
            addCriterion("element_name <=", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameLike(String value) {
            addCriterion("element_name like", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameNotLike(String value) {
            addCriterion("element_name not like", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameIn(List<String> values) {
            addCriterion("element_name in", values, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameNotIn(List<String> values) {
            addCriterion("element_name not in", values, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameBetween(String value1, String value2) {
            addCriterion("element_name between", value1, value2, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameNotBetween(String value1, String value2) {
            addCriterion("element_name not between", value1, value2, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementIndexIsNull() {
            addCriterion("element_index is null");
            return (Criteria) this;
        }

        public Criteria andElementIndexIsNotNull() {
            addCriterion("element_index is not null");
            return (Criteria) this;
        }

        public Criteria andElementIndexEqualTo(Integer value) {
            addCriterion("element_index =", value, "elementIndex");
            return (Criteria) this;
        }

        public Criteria andElementIndexNotEqualTo(Integer value) {
            addCriterion("element_index <>", value, "elementIndex");
            return (Criteria) this;
        }

        public Criteria andElementIndexGreaterThan(Integer value) {
            addCriterion("element_index >", value, "elementIndex");
            return (Criteria) this;
        }

        public Criteria andElementIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("element_index >=", value, "elementIndex");
            return (Criteria) this;
        }

        public Criteria andElementIndexLessThan(Integer value) {
            addCriterion("element_index <", value, "elementIndex");
            return (Criteria) this;
        }

        public Criteria andElementIndexLessThanOrEqualTo(Integer value) {
            addCriterion("element_index <=", value, "elementIndex");
            return (Criteria) this;
        }

        public Criteria andElementIndexIn(List<Integer> values) {
            addCriterion("element_index in", values, "elementIndex");
            return (Criteria) this;
        }

        public Criteria andElementIndexNotIn(List<Integer> values) {
            addCriterion("element_index not in", values, "elementIndex");
            return (Criteria) this;
        }

        public Criteria andElementIndexBetween(Integer value1, Integer value2) {
            addCriterion("element_index between", value1, value2, "elementIndex");
            return (Criteria) this;
        }

        public Criteria andElementIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("element_index not between", value1, value2, "elementIndex");
            return (Criteria) this;
        }

        public Criteria andElementJsonIsNull() {
            addCriterion("element_json is null");
            return (Criteria) this;
        }

        public Criteria andElementJsonIsNotNull() {
            addCriterion("element_json is not null");
            return (Criteria) this;
        }

        public Criteria andElementJsonEqualTo(String value) {
            addCriterion("element_json =", value, "elementJson");
            return (Criteria) this;
        }

        public Criteria andElementJsonNotEqualTo(String value) {
            addCriterion("element_json <>", value, "elementJson");
            return (Criteria) this;
        }

        public Criteria andElementJsonGreaterThan(String value) {
            addCriterion("element_json >", value, "elementJson");
            return (Criteria) this;
        }

        public Criteria andElementJsonGreaterThanOrEqualTo(String value) {
            addCriterion("element_json >=", value, "elementJson");
            return (Criteria) this;
        }

        public Criteria andElementJsonLessThan(String value) {
            addCriterion("element_json <", value, "elementJson");
            return (Criteria) this;
        }

        public Criteria andElementJsonLessThanOrEqualTo(String value) {
            addCriterion("element_json <=", value, "elementJson");
            return (Criteria) this;
        }

        public Criteria andElementJsonLike(String value) {
            addCriterion("element_json like", value, "elementJson");
            return (Criteria) this;
        }

        public Criteria andElementJsonNotLike(String value) {
            addCriterion("element_json not like", value, "elementJson");
            return (Criteria) this;
        }

        public Criteria andElementJsonIn(List<String> values) {
            addCriterion("element_json in", values, "elementJson");
            return (Criteria) this;
        }

        public Criteria andElementJsonNotIn(List<String> values) {
            addCriterion("element_json not in", values, "elementJson");
            return (Criteria) this;
        }

        public Criteria andElementJsonBetween(String value1, String value2) {
            addCriterion("element_json between", value1, value2, "elementJson");
            return (Criteria) this;
        }

        public Criteria andElementJsonNotBetween(String value1, String value2) {
            addCriterion("element_json not between", value1, value2, "elementJson");
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