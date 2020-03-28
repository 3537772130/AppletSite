package com.applet.common.entity;

import com.applet.common.util.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewUserCommentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public ViewUserCommentExample() {
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

        public Criteria andRelationIdIsNull() {
            addCriterion("relation_id is null");
            return (Criteria) this;
        }

        public Criteria andRelationIdIsNotNull() {
            addCriterion("relation_id is not null");
            return (Criteria) this;
        }

        public Criteria andRelationIdEqualTo(Integer value) {
            addCriterion("relation_id =", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdNotEqualTo(Integer value) {
            addCriterion("relation_id <>", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdGreaterThan(Integer value) {
            addCriterion("relation_id >", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("relation_id >=", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdLessThan(Integer value) {
            addCriterion("relation_id <", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdLessThanOrEqualTo(Integer value) {
            addCriterion("relation_id <=", value, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdIn(List<Integer> values) {
            addCriterion("relation_id in", values, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdNotIn(List<Integer> values) {
            addCriterion("relation_id not in", values, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdBetween(Integer value1, Integer value2) {
            addCriterion("relation_id between", value1, value2, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("relation_id not between", value1, value2, "relationId");
            return (Criteria) this;
        }

        public Criteria andRelationUserIdIsNull() {
            addCriterion("relation_user_id is null");
            return (Criteria) this;
        }

        public Criteria andRelationUserIdIsNotNull() {
            addCriterion("relation_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andRelationUserIdEqualTo(Integer value) {
            addCriterion("relation_user_id =", value, "relationUserId");
            return (Criteria) this;
        }

        public Criteria andRelationUserIdNotEqualTo(Integer value) {
            addCriterion("relation_user_id <>", value, "relationUserId");
            return (Criteria) this;
        }

        public Criteria andRelationUserIdGreaterThan(Integer value) {
            addCriterion("relation_user_id >", value, "relationUserId");
            return (Criteria) this;
        }

        public Criteria andRelationUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("relation_user_id >=", value, "relationUserId");
            return (Criteria) this;
        }

        public Criteria andRelationUserIdLessThan(Integer value) {
            addCriterion("relation_user_id <", value, "relationUserId");
            return (Criteria) this;
        }

        public Criteria andRelationUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("relation_user_id <=", value, "relationUserId");
            return (Criteria) this;
        }

        public Criteria andRelationUserIdIn(List<Integer> values) {
            addCriterion("relation_user_id in", values, "relationUserId");
            return (Criteria) this;
        }

        public Criteria andRelationUserIdNotIn(List<Integer> values) {
            addCriterion("relation_user_id not in", values, "relationUserId");
            return (Criteria) this;
        }

        public Criteria andRelationUserIdBetween(Integer value1, Integer value2) {
            addCriterion("relation_user_id between", value1, value2, "relationUserId");
            return (Criteria) this;
        }

        public Criteria andRelationUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("relation_user_id not between", value1, value2, "relationUserId");
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

        public Criteria andGoodsPicIsNull() {
            addCriterion("goods_pic is null");
            return (Criteria) this;
        }

        public Criteria andGoodsPicIsNotNull() {
            addCriterion("goods_pic is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsPicEqualTo(String value) {
            addCriterion("goods_pic =", value, "goodsPic");
            return (Criteria) this;
        }

        public Criteria andGoodsPicNotEqualTo(String value) {
            addCriterion("goods_pic <>", value, "goodsPic");
            return (Criteria) this;
        }

        public Criteria andGoodsPicGreaterThan(String value) {
            addCriterion("goods_pic >", value, "goodsPic");
            return (Criteria) this;
        }

        public Criteria andGoodsPicGreaterThanOrEqualTo(String value) {
            addCriterion("goods_pic >=", value, "goodsPic");
            return (Criteria) this;
        }

        public Criteria andGoodsPicLessThan(String value) {
            addCriterion("goods_pic <", value, "goodsPic");
            return (Criteria) this;
        }

        public Criteria andGoodsPicLessThanOrEqualTo(String value) {
            addCriterion("goods_pic <=", value, "goodsPic");
            return (Criteria) this;
        }

        public Criteria andGoodsPicLike(String value) {
            addCriterion("goods_pic like", value, "goodsPic");
            return (Criteria) this;
        }

        public Criteria andGoodsPicNotLike(String value) {
            addCriterion("goods_pic not like", value, "goodsPic");
            return (Criteria) this;
        }

        public Criteria andGoodsPicIn(List<String> values) {
            addCriterion("goods_pic in", values, "goodsPic");
            return (Criteria) this;
        }

        public Criteria andGoodsPicNotIn(List<String> values) {
            addCriterion("goods_pic not in", values, "goodsPic");
            return (Criteria) this;
        }

        public Criteria andGoodsPicBetween(String value1, String value2) {
            addCriterion("goods_pic between", value1, value2, "goodsPic");
            return (Criteria) this;
        }

        public Criteria andGoodsPicNotBetween(String value1, String value2) {
            addCriterion("goods_pic not between", value1, value2, "goodsPic");
            return (Criteria) this;
        }

        public Criteria andCommentContentIsNull() {
            addCriterion("comment_content is null");
            return (Criteria) this;
        }

        public Criteria andCommentContentIsNotNull() {
            addCriterion("comment_content is not null");
            return (Criteria) this;
        }

        public Criteria andCommentContentEqualTo(String value) {
            addCriterion("comment_content =", value, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentNotEqualTo(String value) {
            addCriterion("comment_content <>", value, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentGreaterThan(String value) {
            addCriterion("comment_content >", value, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentGreaterThanOrEqualTo(String value) {
            addCriterion("comment_content >=", value, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentLessThan(String value) {
            addCriterion("comment_content <", value, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentLessThanOrEqualTo(String value) {
            addCriterion("comment_content <=", value, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentLike(String value) {
            addCriterion("comment_content like", value, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentNotLike(String value) {
            addCriterion("comment_content not like", value, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentIn(List<String> values) {
            addCriterion("comment_content in", values, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentNotIn(List<String> values) {
            addCriterion("comment_content not in", values, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentBetween(String value1, String value2) {
            addCriterion("comment_content between", value1, value2, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentNotBetween(String value1, String value2) {
            addCriterion("comment_content not between", value1, value2, "commentContent");
            return (Criteria) this;
        }

        public Criteria andRelationTimeIsNull() {
            addCriterion("relation_time is null");
            return (Criteria) this;
        }

        public Criteria andRelationTimeIsNotNull() {
            addCriterion("relation_time is not null");
            return (Criteria) this;
        }

        public Criteria andRelationTimeEqualTo(Date value) {
            addCriterion("relation_time =", value, "relationTime");
            return (Criteria) this;
        }

        public Criteria andRelationTimeNotEqualTo(Date value) {
            addCriterion("relation_time <>", value, "relationTime");
            return (Criteria) this;
        }

        public Criteria andRelationTimeGreaterThan(Date value) {
            addCriterion("relation_time >", value, "relationTime");
            return (Criteria) this;
        }

        public Criteria andRelationTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("relation_time >=", value, "relationTime");
            return (Criteria) this;
        }

        public Criteria andRelationTimeLessThan(Date value) {
            addCriterion("relation_time <", value, "relationTime");
            return (Criteria) this;
        }

        public Criteria andRelationTimeLessThanOrEqualTo(Date value) {
            addCriterion("relation_time <=", value, "relationTime");
            return (Criteria) this;
        }

        public Criteria andRelationTimeIn(List<Date> values) {
            addCriterion("relation_time in", values, "relationTime");
            return (Criteria) this;
        }

        public Criteria andRelationTimeNotIn(List<Date> values) {
            addCriterion("relation_time not in", values, "relationTime");
            return (Criteria) this;
        }

        public Criteria andRelationTimeBetween(Date value1, Date value2) {
            addCriterion("relation_time between", value1, value2, "relationTime");
            return (Criteria) this;
        }

        public Criteria andRelationTimeNotBetween(Date value1, Date value2) {
            addCriterion("relation_time not between", value1, value2, "relationTime");
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

        public Criteria andRelationStatusEqualTo(Integer value) {
            addCriterion("relation_status =", value, "relationStatus");
            return (Criteria) this;
        }

        public Criteria andRelationStatusNotEqualTo(Integer value) {
            addCriterion("relation_status <>", value, "relationStatus");
            return (Criteria) this;
        }

        public Criteria andRelationStatusGreaterThan(Integer value) {
            addCriterion("relation_status >", value, "relationStatus");
            return (Criteria) this;
        }

        public Criteria andRelationStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("relation_status >=", value, "relationStatus");
            return (Criteria) this;
        }

        public Criteria andRelationStatusLessThan(Integer value) {
            addCriterion("relation_status <", value, "relationStatus");
            return (Criteria) this;
        }

        public Criteria andRelationStatusLessThanOrEqualTo(Integer value) {
            addCriterion("relation_status <=", value, "relationStatus");
            return (Criteria) this;
        }

        public Criteria andRelationStatusIn(List<Integer> values) {
            addCriterion("relation_status in", values, "relationStatus");
            return (Criteria) this;
        }

        public Criteria andRelationStatusNotIn(List<Integer> values) {
            addCriterion("relation_status not in", values, "relationStatus");
            return (Criteria) this;
        }

        public Criteria andRelationStatusBetween(Integer value1, Integer value2) {
            addCriterion("relation_status between", value1, value2, "relationStatus");
            return (Criteria) this;
        }

        public Criteria andRelationStatusNotBetween(Integer value1, Integer value2) {
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