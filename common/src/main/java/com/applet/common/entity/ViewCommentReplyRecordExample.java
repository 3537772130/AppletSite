package com.applet.common.entity;

import com.applet.common.util.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewCommentReplyRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public ViewCommentReplyRecordExample() {
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

        public Criteria andCommentIdIsNull() {
            addCriterion("comment_id is null");
            return (Criteria) this;
        }

        public Criteria andCommentIdIsNotNull() {
            addCriterion("comment_id is not null");
            return (Criteria) this;
        }

        public Criteria andCommentIdEqualTo(Integer value) {
            addCriterion("comment_id =", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotEqualTo(Integer value) {
            addCriterion("comment_id <>", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdGreaterThan(Integer value) {
            addCriterion("comment_id >", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_id >=", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdLessThan(Integer value) {
            addCriterion("comment_id <", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdLessThanOrEqualTo(Integer value) {
            addCriterion("comment_id <=", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdIn(List<Integer> values) {
            addCriterion("comment_id in", values, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotIn(List<Integer> values) {
            addCriterion("comment_id not in", values, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdBetween(Integer value1, Integer value2) {
            addCriterion("comment_id between", value1, value2, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_id not between", value1, value2, "commentId");
            return (Criteria) this;
        }

        public Criteria andAimUserIdIsNull() {
            addCriterion("aim_user_id is null");
            return (Criteria) this;
        }

        public Criteria andAimUserIdIsNotNull() {
            addCriterion("aim_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andAimUserIdEqualTo(Integer value) {
            addCriterion("aim_user_id =", value, "aimUserId");
            return (Criteria) this;
        }

        public Criteria andAimUserIdNotEqualTo(Integer value) {
            addCriterion("aim_user_id <>", value, "aimUserId");
            return (Criteria) this;
        }

        public Criteria andAimUserIdGreaterThan(Integer value) {
            addCriterion("aim_user_id >", value, "aimUserId");
            return (Criteria) this;
        }

        public Criteria andAimUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("aim_user_id >=", value, "aimUserId");
            return (Criteria) this;
        }

        public Criteria andAimUserIdLessThan(Integer value) {
            addCriterion("aim_user_id <", value, "aimUserId");
            return (Criteria) this;
        }

        public Criteria andAimUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("aim_user_id <=", value, "aimUserId");
            return (Criteria) this;
        }

        public Criteria andAimUserIdIn(List<Integer> values) {
            addCriterion("aim_user_id in", values, "aimUserId");
            return (Criteria) this;
        }

        public Criteria andAimUserIdNotIn(List<Integer> values) {
            addCriterion("aim_user_id not in", values, "aimUserId");
            return (Criteria) this;
        }

        public Criteria andAimUserIdBetween(Integer value1, Integer value2) {
            addCriterion("aim_user_id between", value1, value2, "aimUserId");
            return (Criteria) this;
        }

        public Criteria andAimUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("aim_user_id not between", value1, value2, "aimUserId");
            return (Criteria) this;
        }

        public Criteria andAimNickNameIsNull() {
            addCriterion("aim_nick_name is null");
            return (Criteria) this;
        }

        public Criteria andAimNickNameIsNotNull() {
            addCriterion("aim_nick_name is not null");
            return (Criteria) this;
        }

        public Criteria andAimNickNameEqualTo(String value) {
            addCriterion("aim_nick_name =", value, "aimNickName");
            return (Criteria) this;
        }

        public Criteria andAimNickNameNotEqualTo(String value) {
            addCriterion("aim_nick_name <>", value, "aimNickName");
            return (Criteria) this;
        }

        public Criteria andAimNickNameGreaterThan(String value) {
            addCriterion("aim_nick_name >", value, "aimNickName");
            return (Criteria) this;
        }

        public Criteria andAimNickNameGreaterThanOrEqualTo(String value) {
            addCriterion("aim_nick_name >=", value, "aimNickName");
            return (Criteria) this;
        }

        public Criteria andAimNickNameLessThan(String value) {
            addCriterion("aim_nick_name <", value, "aimNickName");
            return (Criteria) this;
        }

        public Criteria andAimNickNameLessThanOrEqualTo(String value) {
            addCriterion("aim_nick_name <=", value, "aimNickName");
            return (Criteria) this;
        }

        public Criteria andAimNickNameLike(String value) {
            addCriterion("aim_nick_name like", value, "aimNickName");
            return (Criteria) this;
        }

        public Criteria andAimNickNameNotLike(String value) {
            addCriterion("aim_nick_name not like", value, "aimNickName");
            return (Criteria) this;
        }

        public Criteria andAimNickNameIn(List<String> values) {
            addCriterion("aim_nick_name in", values, "aimNickName");
            return (Criteria) this;
        }

        public Criteria andAimNickNameNotIn(List<String> values) {
            addCriterion("aim_nick_name not in", values, "aimNickName");
            return (Criteria) this;
        }

        public Criteria andAimNickNameBetween(String value1, String value2) {
            addCriterion("aim_nick_name between", value1, value2, "aimNickName");
            return (Criteria) this;
        }

        public Criteria andAimNickNameNotBetween(String value1, String value2) {
            addCriterion("aim_nick_name not between", value1, value2, "aimNickName");
            return (Criteria) this;
        }

        public Criteria andAimAvatarUrlIsNull() {
            addCriterion("aim_avatar_url is null");
            return (Criteria) this;
        }

        public Criteria andAimAvatarUrlIsNotNull() {
            addCriterion("aim_avatar_url is not null");
            return (Criteria) this;
        }

        public Criteria andAimAvatarUrlEqualTo(String value) {
            addCriterion("aim_avatar_url =", value, "aimAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andAimAvatarUrlNotEqualTo(String value) {
            addCriterion("aim_avatar_url <>", value, "aimAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andAimAvatarUrlGreaterThan(String value) {
            addCriterion("aim_avatar_url >", value, "aimAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andAimAvatarUrlGreaterThanOrEqualTo(String value) {
            addCriterion("aim_avatar_url >=", value, "aimAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andAimAvatarUrlLessThan(String value) {
            addCriterion("aim_avatar_url <", value, "aimAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andAimAvatarUrlLessThanOrEqualTo(String value) {
            addCriterion("aim_avatar_url <=", value, "aimAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andAimAvatarUrlLike(String value) {
            addCriterion("aim_avatar_url like", value, "aimAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andAimAvatarUrlNotLike(String value) {
            addCriterion("aim_avatar_url not like", value, "aimAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andAimAvatarUrlIn(List<String> values) {
            addCriterion("aim_avatar_url in", values, "aimAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andAimAvatarUrlNotIn(List<String> values) {
            addCriterion("aim_avatar_url not in", values, "aimAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andAimAvatarUrlBetween(String value1, String value2) {
            addCriterion("aim_avatar_url between", value1, value2, "aimAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andAimAvatarUrlNotBetween(String value1, String value2) {
            addCriterion("aim_avatar_url not between", value1, value2, "aimAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andReplyUserIdIsNull() {
            addCriterion("reply_user_id is null");
            return (Criteria) this;
        }

        public Criteria andReplyUserIdIsNotNull() {
            addCriterion("reply_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andReplyUserIdEqualTo(Integer value) {
            addCriterion("reply_user_id =", value, "replyUserId");
            return (Criteria) this;
        }

        public Criteria andReplyUserIdNotEqualTo(Integer value) {
            addCriterion("reply_user_id <>", value, "replyUserId");
            return (Criteria) this;
        }

        public Criteria andReplyUserIdGreaterThan(Integer value) {
            addCriterion("reply_user_id >", value, "replyUserId");
            return (Criteria) this;
        }

        public Criteria andReplyUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("reply_user_id >=", value, "replyUserId");
            return (Criteria) this;
        }

        public Criteria andReplyUserIdLessThan(Integer value) {
            addCriterion("reply_user_id <", value, "replyUserId");
            return (Criteria) this;
        }

        public Criteria andReplyUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("reply_user_id <=", value, "replyUserId");
            return (Criteria) this;
        }

        public Criteria andReplyUserIdIn(List<Integer> values) {
            addCriterion("reply_user_id in", values, "replyUserId");
            return (Criteria) this;
        }

        public Criteria andReplyUserIdNotIn(List<Integer> values) {
            addCriterion("reply_user_id not in", values, "replyUserId");
            return (Criteria) this;
        }

        public Criteria andReplyUserIdBetween(Integer value1, Integer value2) {
            addCriterion("reply_user_id between", value1, value2, "replyUserId");
            return (Criteria) this;
        }

        public Criteria andReplyUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("reply_user_id not between", value1, value2, "replyUserId");
            return (Criteria) this;
        }

        public Criteria andReplyNickNameIsNull() {
            addCriterion("reply_nick_name is null");
            return (Criteria) this;
        }

        public Criteria andReplyNickNameIsNotNull() {
            addCriterion("reply_nick_name is not null");
            return (Criteria) this;
        }

        public Criteria andReplyNickNameEqualTo(String value) {
            addCriterion("reply_nick_name =", value, "replyNickName");
            return (Criteria) this;
        }

        public Criteria andReplyNickNameNotEqualTo(String value) {
            addCriterion("reply_nick_name <>", value, "replyNickName");
            return (Criteria) this;
        }

        public Criteria andReplyNickNameGreaterThan(String value) {
            addCriterion("reply_nick_name >", value, "replyNickName");
            return (Criteria) this;
        }

        public Criteria andReplyNickNameGreaterThanOrEqualTo(String value) {
            addCriterion("reply_nick_name >=", value, "replyNickName");
            return (Criteria) this;
        }

        public Criteria andReplyNickNameLessThan(String value) {
            addCriterion("reply_nick_name <", value, "replyNickName");
            return (Criteria) this;
        }

        public Criteria andReplyNickNameLessThanOrEqualTo(String value) {
            addCriterion("reply_nick_name <=", value, "replyNickName");
            return (Criteria) this;
        }

        public Criteria andReplyNickNameLike(String value) {
            addCriterion("reply_nick_name like", value, "replyNickName");
            return (Criteria) this;
        }

        public Criteria andReplyNickNameNotLike(String value) {
            addCriterion("reply_nick_name not like", value, "replyNickName");
            return (Criteria) this;
        }

        public Criteria andReplyNickNameIn(List<String> values) {
            addCriterion("reply_nick_name in", values, "replyNickName");
            return (Criteria) this;
        }

        public Criteria andReplyNickNameNotIn(List<String> values) {
            addCriterion("reply_nick_name not in", values, "replyNickName");
            return (Criteria) this;
        }

        public Criteria andReplyNickNameBetween(String value1, String value2) {
            addCriterion("reply_nick_name between", value1, value2, "replyNickName");
            return (Criteria) this;
        }

        public Criteria andReplyNickNameNotBetween(String value1, String value2) {
            addCriterion("reply_nick_name not between", value1, value2, "replyNickName");
            return (Criteria) this;
        }

        public Criteria andReplyAvatarUrlIsNull() {
            addCriterion("reply_avatar_url is null");
            return (Criteria) this;
        }

        public Criteria andReplyAvatarUrlIsNotNull() {
            addCriterion("reply_avatar_url is not null");
            return (Criteria) this;
        }

        public Criteria andReplyAvatarUrlEqualTo(String value) {
            addCriterion("reply_avatar_url =", value, "replyAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andReplyAvatarUrlNotEqualTo(String value) {
            addCriterion("reply_avatar_url <>", value, "replyAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andReplyAvatarUrlGreaterThan(String value) {
            addCriterion("reply_avatar_url >", value, "replyAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andReplyAvatarUrlGreaterThanOrEqualTo(String value) {
            addCriterion("reply_avatar_url >=", value, "replyAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andReplyAvatarUrlLessThan(String value) {
            addCriterion("reply_avatar_url <", value, "replyAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andReplyAvatarUrlLessThanOrEqualTo(String value) {
            addCriterion("reply_avatar_url <=", value, "replyAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andReplyAvatarUrlLike(String value) {
            addCriterion("reply_avatar_url like", value, "replyAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andReplyAvatarUrlNotLike(String value) {
            addCriterion("reply_avatar_url not like", value, "replyAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andReplyAvatarUrlIn(List<String> values) {
            addCriterion("reply_avatar_url in", values, "replyAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andReplyAvatarUrlNotIn(List<String> values) {
            addCriterion("reply_avatar_url not in", values, "replyAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andReplyAvatarUrlBetween(String value1, String value2) {
            addCriterion("reply_avatar_url between", value1, value2, "replyAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andReplyAvatarUrlNotBetween(String value1, String value2) {
            addCriterion("reply_avatar_url not between", value1, value2, "replyAvatarUrl");
            return (Criteria) this;
        }

        public Criteria andReplyContentIsNull() {
            addCriterion("reply_content is null");
            return (Criteria) this;
        }

        public Criteria andReplyContentIsNotNull() {
            addCriterion("reply_content is not null");
            return (Criteria) this;
        }

        public Criteria andReplyContentEqualTo(String value) {
            addCriterion("reply_content =", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentNotEqualTo(String value) {
            addCriterion("reply_content <>", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentGreaterThan(String value) {
            addCriterion("reply_content >", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentGreaterThanOrEqualTo(String value) {
            addCriterion("reply_content >=", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentLessThan(String value) {
            addCriterion("reply_content <", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentLessThanOrEqualTo(String value) {
            addCriterion("reply_content <=", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentLike(String value) {
            addCriterion("reply_content like", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentNotLike(String value) {
            addCriterion("reply_content not like", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentIn(List<String> values) {
            addCriterion("reply_content in", values, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentNotIn(List<String> values) {
            addCriterion("reply_content not in", values, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentBetween(String value1, String value2) {
            addCriterion("reply_content between", value1, value2, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentNotBetween(String value1, String value2) {
            addCriterion("reply_content not between", value1, value2, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyIndexIsNull() {
            addCriterion("reply_index is null");
            return (Criteria) this;
        }

        public Criteria andReplyIndexIsNotNull() {
            addCriterion("reply_index is not null");
            return (Criteria) this;
        }

        public Criteria andReplyIndexEqualTo(Integer value) {
            addCriterion("reply_index =", value, "replyIndex");
            return (Criteria) this;
        }

        public Criteria andReplyIndexNotEqualTo(Integer value) {
            addCriterion("reply_index <>", value, "replyIndex");
            return (Criteria) this;
        }

        public Criteria andReplyIndexGreaterThan(Integer value) {
            addCriterion("reply_index >", value, "replyIndex");
            return (Criteria) this;
        }

        public Criteria andReplyIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("reply_index >=", value, "replyIndex");
            return (Criteria) this;
        }

        public Criteria andReplyIndexLessThan(Integer value) {
            addCriterion("reply_index <", value, "replyIndex");
            return (Criteria) this;
        }

        public Criteria andReplyIndexLessThanOrEqualTo(Integer value) {
            addCriterion("reply_index <=", value, "replyIndex");
            return (Criteria) this;
        }

        public Criteria andReplyIndexIn(List<Integer> values) {
            addCriterion("reply_index in", values, "replyIndex");
            return (Criteria) this;
        }

        public Criteria andReplyIndexNotIn(List<Integer> values) {
            addCriterion("reply_index not in", values, "replyIndex");
            return (Criteria) this;
        }

        public Criteria andReplyIndexBetween(Integer value1, Integer value2) {
            addCriterion("reply_index between", value1, value2, "replyIndex");
            return (Criteria) this;
        }

        public Criteria andReplyIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("reply_index not between", value1, value2, "replyIndex");
            return (Criteria) this;
        }

        public Criteria andReplyTimeIsNull() {
            addCriterion("reply_time is null");
            return (Criteria) this;
        }

        public Criteria andReplyTimeIsNotNull() {
            addCriterion("reply_time is not null");
            return (Criteria) this;
        }

        public Criteria andReplyTimeEqualTo(Date value) {
            addCriterion("reply_time =", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeNotEqualTo(Date value) {
            addCriterion("reply_time <>", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeGreaterThan(Date value) {
            addCriterion("reply_time >", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("reply_time >=", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLessThan(Date value) {
            addCriterion("reply_time <", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLessThanOrEqualTo(Date value) {
            addCriterion("reply_time <=", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeIn(List<Date> values) {
            addCriterion("reply_time in", values, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeNotIn(List<Date> values) {
            addCriterion("reply_time not in", values, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeBetween(Date value1, Date value2) {
            addCriterion("reply_time between", value1, value2, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeNotBetween(Date value1, Date value2) {
            addCriterion("reply_time not between", value1, value2, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyStatusIsNull() {
            addCriterion("reply_status is null");
            return (Criteria) this;
        }

        public Criteria andReplyStatusIsNotNull() {
            addCriterion("reply_status is not null");
            return (Criteria) this;
        }

        public Criteria andReplyStatusEqualTo(Boolean value) {
            addCriterion("reply_status =", value, "replyStatus");
            return (Criteria) this;
        }

        public Criteria andReplyStatusNotEqualTo(Boolean value) {
            addCriterion("reply_status <>", value, "replyStatus");
            return (Criteria) this;
        }

        public Criteria andReplyStatusGreaterThan(Boolean value) {
            addCriterion("reply_status >", value, "replyStatus");
            return (Criteria) this;
        }

        public Criteria andReplyStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("reply_status >=", value, "replyStatus");
            return (Criteria) this;
        }

        public Criteria andReplyStatusLessThan(Boolean value) {
            addCriterion("reply_status <", value, "replyStatus");
            return (Criteria) this;
        }

        public Criteria andReplyStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("reply_status <=", value, "replyStatus");
            return (Criteria) this;
        }

        public Criteria andReplyStatusIn(List<Boolean> values) {
            addCriterion("reply_status in", values, "replyStatus");
            return (Criteria) this;
        }

        public Criteria andReplyStatusNotIn(List<Boolean> values) {
            addCriterion("reply_status not in", values, "replyStatus");
            return (Criteria) this;
        }

        public Criteria andReplyStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("reply_status between", value1, value2, "replyStatus");
            return (Criteria) this;
        }

        public Criteria andReplyStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("reply_status not between", value1, value2, "replyStatus");
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