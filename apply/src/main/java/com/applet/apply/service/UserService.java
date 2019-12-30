package com.applet.apply.service;

import com.applet.apply.entity.ReceiveAddress;
import com.applet.apply.entity.ReceiveAddressExample;
import com.applet.apply.entity.UserInfo;
import com.applet.apply.entity.UserInfoExample;
import com.applet.apply.mapper.ReceiveAddressMapper;
import com.applet.apply.mapper.UserInfoMapper;
import com.applet.common.util.*;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
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
@Service
public class UserService {
    @Autowired(required = true)
    private UserInfoMapper userInfoMapper;
    @Autowired
    private ReceiveAddressMapper receiveAddressMapper;

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
     * @param userInfo
     */
    public void updateUserInfo(UserInfo userInfo) {
        if (NullUtil.isNotNullOrEmpty(userInfo.getId())){
            userInfoMapper.updateByPrimaryKeySelective(userInfo);
        } else {
            userInfo.setCreateDate(new Date());
            userInfoMapper.insertSelective(userInfo);
        }
    }

    /**
     * 查询用户收货人集合
     * @param userId
     * @return
     */
    public List<ReceiveAddress> selectReceiveAddressList(Integer userId){
        ReceiveAddressExample example = new ReceiveAddressExample();
        example.setOrderByClause("update_time desc");
        example.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(true);
        return receiveAddressMapper.selectByExample(example);
    }

    /**
     * 查询用户收货人信息
     * @param id
     * @param userId
     * @return
     */
    public ReceiveAddress selectReceiveAddressInfo(Integer id, Integer userId){
        ReceiveAddressExample example = new ReceiveAddressExample();
        example.createCriteria().andIdEqualTo(id).andUserIdEqualTo(userId).andStatusEqualTo(true);
        List<ReceiveAddress> list = receiveAddressMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 更新用户收货人信息
     * @param record
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateReceiveAddressInfo(ReceiveAddress record){
        if (record.getIsDefault()){
            ReceiveAddressExample example = new ReceiveAddressExample();
            example.createCriteria().andUserIdEqualTo(record.getUserId()).andIsDefaultEqualTo(true).andStatusEqualTo(true);
            ReceiveAddress record1 = new ReceiveAddress();
            record1.setIsDefault(false);
            receiveAddressMapper.updateByExampleSelective(record1, example);
        }
        record.setUpdateTime(new Date());
        if (NullUtil.isNotNullOrEmpty(record.getId())){
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
     * @param id
     */
    public void updateReceiveAddressStatus(Integer id) {
        ReceiveAddress record = new ReceiveAddress();
        record.setId(id);
        record.setStatus(false);
        receiveAddressMapper.updateByPrimaryKeySelective(record);
    }
}
