package com.applet.apply.service;

import com.applet.common.util.NullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhouhuahu
 * @date 2018/9/19
 */
@Service
public class ShippingAddressService {
    @Autowired
    private ShippingAddressMapper shippingAddressMapper;


    /**
     * 查询收货地址列表
     *
     * @param userId
     * @return
     */
    public List<ShippingAddress> selectShippingAddressList(Integer userId) {
        ShippingAddressExample example = new ShippingAddressExample();
        example.setOrderByClause("id desc");
        example.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(true);
        return shippingAddressMapper.selectByExample(example);
    }

    /**
     * 查询收货人信息
     *
     * @param id
     * @param userId
     * @return
     */
    public ShippingAddress selectShippingAddressInfo(Integer id, Integer userId) {
        ShippingAddressExample example = new ShippingAddressExample();
        example.createCriteria().andIdEqualTo(id).andUserIdEqualTo(userId).andStatusEqualTo(true);
        List<ShippingAddress> list = shippingAddressMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 查询用户默认收货人信息
     *
     * @param userId
     * @return
     */
    public ShippingAddress selectShippingAddressToDefault(Integer userId) {
        ShippingAddressExample example = new ShippingAddressExample();
        example.createCriteria().andUserIdEqualTo(userId).andIsDefaultEqualTo(true).andStatusEqualTo(true);
        List<ShippingAddress> list = shippingAddressMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 统计用户有效收货地址数目
     *
     * @param userId
     * @return
     */
    public Integer countUserShippingAddress(Integer userId) {
        ShippingAddressExample example = new ShippingAddressExample();
        example.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(true);
        return (int) shippingAddressMapper.countByExample(example);
    }

    /**
     * 添加收货地址
     *
     * @param record
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveShippingAddress(ShippingAddress record) {
        if (NullUtil.isNotNullOrEmpty(record.getId())) {
            shippingAddressMapper.updateByPrimaryKeySelective(record);
        } else {
            int count = countUserShippingAddress(record.getUserId());
            if (count == 0) {
                record.setIsDefault(true);
            } else {
                record.setIsDefault(false);
            }
            record.setStatus(true);
            shippingAddressMapper.insertSelective(record);
        }
    }

    /**
     * 更新用户收货地址是否默认
     *
     * @param userId
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateShippingAddressToDefault(Integer id, Integer userId) {
        ShippingAddress record = selectShippingAddressToDefault(userId);
        if (record == null) {
            //未设置默认
            record = new ShippingAddress();
            record.setId(id);
            record.setIsDefault(true);
        } else {
            //已设置默认
            if (id.intValue() == record.getId()) {
                //正是当前地址
                record.setIsDefault(!record.getIsDefault());
            } else {
                //非当前地址
                record.setIsDefault(false);
                shippingAddressMapper.updateByPrimaryKeySelective(record);
                record = new ShippingAddress();
                record.setId(id);
                record.setIsDefault(true);
            }
        }
        shippingAddressMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 删除收货地址
     *
     * @param record
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteShippingAddressInfo(ShippingAddress record) {
        boolean isDefault = record.getIsDefault();
        record.setIsDefault(false);
        record.setStatus(false);
        shippingAddressMapper.updateByPrimaryKeySelective(record);

        if (isDefault) {
            List<ShippingAddress> list = selectShippingAddressList(record.getUserId());
            if (NullUtil.isNotNullOrEmpty(list)) {
                record = list.get(0);
                record.setIsDefault(true);
                shippingAddressMapper.updateByPrimaryKeySelective(record);
            }
        }
    }
}
