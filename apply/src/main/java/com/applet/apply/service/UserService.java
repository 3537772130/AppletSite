package com.applet.apply.service;

import com.applet.common.entity.*;
import com.applet.common.mapper.*;
import com.applet.common.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2019/12/29
 * Time: 1:24
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@SuppressWarnings("ALL")
@Service
public class UserService {
    @Autowired(required = true)
    private UserInfoMapper userInfoMapper;
    @Autowired
    private ReceiveAddressMapper receiveAddressMapper;
    @Autowired
    private UserRemindRecordMapper userRemindRecordMapper;
    @Autowired
    private SystemNoticeMapper systemNoticeMapper;
    @Autowired
    private ViewUserSystemNoticeMapper viewUserSystemNoticeMapper;
    @Autowired
    private ViewUserCommentMapper viewUserCommentMapper;

    /**
     * 查询用户信息
     *
     * @param userId
     * @return
     */
    public UserInfo getUserInfo(Integer userId) {
        return userInfoMapper.selectByPrimaryKey(userId);
    }

    /**
     * 查询用户信息
     *
     * @param mobile
     * @return
     */
    public UserInfo getUserInfo(String mobile) {
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andMobileEqualTo(mobile);
        List<UserInfo> list = userInfoMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 更新用户信息
     *
     * @param userInfo
     */
    public void updateUserInfo(UserInfo userInfo) {
        if (NullUtil.isNotNullOrEmpty(userInfo.getId())) {
            userInfoMapper.updateByPrimaryKeySelective(userInfo);
        } else {
            userInfo.setCreateDate(new Date());
            userInfoMapper.insertSelective(userInfo);
        }
    }

    /**
     * 查询用户收货人集合
     *
     * @param userId
     * @return
     */
    public List<ReceiveAddress> selectReceiveAddressList(Integer userId) {
        ReceiveAddressExample example = new ReceiveAddressExample();
        example.setOrderByClause("update_time desc");
        example.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(true);
        return receiveAddressMapper.selectByExample(example);
    }

    /**
     * 查询用户收货人信息
     *
     * @param id
     * @param userId
     * @return
     */
    public ReceiveAddress selectReceiveAddressInfo(Integer id, Integer userId) {
        ReceiveAddressExample example = new ReceiveAddressExample();
        example.createCriteria().andIdEqualTo(id).andUserIdEqualTo(userId).andStatusEqualTo(true);
        List<ReceiveAddress> list = receiveAddressMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询用户默认收货人信息
     *
     * @param userId
     * @return
     */
    public ReceiveAddress selectReceiveAddressInfo(Integer userId) {
        ReceiveAddressExample example = new ReceiveAddressExample();
        example.createCriteria().andUserIdEqualTo(userId).andIsDefaultEqualTo(true).andStatusEqualTo(true);
        List<ReceiveAddress> list = receiveAddressMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 更新用户收货人信息
     *
     * @param record
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateReceiveAddressInfo(ReceiveAddress record) {
        if (record.getIsDefault()) {
            ReceiveAddressExample example = new ReceiveAddressExample();
            example.createCriteria().andUserIdEqualTo(record.getUserId()).andIsDefaultEqualTo(true).andStatusEqualTo(true);
            ReceiveAddress record1 = new ReceiveAddress();
            record1.setIsDefault(false);
            receiveAddressMapper.updateByExampleSelective(record1, example);
        }
        record.setUpdateTime(new Date());
        if (NullUtil.isNotNullOrEmpty(record.getId())) {
            record.setUserId(null);
            record.setStatus(null);
            receiveAddressMapper.updateByPrimaryKeySelective(record);
        } else {
            record.setStatus(true);
            receiveAddressMapper.insertSelective(record);
        }


    }

    /**
     * 删除用户收货人信息
     *
     * @param id
     */
    public void updateReceiveAddressStatus(Integer id) {
        ReceiveAddress record = new ReceiveAddress();
        record.setId(id);
        record.setStatus(false);
        receiveAddressMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 查询系统公告
     * @return
     */
    public SystemNotice selectSystemNoticeByPublic() {
        SystemNoticeExample example = new SystemNoticeExample();
        example.setOrderByClause("create_time desc");
        example.createCriteria().andNoticeTypeEqualTo(Constants.SYSTEM_NOTICE_TYPE_PUBLIC).andNoticeStatusEqualTo(true);
        List<SystemNotice> list = systemNoticeMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)){
            return list.get(0);
        }
        return null;
    }

    /**
     * 设置新用户系统消息推送
     * @param userId
     */
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void setSystemNoticeByNewUser(Integer userId) {
        SystemNoticeExample example = new SystemNoticeExample();
        example.createCriteria().andNoticeTypeEqualTo(Constants.SYSTEM_NOTICE_TYPE_NEW_USER).andNoticeStatusEqualTo(true);
        List<SystemNotice> list = systemNoticeMapper.selectByExample(example);
        for (SystemNotice notice : list) {
            updateUserRemindRecord(notice.getId(), userId, Constants.RELATION_TYPE_SYSTEM);
        }
    }


    /**
     * 更新评论提醒记录
     *
     * @param relationId
     * @param relationUserId
     * @param relationType
     */
    public void updateUserRemindRecord(Integer relationId, Integer relationUserId, Integer relationType) {
        updateUserRemindRecord(relationId, relationUserId, relationType, null);
    }

    public void updateUserRemindRecord(Integer relationId, Integer relationUserId, Integer relationType, Integer relationStatus) {
        if (NullUtil.isNullOrEmpty(relationStatus)){
            relationStatus = 0;
        }
        // 更新消息提醒记录
        UserRemindRecordExample example = new UserRemindRecordExample();
        example.createCriteria().andRelationIdEqualTo(relationId).andRelationUserIdEqualTo(relationUserId).andRelationTypeEqualTo(relationType);
        UserRemindRecord record = new UserRemindRecord();
        record.setRelationTime(new Date());
        record.setRelationStatus(relationStatus);
        int result = userRemindRecordMapper.updateByExampleSelective(record, example);
        if (result == 0) {
            // 未找到相关记录，更新失败后，插入记录
            record.setRelationId(relationId);
            record.setRelationUserId(relationUserId);
            record.setRelationType(relationType);
            userRemindRecordMapper.insertSelective(record);
        }
    }



    /**
     * 统计通知记录未读总数
     *
     * @param relationUserId
     * @param relationType
     * @return
     */
    public int selectUserRemindRecordByCount(Integer relationUserId, Integer relationType) {
        if (NullUtil.isNullOrEmpty(relationUserId)){
            return 0;
        }
        UserRemindRecordExample example = new UserRemindRecordExample();
        UserRemindRecordExample.Criteria c = example.createCriteria();
        c.andRelationUserIdEqualTo(relationUserId);
        if (NullUtil.isNotNullOrEmpty(relationType)){
            c.andRelationTypeEqualTo(relationType);
        }
        c.andRelationStatusEqualTo(0);
        return (int) userRemindRecordMapper.countByExample(example);
    }

    /**
     * 查询系统通知消息
     * @param id
     * @param userId
     * @return
     */
    public ViewUserSystemNotice selectUserSystemNotice(Integer id, Integer userId){
        ViewUserSystemNoticeExample example = new ViewUserSystemNoticeExample();
        example.createCriteria().andIdEqualTo(id).andRelationUserIdEqualTo(userId);
        List<ViewUserSystemNotice> list = viewUserSystemNoticeMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)){
            ViewUserSystemNotice info = list.get(0);
            // 更新用户系统通知消息状态
            updateUserRemindRecord(info.getRelationId(), info.getRelationUserId(), Constants.RELATION_TYPE_SYSTEM, 1);
            return info;
        }
        return null;
    }

    /**
     * 统计消息阅读量
     * @param relationId
     * @return
     */
    public Integer countUserSystemNoticeByRead(Integer relationId){
        ViewUserSystemNoticeExample example = new ViewUserSystemNoticeExample();
        example.createCriteria().andRelationIdEqualTo(relationId).andRelationStatusEqualTo(1);
        return (int) viewUserSystemNoticeMapper.countByExample(example);
    }

    /**
     * 分页查询用户系统通知消息
     * @param userId
     * @param page
     * @return
     */
    public Page selectUserSystemNoticeByPage(Integer userId, Page page) {
        ViewUserSystemNoticeExample example = new ViewUserSystemNoticeExample();
        example.setPage(page);
        example.setOrderByClause("relation_status,relation_time desc");
        example.createCriteria().andRelationUserIdEqualTo(userId);
        page.setTotalCount(viewUserSystemNoticeMapper.countByExample(example));
        page.setDataSource(viewUserSystemNoticeMapper.selectByExample(example));
        return page;
    }

    /**
     * 分页查询用户评论通知消息
     * @param userId
     * @param page
     * @return
     */
    public Page selectUserCommentByPage(Integer userId, Page page) {
        ViewUserCommentExample example = new ViewUserCommentExample();
        example.setPage(page);
        example.setOrderByClause("relation_status,relation_time desc");
        example.createCriteria().andRelationUserIdEqualTo(userId);
        page.setTotalCount(viewUserCommentMapper.countByExample(example));
        page.setDataSource(viewUserCommentMapper.selectByExample(example));
        return page;
    }

}
