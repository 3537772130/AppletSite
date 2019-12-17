package com.applet.apply.service;

import com.applet.apply.entity.*;
import com.applet.apply.mapper.*;
import com.applet.apply.util.NullUtil;
import com.applet.apply.util.RandomUtil;
import com.applet.apply.util.encryption.DesUtil;
import com.applet.apply.util.encryption.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by zhouhuahu on 2018/6/26.
 */
@Service
public class WeChantService {
    @Autowired
    private WeChantInfoMapper weChantInfoMapper;
    @Autowired
    private ViewWeChantInfoMapper viewWeChantInfoMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private CommonMapper commonMapper;

    /**
     * 查询登录微信信息
     * @param appletId
     * @param openId
     * @param nickName
     * @return
     */
    public ViewWeChantInfo selectWeChantInfo(Integer appletId, String openId, String nickName, String avatarUrl, boolean gender){
        ViewWeChantInfoExample example = new ViewWeChantInfoExample();
        example.createCriteria().andOpenIdEqualTo(openId).andAppletIdEqualTo(appletId);
        List<ViewWeChantInfo> list = viewWeChantInfoMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)){
            ViewWeChantInfo info = list.get(0);
            updateWeChantInfo(info.getId(), appletId, openId, nickName, avatarUrl, gender);
            return info;
        } else {
            String wxCode = updateWeChantInfo(null, appletId, openId, nickName, avatarUrl, gender);
            ViewWeChantInfo info = new ViewWeChantInfo();
            info.setWxCode(wxCode);
            info.setAppletId(appletId);
            info.setNickName(nickName);
            info.setAvatarUrl(avatarUrl);
            info.setGender(gender ? 1 : 0);
            return info;
        }
    }

    /**
     * 更新微信用户信息
     * @param id
     * @param appletId
     * @param openId
     * @param nickName
     * @param avatarUrl
     * @param gender
     * @return
     */
    public String updateWeChantInfo(Integer id, Integer appletId, String openId, String nickName, String avatarUrl, boolean gender){
        WeChantInfo info = new WeChantInfo();
        info.setId(id);
        info.setNickName(nickName);
        info.setAvatarUrl(avatarUrl);
        info.setGender(gender);
        info.setCreateTime(new Date());
        if (NullUtil.isNotNullOrEmpty(id)){
            weChantInfoMapper.updateByPrimaryKeySelective(info);
        } else {
            info.setAppletId(appletId);
            info.setOpenId(openId);
            String cipher = DesUtil.encrypt(openId, RandomUtil.getUUID());
            cipher = MD5Util.MD5(cipher);
            info.setWxCode(cipher);
            info.setStatus(true);
            weChantInfoMapper.insertSelective(info);
        }
        return info.getWxCode();
    }

    /**
     * 查询微信信息
     * @param wxCode
     * @return
     */
    public WeChantInfo selectWeChantInfo(String wxCode){
        WeChantInfoExample example = new WeChantInfoExample();
        example.createCriteria().andWxCodeEqualTo(wxCode);
        List<WeChantInfo> list = weChantInfoMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)){
            WeChantInfo info = list.get(0);
            if (info.getStatus()){
                return info;
            }
        }
        return null;
    }

    /**
     * 查询微信信息
     * @param appletId
     * @param wxCode
     * @return
     */
    public WeChantInfo selectWeChantInfo(Integer appletId, String wxCode){
        WeChantInfoExample example = new WeChantInfoExample();
        example.createCriteria().andAppletIdEqualTo(appletId).andWxCodeEqualTo(wxCode);
        List<WeChantInfo> list = weChantInfoMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)){
            WeChantInfo info = list.get(0);
            if (info.getStatus()){
                return info;
            }
        }
        return null;
    }

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    public UserInfo getUserInfo(Integer userId){
        return userInfoMapper.selectByPrimaryKey(userId);
    }

    /**
     * 查询用户信息
     * @param mobile
     * @return
     */
    public UserInfo getUserInfo(String mobile){
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andMobileEqualTo(mobile);
        List<UserInfo> list = userInfoMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)){
            return list.get(0);
        }
        return null;
    }

    /**
     * 更新微信绑定用户
     * @param WeChantInfo
     * @param mobile
     * @param code
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWeChant(WeChantInfo WeChantInfo, String mobile, String code){
        UserInfo info = getUserInfo(mobile);
        if (info == null){
            //未注册手机号则自动完成注册
            info = new UserInfo();
            info.setMobile(mobile);
            info.setNickName(WeChantInfo.getNickName());
            info.setEncrypted(RandomUtil.getRandomStr32());
            String cipher = DesUtil.encrypt(code, info.getEncrypted());
            cipher = MD5Util.MD5(cipher);
            info.setPassword(cipher);
            info.setIsDealer(false);
            info.setBalance(0.0d);
            info.setFreeBalance(0.0d);
            info.setIntegral(0);
            info.setCreateDate(new Date());
            info.setStatus(true);
            userInfoMapper.insertSelective(info);
        }
        if (info.getStatus()){
            WeChantInfo.setUserId(info.getId());
            weChantInfoMapper.updateByPrimaryKeySelective(WeChantInfo);
        }
    }

    /**
     * 更新用户信息
     * @param userInfo
     */
    public void updateUserInfo(UserInfo userInfo){
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    /**
     * 微信自动解绑账号
     * @param weChantInfo
     */
    public void updateWeChant(ViewWeChantInfo weChantInfo){
        String sql = "UPDATE we_chant_applet SET user_id = NULL WHERE id = " + weChantInfo.getId();
        commonMapper.updateBatch(sql);
    }
}
