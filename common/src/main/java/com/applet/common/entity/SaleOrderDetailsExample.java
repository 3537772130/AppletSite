package com.applet.common.entity;

import com.applet.common.util.Page;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaleOrderDetailsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public SaleOrderDetailsExample() {
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

        public Criteria andOrderDtlIdIsNull() {
            addCriterion("order_dtl_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdIsNotNull() {
            addCriterion("order_dtl_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdEqualTo(Integer value) {
            addCriterion("order_dtl_id =", value, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdNotEqualTo(Integer value) {
            addCriterion("order_dtl_id <>", value, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdGreaterThan(Integer value) {
            addCriterion("order_dtl_id >", value, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_dtl_id >=", value, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdLessThan(Integer value) {
            addCriterion("order_dtl_id <", value, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_dtl_id <=", value, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdIn(List<Integer> values) {
            addCriterion("order_dtl_id in", values, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdNotIn(List<Integer> values) {
            addCriterion("order_dtl_id not in", values, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdBetween(Integer value1, Integer value2) {
            addCriterion("order_dtl_id between", value1, value2, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andOrderDtlIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_dtl_id not between", value1, value2, "orderDtlId");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedIsNull() {
            addCriterion("gmt_created is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedIsNotNull() {
            addCriterion("gmt_created is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedEqualTo(Date value) {
            addCriterion("gmt_created =", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedNotEqualTo(Date value) {
            addCriterion("gmt_created <>", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedGreaterThan(Date value) {
            addCriterion("gmt_created >", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_created >=", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedLessThan(Date value) {
            addCriterion("gmt_created <", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_created <=", value, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedIn(List<Date> values) {
            addCriterion("gmt_created in", values, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedNotIn(List<Date> values) {
            addCriterion("gmt_created not in", values, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedBetween(Date value1, Date value2) {
            addCriterion("gmt_created between", value1, value2, "gmtCreated");
            return (Criteria) this;
        }

        public Criteria andGmtCreatedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_created not between", value1, value2, "gmtCreated");
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

        public Criteria andSaleQtyIsNull() {
            addCriterion("sale_qty is null");
            return (Criteria) this;
        }

        public Criteria andSaleQtyIsNotNull() {
            addCriterion("sale_qty is not null");
            return (Criteria) this;
        }

        public Criteria andSaleQtyEqualTo(Integer value) {
            addCriterion("sale_qty =", value, "saleQty");
            return (Criteria) this;
        }

        public Criteria andSaleQtyNotEqualTo(Integer value) {
            addCriterion("sale_qty <>", value, "saleQty");
            return (Criteria) this;
        }

        public Criteria andSaleQtyGreaterThan(Integer value) {
            addCriterion("sale_qty >", value, "saleQty");
            return (Criteria) this;
        }

        public Criteria andSaleQtyGreaterThanOrEqualTo(Integer value) {
            addCriterion("sale_qty >=", value, "saleQty");
            return (Criteria) this;
        }

        public Criteria andSaleQtyLessThan(Integer value) {
            addCriterion("sale_qty <", value, "saleQty");
            return (Criteria) this;
        }

        public Criteria andSaleQtyLessThanOrEqualTo(Integer value) {
            addCriterion("sale_qty <=", value, "saleQty");
            return (Criteria) this;
        }

        public Criteria andSaleQtyIn(List<Integer> values) {
            addCriterion("sale_qty in", values, "saleQty");
            return (Criteria) this;
        }

        public Criteria andSaleQtyNotIn(List<Integer> values) {
            addCriterion("sale_qty not in", values, "saleQty");
            return (Criteria) this;
        }

        public Criteria andSaleQtyBetween(Integer value1, Integer value2) {
            addCriterion("sale_qty between", value1, value2, "saleQty");
            return (Criteria) this;
        }

        public Criteria andSaleQtyNotBetween(Integer value1, Integer value2) {
            addCriterion("sale_qty not between", value1, value2, "saleQty");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceIsNull() {
            addCriterion("discount_price is null");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceIsNotNull() {
            addCriterion("discount_price is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceEqualTo(BigDecimal value) {
            addCriterion("discount_price =", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceNotEqualTo(BigDecimal value) {
            addCriterion("discount_price <>", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceGreaterThan(BigDecimal value) {
            addCriterion("discount_price >", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("discount_price >=", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceLessThan(BigDecimal value) {
            addCriterion("discount_price <", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("discount_price <=", value, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceIn(List<BigDecimal> values) {
            addCriterion("discount_price in", values, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceNotIn(List<BigDecimal> values) {
            addCriterion("discount_price not in", values, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount_price between", value1, value2, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("discount_price not between", value1, value2, "discountPrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceIsNull() {
            addCriterion("sale_price is null");
            return (Criteria) this;
        }

        public Criteria andSalePriceIsNotNull() {
            addCriterion("sale_price is not null");
            return (Criteria) this;
        }

        public Criteria andSalePriceEqualTo(BigDecimal value) {
            addCriterion("sale_price =", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotEqualTo(BigDecimal value) {
            addCriterion("sale_price <>", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceGreaterThan(BigDecimal value) {
            addCriterion("sale_price >", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sale_price >=", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceLessThan(BigDecimal value) {
            addCriterion("sale_price <", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sale_price <=", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceIn(List<BigDecimal> values) {
            addCriterion("sale_price in", values, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotIn(List<BigDecimal> values) {
            addCriterion("sale_price not in", values, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sale_price between", value1, value2, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sale_price not between", value1, value2, "salePrice");
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
