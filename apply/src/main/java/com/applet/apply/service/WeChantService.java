package com.applet.apply.service;

import com.applet.apply.entity.*;
import com.applet.apply.mapper.*;
import com.applet.apply.util.NullUtil;
import com.applet.apply.util.RandomUtil;
import com.applet.apply.util.encryption.DesUtil;
import com.applet.apply.util.encryption.MD5Util;
import com.applet.apply.util.enums.UserOperationType;
import com.applet.apply.util.file.GetImageUtil;
import com.applet.apply.util.qiniu.QiNiuConfig;
import com.applet.apply.util.qiniu.QiNiuUtil;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by zhouhuahu on 2018/6/26.
 */
@Service
public class WeChantService {
    private final static Logger log = LoggerFactory.getLogger(WeChantService.class);
    @Autowired
    private WeChantInfoMapper weChantInfoMapper;
    @Autowired
    private ViewWeChantInfoMapper viewWeChantInfoMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserOperationLogMapper userOperationLogMapper;
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
     * 查询登录微信信息
     * @param wxId
     * @param appletId
     * @param mobile
     * @return
     */
    public ViewWeChantInfo selectWeChantInfo(Integer wxId, Integer appletId, String mobile){
        ViewWeChantInfoExample example = new ViewWeChantInfoExample();
        example.createCriteria().andIdEqualTo(wxId).andAppletIdEqualTo(appletId).andMobileEqualTo(mobile);
        List<ViewWeChantInfo> list = viewWeChantInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
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
     * 查询微信信息
     * @param appletId
     * @param userId
     * @return
     */
    public WeChantInfo selectWeChantInfo(Integer appletId, Integer userId){
        WeChantInfoExample example = new WeChantInfoExample();
        example.createCriteria().andAppletIdEqualTo(appletId).andUserIdEqualTo(userId);
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
     *
     * @param appletInfo
     * @param weChantInfo
     * @param userInfo
     * @param mobile
     * @param rmdInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateWeChantUserId(ViewAppletInfo appletInfo, WeChantInfo weChantInfo, UserInfo userInfo, String mobile, UserInfo rmdInfo){
        if (userInfo == null){
            //未注册手机号则自动完成注册
            userInfo = new UserInfo();
            userInfo.setMobile(mobile);
            userInfo.setNickName(weChantInfo.getNickName());
            userInfo.setEncrypted(RandomUtil.getRandomStr32());
            String cipher = DesUtil.encrypt(mobile.substring(5, 11), userInfo.getEncrypted());
            cipher = MD5Util.MD5(cipher);
            userInfo.setPassword(cipher);
            userInfo.setAvatarUrl("/api/image/USER-A" + RandomUtil.getTimeStamp());
            userInfo.setGender(weChantInfo.getGender());
            userInfo.setBirthday(weChantInfo.getBirthday());
            userInfo.setEmail(weChantInfo.getEmail());
            userInfo.setIsDealer(false);
            userInfo.setBalance(0.0d);
            userInfo.setFreeBalance(0.0d);
            userInfo.setIntegral(0);
            userInfo.setRecommendId(null == rmdInfo ? null : rmdInfo.getId());
            userInfo.setCreateDate(new Date());
            userInfo.setStatus(true);
            userInfoMapper.insertSelective(userInfo);
            updateUserInfo(userInfo, weChantInfo.getAvatarUrl());
        }

        //操作日志记录
        UserOperationLog log = new UserOperationLog();
        log.setUserId(userInfo.getId());
        if (NullUtil.isNotNullOrEmpty(weChantInfo.getUserId())){
            log.setType(UserOperationType.BIND_APPLET_UPDATE.toString());
            log.setDescribeContent("在小程序【" + appletInfo.getId() + "："+ appletInfo.getAppletName() + "】中进行换绑小程序的操作，" +
                    "将原绑定账户【ID：" + weChantInfo.getUserId() + "】替换为新账户【ID：" + userInfo.getId() + "】");
        } else {
            log.setType(UserOperationType.BIND_APPLET.toString());
            log.setDescribeContent("在小程序【" + appletInfo.getId() + "："+ appletInfo.getAppletName() + "】中进行绑定小程序的操作，" +
                    "绑定了账户【ID：" + userInfo.getId() + "】");
        }
        log.setCreateTime(new Date());
        userOperationLogMapper.insertSelective(log);
        if (NullUtil.isNotNullOrEmpty(weChantInfo.getUserId())){
            log.setId(null);
            log.setUserId(weChantInfo.getUserId());
            log.setCreateTime(new Date());
            userOperationLogMapper.insertSelective(log);
        }

        //更新微信信息
        weChantInfo.setUserId(userInfo.getId());
        weChantInfoMapper.updateByPrimaryKeySelective(weChantInfo);
    }

    /**
     * 更新微信头像到七牛空间
     * @param info
     * @param avatarUrl
     */
    @Async
    public void updateUserInfo(UserInfo info, String avatarUrl){
        try {
            byte[] pdfFile = GetImageUtil.getImageFromNetByUrl(avatarUrl);
            InputStream inputStream = new ByteArrayInputStream(pdfFile);
            MultipartFile file = new MockMultipartFile("newFileName","oldFileName", ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
            QiNiuUtil.uploadFile(file, QiNiuConfig.bucketAppletImage);
            userInfoMapper.updateByPrimaryKeySelective(info);
        } catch (Exception e) {
            log.error("上传微信头像到七牛空间出错{}", e);
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