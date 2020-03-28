package com.applet.apply.service;

import com.applet.common.entity.*;
import com.applet.common.mapper.*;
import com.applet.common.util.Constants;
import com.applet.common.util.NullUtil;
import com.applet.common.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2020/3/17
 * Time: 18:11
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@SuppressWarnings("ALL")
@Service
public class CommentService {
    @Autowired
    private CommentInfoMapper commentInfoMapper;
    @Autowired
    private CommentReplyRecordMapper commentReplyRecordMapper;
    @Autowired
    private ViewCommentInfoMapper viewCommentInfoMapper;
    @Autowired
    private ViewCommentReplyRecordMapper viewCommentReplyRecordMapper;
    @Autowired
    private UserService userService;

    public CommentInfo selectCommentInfo(Integer id){
        return commentInfoMapper.selectByPrimaryKey(id);
    }

    public ViewCommentInfo selectViewCommentInfo(Integer id){
        ViewCommentInfoExample example = new ViewCommentInfoExample();
        example.createCriteria().andIdEqualTo(id).andCommentStatusEqualTo(true);
        List<ViewCommentInfo> list = viewCommentInfoMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)){
            return list.get(0);
        }
        return null;
    }

    /**
     * 商品详情页加载评论
     * @param goodsId
     * @return
     */
    public List<ViewCommentInfo> loadCommentListByGoodsId(Integer goodsId){
        Page page = new Page(1,3);
        ViewCommentInfoExample example = new ViewCommentInfoExample();
        example.setPage(page);
        example.setOrderByClause("comment_time desc");
        example.createCriteria().andGoodsIdEqualTo(goodsId).andCommentStatusEqualTo(true);
        return viewCommentInfoMapper.selectByExample(example);
    }

    /**
     * 分页查询商品评论列表
     * @param goodsId
     * @param page
     * @return
     */
    public Page selectCommentListByPage(Integer goodsId, Page page){
        ViewCommentInfoExample example = new ViewCommentInfoExample();
        example.setPage(page);
        example.setOrderByClause("comment_time desc");
        example.createCriteria().andGoodsIdEqualTo(goodsId).andCommentStatusEqualTo(true);
        long count = viewCommentInfoMapper.countByExample(example);
        if (count > 0){
            page.setTotalCount(count);
            page.setDataSource(viewCommentInfoMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 查询订单商品是否已评论
     * @param userId
     * @param orderId
     * @param goodsId
     * @return
     */
    public boolean selectOrderGoodsCommentListByIf(Integer userId, Integer orderId, Integer goodsId){
        ViewCommentInfoExample example = new ViewCommentInfoExample();
        example.createCriteria().andCommentUserIdEqualTo(userId).andOrderIdEqualTo(orderId).andGoodsIdEqualTo(goodsId);
        List<ViewCommentInfo> list = viewCommentInfoMapper.selectByExample(example);
        return NullUtil.isNullOrEmpty(list);
    }

    /**
     * 查询订单商品已评论的记录
     * @param orderDtlIdList
     * @param goodsIdList
     * @return
     */
    public List<ViewCommentInfo> loadOrderGoodsCommentListByAlready(List<Integer> orderIdList, List<Integer> goodsIdList){
        ViewCommentInfoExample example = new ViewCommentInfoExample();
        example.createCriteria().andOrderIdIn(orderIdList).andGoodsIdIn(goodsIdList);
        return viewCommentInfoMapper.selectByExample(example);
    }

    /**
     * 更新评论信息
     * @param info
     */
    public void updateCommentInfo(CommentInfo info){
        if (NullUtil.isNullOrEmpty(info.getId())){
            info.setCommentTime(new Date());
            info.setCommentStatus(true);
            commentInfoMapper.insertSelective(info);
            // 插入已读提醒记录
            userService.updateUserRemindRecord(info.getId(), info.getCommentUserId(), Constants.RELATION_TYPE_COMMENT, 1);
        } else {
            CommentInfoExample example = new CommentInfoExample();
            example.createCriteria().andIdEqualTo(info.getId()).andCommentUserIdEqualTo(info.getCommentUserId());
            info = new CommentInfo();
            info.setCommentStatus(false);
            commentInfoMapper.updateByExampleSelective(info, example);
        }
    }

    /**
     * 查询评论回复信息
     * @param id
     * @return
     */
    public ViewCommentReplyRecord selectCommentReplyRecordById(Integer id){
        ViewCommentReplyRecordExample example = new ViewCommentReplyRecordExample();
        example.createCriteria().andIdEqualTo(id);
        List<ViewCommentReplyRecord> list = viewCommentReplyRecordMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)){
            return list.get(0);
        }
        return null;
    }

    /**
     * 查询评论回复记录
     * @param commentId
     * @return
     */
    public List<ViewCommentReplyRecord> selectCommentReplyRecordByList(Integer commentId){
        ViewCommentReplyRecordExample example = new ViewCommentReplyRecordExample();
        example.setOrderByClause("reply_index asc");
        example.createCriteria().andCommentIdEqualTo(commentId).andReplyStatusEqualTo(true);
        return viewCommentReplyRecordMapper.selectByExample(example);
    }

    /**
     * 查询回复记录总数
     * @param commentId
     * @return
     */
    public int selectCommentReplyRecordByCount(Integer commentId){
        CommentReplyRecordExample example = new CommentReplyRecordExample();
        example.createCriteria().andCommentIdEqualTo(commentId);
        return (int) commentReplyRecordMapper.countByExample(example);
    }

    /**
     * 校验回复是否正常
     * @param commentId
     * @param aimUserId
     * @return
     */
    public boolean checkCommentReplyQualifications(Integer commentId, Integer aimUserId){
        if (NullUtil.isNotNullOrEmpty(aimUserId)){
            CommentReplyRecordExample example = new CommentReplyRecordExample();
            example.createCriteria().andCommentIdEqualTo(commentId).andReplyUserIdEqualTo(aimUserId).andReplyStatusEqualTo(true);
            List<CommentReplyRecord> list = commentReplyRecordMapper.selectByExample(example);
            return NullUtil.isNotNullOrEmpty(list);
        } else {
            CommentInfoExample example1 = new CommentInfoExample();
            example1.createCriteria().andIdEqualTo(commentId).andCommentStatusEqualTo(true);
            List<CommentInfo> list1 = commentInfoMapper.selectByExample(example1);
            return NullUtil.isNotNullOrEmpty(list1);
        }
    }

    /**
     * 更新回复的评论信息
     * @param record
     */
    @Transactional(rollbackFor = Exception.class)
    public CommentReplyRecord updateCommentReplyRecord(CommentReplyRecord record) {
        if (NullUtil.isNullOrEmpty(record.getId())){
            int count = selectCommentReplyRecordByCount(record.getCommentId());
            record.setReplyIndex(++count);
            record.setReplyTime(new Date());
            record.setReplyStatus(true);
            commentReplyRecordMapper.insertSelective(record);

            // 更新提醒记录
            Integer userId = record.getAimUserId();
            if (NullUtil.isNullOrEmpty(userId)){
                CommentInfo info = selectCommentInfo(record.getCommentId());
                userId = info.getCommentUserId();
            }
            userService.updateUserRemindRecord(record.getCommentId(), userId, Constants.RELATION_TYPE_COMMENT);
        } else {
            CommentReplyRecordExample example = new CommentReplyRecordExample();
            example.createCriteria().andIdEqualTo(record.getId()).andAimUserIdEqualTo(record.getAimUserId());
            record = new CommentReplyRecord();
            record.setReplyStatus(false);
            commentReplyRecordMapper.updateByPrimaryKeySelective(record);
        }
        return record;
    }
}
