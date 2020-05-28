package com.applet.common.entity;

import com.applet.common.util.Page;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public OrderDetailsExample() {
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

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Integer value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Integer value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Integer value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Integer value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Integer> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Integer> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
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

        public Criteria andGoodsNameIsNull() {
            addCriterion("goods_name is null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIsNotNull() {
            addCriterion("goods_name is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameEqualTo(String value) {
            addCriterion("goods_name =", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotEqualTo(String value) {
            addCriterion("goods_name <>", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThan(String value) {
            addCriterion("goods_name >", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThanOrEqualTo(String value) {
            addCriterion("goods_name >=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThan(String value) {
            addCriterion("goods_name <", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThanOrEqualTo(String value) {
            addCriterion("goods_name <=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLike(String value) {
            addCriterion("goods_name like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotLike(String value) {
            addCriterion("goods_name not like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIn(List<String> values) {
            addCriterion("goods_name in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotIn(List<String> values) {
            addCriterion("goods_name not in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameBetween(String value1, String value2) {
            addCriterion("goods_name between", value1, value2, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotBetween(String value1, String value2) {
            addCriterion("goods_name not between", value1, value2, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsDiscountIsNull() {
            addCriterion("goods_discount is null");
            return (Criteria) this;
        }

        public Criteria andGoodsDiscountIsNotNull() {
            addCriterion("goods_discount is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsDiscountEqualTo(Integer value) {
            addCriterion("goods_discount =", value, "goodsDiscount");
            return (Criteria) this;
        }

        public Criteria andGoodsDiscountNotEqualTo(Integer value) {
            addCriterion("goods_discount <>", value, "goodsDiscount");
            return (Criteria) this;
        }

        public Criteria andGoodsDiscountGreaterThan(Integer value) {
            addCriterion("goods_discount >", value, "goodsDiscount");
            return (Criteria) this;
        }

        public Criteria andGoodsDiscountGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_discount >=", value, "goodsDiscount");
            return (Criteria) this;
        }

        public Criteria andGoodsDiscountLessThan(Integer value) {
            addCriterion("goods_discount <", value, "goodsDiscount");
            return (Criteria) this;
        }

        public Criteria andGoodsDiscountLessThanOrEqualTo(Integer value) {
            addCriterion("goods_discount <=", value, "goodsDiscount");
            return (Criteria) this;
        }

        public Criteria andGoodsDiscountIn(List<Integer> values) {
            addCriterion("goods_discount in", values, "goodsDiscount");
            return (Criteria) this;
        }

        public Criteria andGoodsDiscountNotIn(List<Integer> values) {
            addCriterion("goods_discount not in", values, "goodsDiscount");
            return (Criteria) this;
        }

        public Criteria andGoodsDiscountBetween(Integer value1, Integer value2) {
            addCriterion("goods_discount between", value1, value2, "goodsDiscount");
            return (Criteria) this;
        }

        public Criteria andGoodsDiscountNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_discount not between", value1, value2, "goodsDiscount");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsIdIsNull() {
            addCriterion("goods_specs_id is null");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsIdIsNotNull() {
            addCriterion("goods_specs_id is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsIdEqualTo(Integer value) {
            addCriterion("goods_specs_id =", value, "goodsSpecsId");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsIdNotEqualTo(Integer value) {
            addCriterion("goods_specs_id <>", value, "goodsSpecsId");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsIdGreaterThan(Integer value) {
            addCriterion("goods_specs_id >", value, "goodsSpecsId");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_specs_id >=", value, "goodsSpecsId");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsIdLessThan(Integer value) {
            addCriterion("goods_specs_id <", value, "goodsSpecsId");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsIdLessThanOrEqualTo(Integer value) {
            addCriterion("goods_specs_id <=", value, "goodsSpecsId");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsIdIn(List<Integer> values) {
            addCriterion("goods_specs_id in", values, "goodsSpecsId");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsIdNotIn(List<Integer> values) {
            addCriterion("goods_specs_id not in", values, "goodsSpecsId");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsIdBetween(Integer value1, Integer value2) {
            addCriterion("goods_specs_id between", value1, value2, "goodsSpecsId");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_specs_id not between", value1, value2, "goodsSpecsId");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsNameIsNull() {
            addCriterion("goods_specs_name is null");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsNameIsNotNull() {
            addCriterion("goods_specs_name is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsNameEqualTo(String value) {
            addCriterion("goods_specs_name =", value, "goodsSpecsName");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsNameNotEqualTo(String value) {
            addCriterion("goods_specs_name <>", value, "goodsSpecsName");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsNameGreaterThan(String value) {
            addCriterion("goods_specs_name >", value, "goodsSpecsName");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsNameGreaterThanOrEqualTo(String value) {
            addCriterion("goods_specs_name >=", value, "goodsSpecsName");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsNameLessThan(String value) {
            addCriterion("goods_specs_name <", value, "goodsSpecsName");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsNameLessThanOrEqualTo(String value) {
            addCriterion("goods_specs_name <=", value, "goodsSpecsName");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsNameLike(String value) {
            addCriterion("goods_specs_name like", value, "goodsSpecsName");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsNameNotLike(String value) {
            addCriterion("goods_specs_name not like", value, "goodsSpecsName");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsNameIn(List<String> values) {
            addCriterion("goods_specs_name in", values, "goodsSpecsName");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsNameNotIn(List<String> values) {
            addCriterion("goods_specs_name not in", values, "goodsSpecsName");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsNameBetween(String value1, String value2) {
            addCriterion("goods_specs_name between", value1, value2, "goodsSpecsName");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsNameNotBetween(String value1, String value2) {
            addCriterion("goods_specs_name not between", value1, value2, "goodsSpecsName");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsTypeIsNull() {
            addCriterion("goods_specs_type is null");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsTypeIsNotNull() {
            addCriterion("goods_specs_type is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsTypeEqualTo(String value) {
            addCriterion("goods_specs_type =", value, "goodsSpecsType");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsTypeNotEqualTo(String value) {
            addCriterion("goods_specs_type <>", value, "goodsSpecsType");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsTypeGreaterThan(String value) {
            addCriterion("goods_specs_type >", value, "goodsSpecsType");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("goods_specs_type >=", value, "goodsSpecsType");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsTypeLessThan(String value) {
            addCriterion("goods_specs_type <", value, "goodsSpecsType");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsTypeLessThanOrEqualTo(String value) {
            addCriterion("goods_specs_type <=", value, "goodsSpecsType");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsTypeLike(String value) {
            addCriterion("goods_specs_type like", value, "goodsSpecsType");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsTypeNotLike(String value) {
            addCriterion("goods_specs_type not like", value, "goodsSpecsType");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsTypeIn(List<String> values) {
            addCriterion("goods_specs_type in", values, "goodsSpecsType");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsTypeNotIn(List<String> values) {
            addCriterion("goods_specs_type not in", values, "goodsSpecsType");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsTypeBetween(String value1, String value2) {
            addCriterion("goods_specs_type between", value1, value2, "goodsSpecsType");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsTypeNotBetween(String value1, String value2) {
            addCriterion("goods_specs_type not between", value1, value2, "goodsSpecsType");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsPicIsNull() {
            addCriterion("goods_specs_pic is null");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsPicIsNotNull() {
            addCriterion("goods_specs_pic is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsPicEqualTo(String value) {
            addCriterion("goods_specs_pic =", value, "goodsSpecsPic");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsPicNotEqualTo(String value) {
            addCriterion("goods_specs_pic <>", value, "goodsSpecsPic");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsPicGreaterThan(String value) {
            addCriterion("goods_specs_pic >", value, "goodsSpecsPic");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsPicGreaterThanOrEqualTo(String value) {
            addCriterion("goods_specs_pic >=", value, "goodsSpecsPic");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsPicLessThan(String value) {
            addCriterion("goods_specs_pic <", value, "goodsSpecsPic");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsPicLessThanOrEqualTo(String value) {
            addCriterion("goods_specs_pic <=", value, "goodsSpecsPic");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsPicLike(String value) {
            addCriterion("goods_specs_pic like", value, "goodsSpecsPic");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsPicNotLike(String value) {
            addCriterion("goods_specs_pic not like", value, "goodsSpecsPic");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsPicIn(List<String> values) {
            addCriterion("goods_specs_pic in", values, "goodsSpecsPic");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsPicNotIn(List<String> values) {
            addCriterion("goods_specs_pic not in", values, "goodsSpecsPic");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsPicBetween(String value1, String value2) {
            addCriterion("goods_specs_pic between", value1, value2, "goodsSpecsPic");
            return (Criteria) this;
        }

        public Criteria andGoodsSpecsPicNotBetween(String value1, String value2) {
            addCriterion("goods_specs_pic not between", value1, value2, "goodsSpecsPic");
            return (Criteria) this;
        }

        public Criteria andGoodsNumberIsNull() {
            addCriterion("goods_number is null");
            return (Criteria) this;
        }

        public Criteria andGoodsNumberIsNotNull() {
            addCriterion("goods_number is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsNumberEqualTo(Integer value) {
            addCriterion("goods_number =", value, "goodsNumber");
            return (Criteria) this;
        }

        public Criteria andGoodsNumberNotEqualTo(Integer value) {
            addCriterion("goods_number <>", value, "goodsNumber");
            return (Criteria) this;
        }

        public Criteria andGoodsNumberGreaterThan(Integer value) {
            addCriterion("goods_number >", value, "goodsNumber");
            return (Criteria) this;
        }

        public Criteria andGoodsNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_number >=", value, "goodsNumber");
            return (Criteria) this;
        }

        public Criteria andGoodsNumberLessThan(Integer value) {
            addCriterion("goods_number <", value, "goodsNumber");
            return (Criteria) this;
        }

        public Criteria andGoodsNumberLessThanOrEqualTo(Integer value) {
            addCriterion("goods_number <=", value, "goodsNumber");
            return (Criteria) this;
        }

        public Criteria andGoodsNumberIn(List<Integer> values) {
            addCriterion("goods_number in", values, "goodsNumber");
            return (Criteria) this;
        }

        public Criteria andGoodsNumberNotIn(List<Integer> values) {
            addCriterion("goods_number not in", values, "goodsNumber");
            return (Criteria) this;
        }

        public Criteria andGoodsNumberBetween(Integer value1, Integer value2) {
            addCriterion("goods_number between", value1, value2, "goodsNumber");
            return (Criteria) this;
        }

        public Criteria andGoodsNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_number not between", value1, value2, "goodsNumber");
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

        public Criteria andActualPriceIsNull() {
            addCriterion("actual_price is null");
            return (Criteria) this;
        }

        public Criteria andActualPriceIsNotNull() {
            addCriterion("actual_price is not null");
            return (Criteria) this;
        }

        public Criteria andActualPriceEqualTo(Double value) {
            addCriterion("actual_price =", value, "actualPrice");
            return (Criteria) this;
        }

        public Criteria andActualPriceNotEqualTo(Double value) {
            addCriterion("actual_price <>", value, "actualPrice");
            return (Criteria) this;
        }

        public Criteria andActualPriceGreaterThan(Double value) {
            addCriterion("actual_price >", value, "actualPrice");
            return (Criteria) this;
        }

        public Criteria andActualPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("actual_price >=", value, "actualPrice");
            return (Criteria) this;
        }

        public Criteria andActualPriceLessThan(Double value) {
            addCriterion("actual_price <", value, "actualPrice");
            return (Criteria) this;
        }

        public Criteria andActualPriceLessThanOrEqualTo(Double value) {
            addCriterion("actual_price <=", value, "actualPrice");
            return (Criteria) this;
        }

        public Criteria andActualPriceIn(List<Double> values) {
            addCriterion("actual_price in", values, "actualPrice");
            return (Criteria) this;
        }

        public Criteria andActualPriceNotIn(List<Double> values) {
            addCriterion("actual_price not in", values, "actualPrice");
            return (Criteria) this;
        }

        public Criteria andActualPriceBetween(Double value1, Double value2) {
            addCriterion("actual_price between", value1, value2, "actualPrice");
            return (Criteria) this;
        }

        public Criteria andActualPriceNotBetween(Double value1, Double value2) {
            addCriterion("actual_price not between", value1, value2, "actualPrice");
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