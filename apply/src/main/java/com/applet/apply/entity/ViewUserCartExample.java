package com.applet.apply.entity;

import com.applet.common.util.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewUserCartExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public ViewUserCartExample() {
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

        public Criteria andDiscountIsNull() {
            addCriterion("discount is null");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNotNull() {
            addCriterion("discount is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountEqualTo(Integer value) {
            addCriterion("discount =", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotEqualTo(Integer value) {
            addCriterion("discount <>", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThan(Integer value) {
            addCriterion("discount >", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThanOrEqualTo(Integer value) {
            addCriterion("discount >=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThan(Integer value) {
            addCriterion("discount <", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThanOrEqualTo(Integer value) {
            addCriterion("discount <=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountIn(List<Integer> values) {
            addCriterion("discount in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotIn(List<Integer> values) {
            addCriterion("discount not in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountBetween(Integer value1, Integer value2) {
            addCriterion("discount between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotBetween(Integer value1, Integer value2) {
            addCriterion("discount not between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andIfDiscountIsNull() {
            addCriterion("if_discount is null");
            return (Criteria) this;
        }

        public Criteria andIfDiscountIsNotNull() {
            addCriterion("if_discount is not null");
            return (Criteria) this;
        }

        public Criteria andIfDiscountEqualTo(Boolean value) {
            addCriterion("if_discount =", value, "ifDiscount");
            return (Criteria) this;
        }

        public Criteria andIfDiscountNotEqualTo(Boolean value) {
            addCriterion("if_discount <>", value, "ifDiscount");
            return (Criteria) this;
        }

        public Criteria andIfDiscountGreaterThan(Boolean value) {
            addCriterion("if_discount >", value, "ifDiscount");
            return (Criteria) this;
        }

        public Criteria andIfDiscountGreaterThanOrEqualTo(Boolean value) {
            addCriterion("if_discount >=", value, "ifDiscount");
            return (Criteria) this;
        }

        public Criteria andIfDiscountLessThan(Boolean value) {
            addCriterion("if_discount <", value, "ifDiscount");
            return (Criteria) this;
        }

        public Criteria andIfDiscountLessThanOrEqualTo(Boolean value) {
            addCriterion("if_discount <=", value, "ifDiscount");
            return (Criteria) this;
        }

        public Criteria andIfDiscountIn(List<Boolean> values) {
            addCriterion("if_discount in", values, "ifDiscount");
            return (Criteria) this;
        }

        public Criteria andIfDiscountNotIn(List<Boolean> values) {
            addCriterion("if_discount not in", values, "ifDiscount");
            return (Criteria) this;
        }

        public Criteria andIfDiscountBetween(Boolean value1, Boolean value2) {
            addCriterion("if_discount between", value1, value2, "ifDiscount");
            return (Criteria) this;
        }

        public Criteria andIfDiscountNotBetween(Boolean value1, Boolean value2) {
            addCriterion("if_discount not between", value1, value2, "ifDiscount");
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

        public Criteria andSpecsIdIsNull() {
            addCriterion("specs_id is null");
            return (Criteria) this;
        }

        public Criteria andSpecsIdIsNotNull() {
            addCriterion("specs_id is not null");
            return (Criteria) this;
        }

        public Criteria andSpecsIdEqualTo(Integer value) {
            addCriterion("specs_id =", value, "specsId");
            return (Criteria) this;
        }

        public Criteria andSpecsIdNotEqualTo(Integer value) {
            addCriterion("specs_id <>", value, "specsId");
            return (Criteria) this;
        }

        public Criteria andSpecsIdGreaterThan(Integer value) {
            addCriterion("specs_id >", value, "specsId");
            return (Criteria) this;
        }

        public Criteria andSpecsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("specs_id >=", value, "specsId");
            return (Criteria) this;
        }

        public Criteria andSpecsIdLessThan(Integer value) {
            addCriterion("specs_id <", value, "specsId");
            return (Criteria) this;
        }

        public Criteria andSpecsIdLessThanOrEqualTo(Integer value) {
            addCriterion("specs_id <=", value, "specsId");
            return (Criteria) this;
        }

        public Criteria andSpecsIdIn(List<Integer> values) {
            addCriterion("specs_id in", values, "specsId");
            return (Criteria) this;
        }

        public Criteria andSpecsIdNotIn(List<Integer> values) {
            addCriterion("specs_id not in", values, "specsId");
            return (Criteria) this;
        }

        public Criteria andSpecsIdBetween(Integer value1, Integer value2) {
            addCriterion("specs_id between", value1, value2, "specsId");
            return (Criteria) this;
        }

        public Criteria andSpecsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("specs_id not between", value1, value2, "specsId");
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

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Integer value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Integer value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Integer value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Integer value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Integer value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Integer> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Integer> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Integer value1, Integer value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("amount not between", value1, value2, "amount");
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