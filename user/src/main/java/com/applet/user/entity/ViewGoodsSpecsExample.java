package com.applet.user.entity;

import com.applet.common.util.Page;
import java.util.ArrayList;
import java.util.List;

public class ViewGoodsSpecsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public ViewGoodsSpecsExample() {
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

        public Criteria andGoodsIdIsNull() {
            addCriterion("goods_id is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNotNull() {
            addCriterion("goods_id is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdEqualTo(Integer value) {
            addCriterion("goods_id =", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotEqualTo(Integer value) {
            addCriterion("goods_id <>", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThan(Integer value) {
            addCriterion("goods_id >", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_id >=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThan(Integer value) {
            addCriterion("goods_id <", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThanOrEqualTo(Integer value) {
            addCriterion("goods_id <=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIn(List<Integer> values) {
            addCriterion("goods_id in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotIn(List<Integer> values) {
            addCriterion("goods_id not in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdBetween(Integer value1, Integer value2) {
            addCriterion("goods_id between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_id not between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andSpecsSrcIsNull() {
            addCriterion("specs_src is null");
            return (Criteria) this;
        }

        public Criteria andSpecsSrcIsNotNull() {
            addCriterion("specs_src is not null");
            return (Criteria) this;
        }

        public Criteria andSpecsSrcEqualTo(String value) {
            addCriterion("specs_src =", value, "specsSrc");
            return (Criteria) this;
        }

        public Criteria andSpecsSrcNotEqualTo(String value) {
            addCriterion("specs_src <>", value, "specsSrc");
            return (Criteria) this;
        }

        public Criteria andSpecsSrcGreaterThan(String value) {
            addCriterion("specs_src >", value, "specsSrc");
            return (Criteria) this;
        }

        public Criteria andSpecsSrcGreaterThanOrEqualTo(String value) {
            addCriterion("specs_src >=", value, "specsSrc");
            return (Criteria) this;
        }

        public Criteria andSpecsSrcLessThan(String value) {
            addCriterion("specs_src <", value, "specsSrc");
            return (Criteria) this;
        }

        public Criteria andSpecsSrcLessThanOrEqualTo(String value) {
            addCriterion("specs_src <=", value, "specsSrc");
            return (Criteria) this;
        }

        public Criteria andSpecsSrcLike(String value) {
            addCriterion("specs_src like", value, "specsSrc");
            return (Criteria) this;
        }

        public Criteria andSpecsSrcNotLike(String value) {
            addCriterion("specs_src not like", value, "specsSrc");
            return (Criteria) this;
        }

        public Criteria andSpecsSrcIn(List<String> values) {
            addCriterion("specs_src in", values, "specsSrc");
            return (Criteria) this;
        }

        public Criteria andSpecsSrcNotIn(List<String> values) {
            addCriterion("specs_src not in", values, "specsSrc");
            return (Criteria) this;
        }

        public Criteria andSpecsSrcBetween(String value1, String value2) {
            addCriterion("specs_src between", value1, value2, "specsSrc");
            return (Criteria) this;
        }

        public Criteria andSpecsSrcNotBetween(String value1, String value2) {
            addCriterion("specs_src not between", value1, value2, "specsSrc");
            return (Criteria) this;
        }

        public Criteria andSpecsTextIsNull() {
            addCriterion("specs_text is null");
            return (Criteria) this;
        }

        public Criteria andSpecsTextIsNotNull() {
            addCriterion("specs_text is not null");
            return (Criteria) this;
        }

        public Criteria andSpecsTextEqualTo(String value) {
            addCriterion("specs_text =", value, "specsText");
            return (Criteria) this;
        }

        public Criteria andSpecsTextNotEqualTo(String value) {
            addCriterion("specs_text <>", value, "specsText");
            return (Criteria) this;
        }

        public Criteria andSpecsTextGreaterThan(String value) {
            addCriterion("specs_text >", value, "specsText");
            return (Criteria) this;
        }

        public Criteria andSpecsTextGreaterThanOrEqualTo(String value) {
            addCriterion("specs_text >=", value, "specsText");
            return (Criteria) this;
        }

        public Criteria andSpecsTextLessThan(String value) {
            addCriterion("specs_text <", value, "specsText");
            return (Criteria) this;
        }

        public Criteria andSpecsTextLessThanOrEqualTo(String value) {
            addCriterion("specs_text <=", value, "specsText");
            return (Criteria) this;
        }

        public Criteria andSpecsTextLike(String value) {
            addCriterion("specs_text like", value, "specsText");
            return (Criteria) this;
        }

        public Criteria andSpecsTextNotLike(String value) {
            addCriterion("specs_text not like", value, "specsText");
            return (Criteria) this;
        }

        public Criteria andSpecsTextIn(List<String> values) {
            addCriterion("specs_text in", values, "specsText");
            return (Criteria) this;
        }

        public Criteria andSpecsTextNotIn(List<String> values) {
            addCriterion("specs_text not in", values, "specsText");
            return (Criteria) this;
        }

        public Criteria andSpecsTextBetween(String value1, String value2) {
            addCriterion("specs_text between", value1, value2, "specsText");
            return (Criteria) this;
        }

        public Criteria andSpecsTextNotBetween(String value1, String value2) {
            addCriterion("specs_text not between", value1, value2, "specsText");
            return (Criteria) this;
        }

        public Criteria andSellPriceIsNull() {
            addCriterion("sell_price is null");
            return (Criteria) this;
        }

        public Criteria andSellPriceIsNotNull() {
            addCriterion("sell_price is not null");
            return (Criteria) this;
        }

        public Criteria andSellPriceEqualTo(Double value) {
            addCriterion("sell_price =", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceNotEqualTo(Double value) {
            addCriterion("sell_price <>", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceGreaterThan(Double value) {
            addCriterion("sell_price >", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("sell_price >=", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceLessThan(Double value) {
            addCriterion("sell_price <", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceLessThanOrEqualTo(Double value) {
            addCriterion("sell_price <=", value, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceIn(List<Double> values) {
            addCriterion("sell_price in", values, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceNotIn(List<Double> values) {
            addCriterion("sell_price not in", values, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceBetween(Double value1, Double value2) {
            addCriterion("sell_price between", value1, value2, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andSellPriceNotBetween(Double value1, Double value2) {
            addCriterion("sell_price not between", value1, value2, "sellPrice");
            return (Criteria) this;
        }

        public Criteria andDescribeStrIsNull() {
            addCriterion("describe_str is null");
            return (Criteria) this;
        }

        public Criteria andDescribeStrIsNotNull() {
            addCriterion("describe_str is not null");
            return (Criteria) this;
        }

        public Criteria andDescribeStrEqualTo(String value) {
            addCriterion("describe_str =", value, "describeStr");
            return (Criteria) this;
        }

        public Criteria andDescribeStrNotEqualTo(String value) {
            addCriterion("describe_str <>", value, "describeStr");
            return (Criteria) this;
        }

        public Criteria andDescribeStrGreaterThan(String value) {
            addCriterion("describe_str >", value, "describeStr");
            return (Criteria) this;
        }

        public Criteria andDescribeStrGreaterThanOrEqualTo(String value) {
            addCriterion("describe_str >=", value, "describeStr");
            return (Criteria) this;
        }

        public Criteria andDescribeStrLessThan(String value) {
            addCriterion("describe_str <", value, "describeStr");
            return (Criteria) this;
        }

        public Criteria andDescribeStrLessThanOrEqualTo(String value) {
            addCriterion("describe_str <=", value, "describeStr");
            return (Criteria) this;
        }

        public Criteria andDescribeStrLike(String value) {
            addCriterion("describe_str like", value, "describeStr");
            return (Criteria) this;
        }

        public Criteria andDescribeStrNotLike(String value) {
            addCriterion("describe_str not like", value, "describeStr");
            return (Criteria) this;
        }

        public Criteria andDescribeStrIn(List<String> values) {
            addCriterion("describe_str in", values, "describeStr");
            return (Criteria) this;
        }

        public Criteria andDescribeStrNotIn(List<String> values) {
            addCriterion("describe_str not in", values, "describeStr");
            return (Criteria) this;
        }

        public Criteria andDescribeStrBetween(String value1, String value2) {
            addCriterion("describe_str between", value1, value2, "describeStr");
            return (Criteria) this;
        }

        public Criteria andDescribeStrNotBetween(String value1, String value2) {
            addCriterion("describe_str not between", value1, value2, "describeStr");
            return (Criteria) this;
        }

        public Criteria andSpecsIndexIsNull() {
            addCriterion("specs_index is null");
            return (Criteria) this;
        }

        public Criteria andSpecsIndexIsNotNull() {
            addCriterion("specs_index is not null");
            return (Criteria) this;
        }

        public Criteria andSpecsIndexEqualTo(Integer value) {
            addCriterion("specs_index =", value, "specsIndex");
            return (Criteria) this;
        }

        public Criteria andSpecsIndexNotEqualTo(Integer value) {
            addCriterion("specs_index <>", value, "specsIndex");
            return (Criteria) this;
        }

        public Criteria andSpecsIndexGreaterThan(Integer value) {
            addCriterion("specs_index >", value, "specsIndex");
            return (Criteria) this;
        }

        public Criteria andSpecsIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("specs_index >=", value, "specsIndex");
            return (Criteria) this;
        }

        public Criteria andSpecsIndexLessThan(Integer value) {
            addCriterion("specs_index <", value, "specsIndex");
            return (Criteria) this;
        }

        public Criteria andSpecsIndexLessThanOrEqualTo(Integer value) {
            addCriterion("specs_index <=", value, "specsIndex");
            return (Criteria) this;
        }

        public Criteria andSpecsIndexIn(List<Integer> values) {
            addCriterion("specs_index in", values, "specsIndex");
            return (Criteria) this;
        }

        public Criteria andSpecsIndexNotIn(List<Integer> values) {
            addCriterion("specs_index not in", values, "specsIndex");
            return (Criteria) this;
        }

        public Criteria andSpecsIndexBetween(Integer value1, Integer value2) {
            addCriterion("specs_index between", value1, value2, "specsIndex");
            return (Criteria) this;
        }

        public Criteria andSpecsIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("specs_index not between", value1, value2, "specsIndex");
            return (Criteria) this;
        }

        public Criteria andSpecsStatusIsNull() {
            addCriterion("specs_status is null");
            return (Criteria) this;
        }

        public Criteria andSpecsStatusIsNotNull() {
            addCriterion("specs_status is not null");
            return (Criteria) this;
        }

        public Criteria andSpecsStatusEqualTo(Boolean value) {
            addCriterion("specs_status =", value, "specsStatus");
            return (Criteria) this;
        }

        public Criteria andSpecsStatusNotEqualTo(Boolean value) {
            addCriterion("specs_status <>", value, "specsStatus");
            return (Criteria) this;
        }

        public Criteria andSpecsStatusGreaterThan(Boolean value) {
            addCriterion("specs_status >", value, "specsStatus");
            return (Criteria) this;
        }

        public Criteria andSpecsStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("specs_status >=", value, "specsStatus");
            return (Criteria) this;
        }

        public Criteria andSpecsStatusLessThan(Boolean value) {
            addCriterion("specs_status <", value, "specsStatus");
            return (Criteria) this;
        }

        public Criteria andSpecsStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("specs_status <=", value, "specsStatus");
            return (Criteria) this;
        }

        public Criteria andSpecsStatusIn(List<Boolean> values) {
            addCriterion("specs_status in", values, "specsStatus");
            return (Criteria) this;
        }

        public Criteria andSpecsStatusNotIn(List<Boolean> values) {
            addCriterion("specs_status not in", values, "specsStatus");
            return (Criteria) this;
        }

        public Criteria andSpecsStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("specs_status between", value1, value2, "specsStatus");
            return (Criteria) this;
        }

        public Criteria andSpecsStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("specs_status not between", value1, value2, "specsStatus");
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