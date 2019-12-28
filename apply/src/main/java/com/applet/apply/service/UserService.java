package com.applet.apply.service;

import com.applet.apply.config.annotation.SessionScope;
import com.applet.apply.entity.AuthCodeExample;
import com.applet.apply.entity.UserInfo;
import com.applet.apply.entity.UserInfoExample;
import com.applet.apply.entity.WeChantInfo;
import com.applet.apply.mapper.UserInfoMapper;
import com.applet.common.entity.CheckResult;
import com.applet.common.util.*;
import com.applet.common.util.qiniu.QiNiuUtil;
import jodd.datetime.JDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
