package com.applet.manage.service;

import com.applet.manage.entity.*;
import com.applet.manage.mapper.*;
import com.applet.common.util.*;
import jodd.datetime.JDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @program: SpringBootDemo
 * @description: test  服务类
 * @author: Mr.ZhouHuaHu
 * @create: 2019-06-14 14:52
 **/
@Service
@Component
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private ViewMerchantInfoMapper viewMerchantInfoMapper;

    /**
     * 查询用户列表
     *
     * @param mobile
     * @param nickname
     * @param startDate
     * @param endDate
     * @param page
     * @return
     */
    public Page selectUserToPage(String mobile, String nickname, String startDate, String endDate, Boolean status, Page page) {
        UserInfoExample example = new UserInfoExample();
        example.setPage(page);
        example.setOrderByClause("id desc");
        UserInfoExample.Criteria c = example.createCriteria();
        if (NullUtil.isNotNullOrEmpty(mobile)) {
            c.andMobileLike(mobile + "%");
        }
        if (NullUtil.isNotNullOrEmpty(nickname)) {
            c.andNickNameLike("%" + nickname + "%");
        }
        if (NullUtil.isNotNullOrEmpty(startDate)) {
            JDateTime date = new JDateTime(startDate);
            c.andCreateDateGreaterThanOrEqualTo(date.convertToDate());
        }
        if (NullUtil.isNotNullOrEmpty(endDate)) {
            JDateTime date = new JDateTime(endDate);
            c.andCreateDateLessThanOrEqualTo(date.addDay(1).convertToDate());
        }
        if (NullUtil.isNotNullOrEmpty(status)) {
            c.andStatusEqualTo(status);
        }
        c.andIsDealerEqualTo(false);
        long count = userInfoMapper.countByExample(example);
        if (count > 0) {
            page.setTotalCount(count);
            page.setDataSource(userInfoMapper.selectByExample(example));
        }
        return page;
    }

    /**
     * 查询商户列表
     *
     * @param mobile
     * @param nickname
     * @param extensionCode
     * @param startDate
     * @param endDate
     * @param page
     * @return
     */
    public Page selectMerchantToPage(String mobile, String nickname, String extensionCode, String startDate, String endDate, Boolean status, Page page) {
        ViewMerchantInfoExample example = new ViewMerchantInfoExample();
        example.setPage(page);
        example.setOrderByClause("id desc");
        ViewMerchantInfoExample.Criteria c = example.createCriteria();
        if (NullUtil.isNotNullOrEmpty(mobile)) {
            c.andMobileLike(mobile + "%");
        }
        if (NullUtil.isNotNullOrEmpty(nickname)) {
            c.andNickNameLike("%" + nickname + "%");
        }
        if (NullUtil.isNotNullOrEmpty(extensionCode)) {
            c.andExtensionCodeLike(extensionCode + "%");
        }
        if (NullUtil.isNotNullOrEmpty(startDate)) {
            JDateTime date = new JDateTime(startDate);
            c.andCreateDateGreaterThanOrEqualTo(date.convertToDate());
        }
        if (NullUtil.isNotNullOrEmpty(endDate)) {
            JDateTime date = new JDateTime(endDate);
            c.andCreateDateLessThanOrEqualTo(date.addDay(1).convertToDate());
        }
        if (NullUtil.isNotNullOrEmpty(status)) {
            c.andStatusEqualTo(status);
        }
        c.andIsDealerEqualTo(true);
        long count = viewMerchantInfoMapper.countByExample(example);
        if (count > 0) {
            page.setTotalCount(count);
            page.setDataSource(viewMerchantInfoMapper.selectByExample(example));
        }
        return page;
    }

}
