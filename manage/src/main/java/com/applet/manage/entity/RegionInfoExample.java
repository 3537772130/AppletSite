package com.applet.manage.entity;

import com.applet.manage.util.Page;

import java.util.ArrayList;
import java.util.List;

public class RegionInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public RegionInfoExample() {
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
        this.page = page;
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

        public Criteria andAreaNameIsNull() {
            addCriterion("area_name is null");
            return (Criteria) this;
        }

        public Criteria andAreaNameIsNotNull() {
            addCriterion("area_name is not null");
            return (Criteria) this;
        }

        public Criteria andAreaNameEqualTo(String value) {
            addCriterion("area_name =", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotEqualTo(String value) {
            addCriterion("area_name <>", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameGreaterThan(String value) {
            addCriterion("area_name >", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameGreaterThanOrEqualTo(String value) {
            addCriterion("area_name >=", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLessThan(String value) {
            addCriterion("area_name <", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLessThanOrEqualTo(String value) {
            addCriterion("area_name <=", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLike(String value) {
            addCriterion("area_name like", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotLike(String value) {
            addCriterion("area_name not like", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameIn(List<String> values) {
            addCriterion("area_name in", values, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotIn(List<String> values) {
            addCriterion("area_name not in", values, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameBetween(String value1, String value2) {
            addCriterion("area_name between", value1, value2, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotBetween(String value1, String value2) {
            addCriterion("area_name not between", value1, value2, "areaName");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Integer value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Integer value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Integer value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Integer value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Integer> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Integer> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Integer value1, Integer value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andShortNameIsNull() {
            addCriterion("short_name is null");
            return (Criteria) this;
        }

        public Criteria andShortNameIsNotNull() {
            addCriterion("short_name is not null");
            return (Criteria) this;
        }

        public Criteria andShortNameEqualTo(String value) {
            addCriterion("short_name =", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameNotEqualTo(String value) {
            addCriterion("short_name <>", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameGreaterThan(String value) {
            addCriterion("short_name >", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameGreaterThanOrEqualTo(String value) {
            addCriterion("short_name >=", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameLessThan(String value) {
            addCriterion("short_name <", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameLessThanOrEqualTo(String value) {
            addCriterion("short_name <=", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameLike(String value) {
            addCriterion("short_name like", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameNotLike(String value) {
            addCriterion("short_name not like", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameIn(List<String> values) {
            addCriterion("short_name in", values, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameNotIn(List<String> values) {
            addCriterion("short_name not in", values, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameBetween(String value1, String value2) {
            addCriterion("short_name between", value1, value2, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameNotBetween(String value1, String value2) {
            addCriterion("short_name not between", value1, value2, "shortName");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("flag is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("flag is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(Integer value) {
            addCriterion("flag =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(Integer value) {
            addCriterion("flag <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(Integer value) {
            addCriterion("flag >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("flag >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(Integer value) {
            addCriterion("flag <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(Integer value) {
            addCriterion("flag <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<Integer> values) {
            addCriterion("flag in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<Integer> values) {
            addCriterion("flag not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(Integer value1, Integer value2) {
            addCriterion("flag between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("flag not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andWgs84LngIsNull() {
            addCriterion("WGS84_LNG is null");
            return (Criteria) this;
        }

        public Criteria andWgs84LngIsNotNull() {
            addCriterion("WGS84_LNG is not null");
            return (Criteria) this;
        }

        public Criteria andWgs84LngEqualTo(Double value) {
            addCriterion("WGS84_LNG =", value, "wgs84Lng");
            return (Criteria) this;
        }

        public Criteria andWgs84LngNotEqualTo(Double value) {
            addCriterion("WGS84_LNG <>", value, "wgs84Lng");
            return (Criteria) this;
        }

        public Criteria andWgs84LngGreaterThan(Double value) {
            addCriterion("WGS84_LNG >", value, "wgs84Lng");
            return (Criteria) this;
        }

        public Criteria andWgs84LngGreaterThanOrEqualTo(Double value) {
            addCriterion("WGS84_LNG >=", value, "wgs84Lng");
            return (Criteria) this;
        }

        public Criteria andWgs84LngLessThan(Double value) {
            addCriterion("WGS84_LNG <", value, "wgs84Lng");
            return (Criteria) this;
        }

        public Criteria andWgs84LngLessThanOrEqualTo(Double value) {
            addCriterion("WGS84_LNG <=", value, "wgs84Lng");
            return (Criteria) this;
        }

        public Criteria andWgs84LngIn(List<Double> values) {
            addCriterion("WGS84_LNG in", values, "wgs84Lng");
            return (Criteria) this;
        }

        public Criteria andWgs84LngNotIn(List<Double> values) {
            addCriterion("WGS84_LNG not in", values, "wgs84Lng");
            return (Criteria) this;
        }

        public Criteria andWgs84LngBetween(Double value1, Double value2) {
            addCriterion("WGS84_LNG between", value1, value2, "wgs84Lng");
            return (Criteria) this;
        }

        public Criteria andWgs84LngNotBetween(Double value1, Double value2) {
            addCriterion("WGS84_LNG not between", value1, value2, "wgs84Lng");
            return (Criteria) this;
        }

        public Criteria andWgs84LatIsNull() {
            addCriterion("WGS84_LAT is null");
            return (Criteria) this;
        }

        public Criteria andWgs84LatIsNotNull() {
            addCriterion("WGS84_LAT is not null");
            return (Criteria) this;
        }

        public Criteria andWgs84LatEqualTo(Double value) {
            addCriterion("WGS84_LAT =", value, "wgs84Lat");
            return (Criteria) this;
        }

        public Criteria andWgs84LatNotEqualTo(Double value) {
            addCriterion("WGS84_LAT <>", value, "wgs84Lat");
            return (Criteria) this;
        }

        public Criteria andWgs84LatGreaterThan(Double value) {
            addCriterion("WGS84_LAT >", value, "wgs84Lat");
            return (Criteria) this;
        }

        public Criteria andWgs84LatGreaterThanOrEqualTo(Double value) {
            addCriterion("WGS84_LAT >=", value, "wgs84Lat");
            return (Criteria) this;
        }

        public Criteria andWgs84LatLessThan(Double value) {
            addCriterion("WGS84_LAT <", value, "wgs84Lat");
            return (Criteria) this;
        }

        public Criteria andWgs84LatLessThanOrEqualTo(Double value) {
            addCriterion("WGS84_LAT <=", value, "wgs84Lat");
            return (Criteria) this;
        }

        public Criteria andWgs84LatIn(List<Double> values) {
            addCriterion("WGS84_LAT in", values, "wgs84Lat");
            return (Criteria) this;
        }

        public Criteria andWgs84LatNotIn(List<Double> values) {
            addCriterion("WGS84_LAT not in", values, "wgs84Lat");
            return (Criteria) this;
        }

        public Criteria andWgs84LatBetween(Double value1, Double value2) {
            addCriterion("WGS84_LAT between", value1, value2, "wgs84Lat");
            return (Criteria) this;
        }

        public Criteria andWgs84LatNotBetween(Double value1, Double value2) {
            addCriterion("WGS84_LAT not between", value1, value2, "wgs84Lat");
            return (Criteria) this;
        }

        public Criteria andGcj02LngIsNull() {
            addCriterion("GCJ02_LNG is null");
            return (Criteria) this;
        }

        public Criteria andGcj02LngIsNotNull() {
            addCriterion("GCJ02_LNG is not null");
            return (Criteria) this;
        }

        public Criteria andGcj02LngEqualTo(Double value) {
            addCriterion("GCJ02_LNG =", value, "gcj02Lng");
            return (Criteria) this;
        }

        public Criteria andGcj02LngNotEqualTo(Double value) {
            addCriterion("GCJ02_LNG <>", value, "gcj02Lng");
            return (Criteria) this;
        }

        public Criteria andGcj02LngGreaterThan(Double value) {
            addCriterion("GCJ02_LNG >", value, "gcj02Lng");
            return (Criteria) this;
        }

        public Criteria andGcj02LngGreaterThanOrEqualTo(Double value) {
            addCriterion("GCJ02_LNG >=", value, "gcj02Lng");
            return (Criteria) this;
        }

        public Criteria andGcj02LngLessThan(Double value) {
            addCriterion("GCJ02_LNG <", value, "gcj02Lng");
            return (Criteria) this;
        }

        public Criteria andGcj02LngLessThanOrEqualTo(Double value) {
            addCriterion("GCJ02_LNG <=", value, "gcj02Lng");
            return (Criteria) this;
        }

        public Criteria andGcj02LngIn(List<Double> values) {
            addCriterion("GCJ02_LNG in", values, "gcj02Lng");
            return (Criteria) this;
        }

        public Criteria andGcj02LngNotIn(List<Double> values) {
            addCriterion("GCJ02_LNG not in", values, "gcj02Lng");
            return (Criteria) this;
        }

        public Criteria andGcj02LngBetween(Double value1, Double value2) {
            addCriterion("GCJ02_LNG between", value1, value2, "gcj02Lng");
            return (Criteria) this;
        }

        public Criteria andGcj02LngNotBetween(Double value1, Double value2) {
            addCriterion("GCJ02_LNG not between", value1, value2, "gcj02Lng");
            return (Criteria) this;
        }

        public Criteria andGcj02LatIsNull() {
            addCriterion("GCJ02_LAT is null");
            return (Criteria) this;
        }

        public Criteria andGcj02LatIsNotNull() {
            addCriterion("GCJ02_LAT is not null");
            return (Criteria) this;
        }

        public Criteria andGcj02LatEqualTo(Double value) {
            addCriterion("GCJ02_LAT =", value, "gcj02Lat");
            return (Criteria) this;
        }

        public Criteria andGcj02LatNotEqualTo(Double value) {
            addCriterion("GCJ02_LAT <>", value, "gcj02Lat");
            return (Criteria) this;
        }

        public Criteria andGcj02LatGreaterThan(Double value) {
            addCriterion("GCJ02_LAT >", value, "gcj02Lat");
            return (Criteria) this;
        }

        public Criteria andGcj02LatGreaterThanOrEqualTo(Double value) {
            addCriterion("GCJ02_LAT >=", value, "gcj02Lat");
            return (Criteria) this;
        }

        public Criteria andGcj02LatLessThan(Double value) {
            addCriterion("GCJ02_LAT <", value, "gcj02Lat");
            return (Criteria) this;
        }

        public Criteria andGcj02LatLessThanOrEqualTo(Double value) {
            addCriterion("GCJ02_LAT <=", value, "gcj02Lat");
            return (Criteria) this;
        }

        public Criteria andGcj02LatIn(List<Double> values) {
            addCriterion("GCJ02_LAT in", values, "gcj02Lat");
            return (Criteria) this;
        }

        public Criteria andGcj02LatNotIn(List<Double> values) {
            addCriterion("GCJ02_LAT not in", values, "gcj02Lat");
            return (Criteria) this;
        }

        public Criteria andGcj02LatBetween(Double value1, Double value2) {
            addCriterion("GCJ02_LAT between", value1, value2, "gcj02Lat");
            return (Criteria) this;
        }

        public Criteria andGcj02LatNotBetween(Double value1, Double value2) {
            addCriterion("GCJ02_LAT not between", value1, value2, "gcj02Lat");
            return (Criteria) this;
        }

        public Criteria andBd09LngIsNull() {
            addCriterion("BD09_LNG is null");
            return (Criteria) this;
        }

        public Criteria andBd09LngIsNotNull() {
            addCriterion("BD09_LNG is not null");
            return (Criteria) this;
        }

        public Criteria andBd09LngEqualTo(Double value) {
            addCriterion("BD09_LNG =", value, "bd09Lng");
            return (Criteria) this;
        }

        public Criteria andBd09LngNotEqualTo(Double value) {
            addCriterion("BD09_LNG <>", value, "bd09Lng");
            return (Criteria) this;
        }

        public Criteria andBd09LngGreaterThan(Double value) {
            addCriterion("BD09_LNG >", value, "bd09Lng");
            return (Criteria) this;
        }

        public Criteria andBd09LngGreaterThanOrEqualTo(Double value) {
            addCriterion("BD09_LNG >=", value, "bd09Lng");
            return (Criteria) this;
        }

        public Criteria andBd09LngLessThan(Double value) {
            addCriterion("BD09_LNG <", value, "bd09Lng");
            return (Criteria) this;
        }

        public Criteria andBd09LngLessThanOrEqualTo(Double value) {
            addCriterion("BD09_LNG <=", value, "bd09Lng");
            return (Criteria) this;
        }

        public Criteria andBd09LngIn(List<Double> values) {
            addCriterion("BD09_LNG in", values, "bd09Lng");
            return (Criteria) this;
        }

        public Criteria andBd09LngNotIn(List<Double> values) {
            addCriterion("BD09_LNG not in", values, "bd09Lng");
            return (Criteria) this;
        }

        public Criteria andBd09LngBetween(Double value1, Double value2) {
            addCriterion("BD09_LNG between", value1, value2, "bd09Lng");
            return (Criteria) this;
        }

        public Criteria andBd09LngNotBetween(Double value1, Double value2) {
            addCriterion("BD09_LNG not between", value1, value2, "bd09Lng");
            return (Criteria) this;
        }

        public Criteria andBd09LatIsNull() {
            addCriterion("BD09_LAT is null");
            return (Criteria) this;
        }

        public Criteria andBd09LatIsNotNull() {
            addCriterion("BD09_LAT is not null");
            return (Criteria) this;
        }

        public Criteria andBd09LatEqualTo(Double value) {
            addCriterion("BD09_LAT =", value, "bd09Lat");
            return (Criteria) this;
        }

        public Criteria andBd09LatNotEqualTo(Double value) {
            addCriterion("BD09_LAT <>", value, "bd09Lat");
            return (Criteria) this;
        }

        public Criteria andBd09LatGreaterThan(Double value) {
            addCriterion("BD09_LAT >", value, "bd09Lat");
            return (Criteria) this;
        }

        public Criteria andBd09LatGreaterThanOrEqualTo(Double value) {
            addCriterion("BD09_LAT >=", value, "bd09Lat");
            return (Criteria) this;
        }

        public Criteria andBd09LatLessThan(Double value) {
            addCriterion("BD09_LAT <", value, "bd09Lat");
            return (Criteria) this;
        }

        public Criteria andBd09LatLessThanOrEqualTo(Double value) {
            addCriterion("BD09_LAT <=", value, "bd09Lat");
            return (Criteria) this;
        }

        public Criteria andBd09LatIn(List<Double> values) {
            addCriterion("BD09_LAT in", values, "bd09Lat");
            return (Criteria) this;
        }

        public Criteria andBd09LatNotIn(List<Double> values) {
            addCriterion("BD09_LAT not in", values, "bd09Lat");
            return (Criteria) this;
        }

        public Criteria andBd09LatBetween(Double value1, Double value2) {
            addCriterion("BD09_LAT between", value1, value2, "bd09Lat");
            return (Criteria) this;
        }

        public Criteria andBd09LatNotBetween(Double value1, Double value2) {
            addCriterion("BD09_LAT not between", value1, value2, "bd09Lat");
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
