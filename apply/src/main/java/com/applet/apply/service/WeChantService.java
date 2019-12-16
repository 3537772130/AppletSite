package com.applet.apply.service;

import com.applet.apply.entity.*;
import com.applet.apply.mapper.*;
import com.applet.apply.util.NullUtil;
import com.applet.apply.util.RandomUtil;
import com.applet.apply.util.encryption.DesUtil;
import com.applet.apply.util.encryption.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
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
    private WeChantAppletMapper weChantAppletMapper;
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
    public WeChantApplet selectWeChantApplet(Integer appletId, String openId, String nickName){
        WeChantAppletExample example = new WeChantAppletExample();
        example.createCriteria().andOpenIdEqualTo(openId).andAppletIdEqualTo(appletId);
        List<WeChantApplet> list = weChantAppletMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)){
            WeChantApplet info = list.get(0);
            if (info.getStatus()){
                return info;
            }
            return null;
        } else {
            WeChantApplet info = new WeChantApplet();
            info.setAppletId(appletId);
            info.setOpenId(openId);
            String cipher = DesUtil.encrypt(openId, RandomUtil.getUUID());
            cipher = MD5Util.MD5(cipher);
            info.setWxLogo(cipher);
            info.setNickName(nickName);
            info.setCreateTime(new Date());
            info.setStatus(true);
            weChantAppletMapper.insertSelective(info);
            return info;
        }
    }

    /**
     * 查询微信信息
     * @param wxLogo
     * @return
     */
    public WeChantApplet selectWeChantApplet(String wxLogo){
        WeChantAppletExample example = new WeChantAppletExample();
        example.createCriteria().andWxLogoEqualTo(wxLogo);
        List<WeChantApplet> list = weChantAppletMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)){
            WeChantApplet info = list.get(0);
            if (info.getStatus()){
                return info;
            }
        }
        return null;
    }

    /**
     * 查询微信信息
     * @param appletId
     * @param wxLogo
     * @return
     */
    public WeChantApplet selectWeChantApplet(Integer appletId, String wxLogo){
        WeChantAppletExample example = new WeChantAppletExample();
        example.createCriteria().andAppletIdEqualTo(appletId).andWxLogoEqualTo(wxLogo);
        List<WeChantApplet> list = weChantAppletMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)){
            WeChantApplet info = list.get(0);
            if (info.getStatus()){
                return info;
            }
        }
        return null;
    }

    /**
     * 修改微信昵称
     * @param id
     * @param nickName
     */
    public void updateNickName(Integer id, String nickName){
        WeChantApplet record = new WeChantApplet();
        record.setId(id);
        record.setNickName(nickName);
        weChantAppletMapper.updateByPrimaryKeySelective(record);
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
     * @param weChantApplet
     * @param mobile
     * @param code
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWeChant(WeChantApplet weChantApplet, String mobile, String code){
        UserInfo info = getUserInfo(mobile);
        if (info == null){
            //未注册手机号则自动完成注册
            info = new UserInfo();
            info.setMobile(mobile);
            info.setNickName(weChantApplet.getNickName());
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
            weChantApplet.setUserId(info.getId());
            weChantAppletMapper.updateByPrimaryKeySelective(weChantApplet);
        }
    }

    /**
     * 微信自动解绑账号
     * @param weChantApplet
     */
    public void updateWeChant(WeChantApplet weChantApplet){
        String sql = "UPDATE we_chant_applet SET user_id = NULL WHERE id = " + weChantApplet.getId();
        commonMapper.updateBatch(sql);
    }
}
