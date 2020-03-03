package com.applet.apply.service;

import com.applet.common.entity.*;
import com.applet.common.mapper.*;
import com.applet.common.util.NullUtil;
import com.applet.common.util.RandomUtil;
import com.applet.common.util.encryption.DesUtil;
import com.applet.common.util.encryption.MD5Util;
import com.applet.common.util.enums.UserOperationType;
import com.applet.common.util.file.GetImageUtil;
import com.applet.common.util.qiniu.QiNiuUtil;
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
@SuppressWarnings("ALL")
@Service
public class WeChantService {
    private final static Logger log = LoggerFactory.getLogger(WeChantService.class);
    @Autowired(required=true)
    private WeChantInfoMapper weChantInfoMapper;
    @Autowired(required=true)
    private ViewWeChantInfoMapper viewWeChantInfoMapper;
    @Autowired(required=true)
    private UserOperationLogMapper userOperationLogMapper;
    @Autowired(required=true)
    private UserService userService;

    /**
     * 查询微信信息
     *
     * @param appletId
     * @param wxCode
     * @return
     */
    public ViewWeChantInfo selectViewWeChantInfo(Integer appletId, String wxCode) {
        ViewWeChantInfoExample example = new ViewWeChantInfoExample();
        example.createCriteria().andAppletIdEqualTo(appletId).andWxCodeEqualTo(wxCode);
        List<ViewWeChantInfo> list = viewWeChantInfoMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)) {
            ViewWeChantInfo info = list.get(0);
            if (info.getStatus().intValue() == 1) {
                return info;
            }
        }
        return null;
    }

    /**
     * 查询登录微信信息
     *
     * @param wxId
     * @param appletId
     * @return
     */
    public ViewWeChantInfo selectViewWeChantInfo(Integer wxId, Integer appletId) {
        ViewWeChantInfoExample example = new ViewWeChantInfoExample();
        example.createCriteria().andIdEqualTo(wxId).andAppletIdEqualTo(appletId);
        List<ViewWeChantInfo> list = viewWeChantInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询登录微信信息
     *
     * @param wxId
     * @param appletId
     * @param mobile
     * @return
     */
    public ViewWeChantInfo selectWeChantInfo(Integer wxId, Integer appletId, String mobile) {
        ViewWeChantInfoExample example = new ViewWeChantInfoExample();
        example.createCriteria().andIdEqualTo(wxId).andAppletIdEqualTo(appletId).andMobileEqualTo(mobile);
        List<ViewWeChantInfo> list = viewWeChantInfoMapper.selectByExample(example);
        return NullUtil.isNotNullOrEmpty(list) ? list.get(0) : null;
    }

    /**
     * 查询登录微信信息
     *
     * @param appletId
     * @param openId
     * @param nickName
     * @return
     */
    public ViewWeChantInfo selectViewWeChantInfo(Integer appletId, String openId, String nickName, String avatarUrl, boolean gender) {
        ViewWeChantInfoExample example = new ViewWeChantInfoExample();
        example.createCriteria().andOpenIdEqualTo(openId).andAppletIdEqualTo(appletId);
        List<ViewWeChantInfo> list = viewWeChantInfoMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)) {
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
     *
     * @param id
     * @param appletId
     * @param openId
     * @param nickName
     * @param avatarUrl
     * @param gender
     * @return
     */
    public String updateWeChantInfo(Integer id, Integer appletId, String openId, String nickName, String avatarUrl, boolean gender) {
        WeChantInfo info = new WeChantInfo();
        info.setId(id);
        info.setNickName(nickName);
        info.setAvatarUrl(avatarUrl);
        info.setGender(gender);
        info.setCreateTime(new Date());
        if (NullUtil.isNotNullOrEmpty(id)) {
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
     *
     * @param wxCode
     * @return
     */
    public WeChantInfo selectWeChantInfo(String wxCode) {
        WeChantInfoExample example = new WeChantInfoExample();
        example.createCriteria().andWxCodeEqualTo(wxCode);
        List<WeChantInfo> list = weChantInfoMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)) {
            WeChantInfo info = list.get(0);
            if (info.getStatus()) {
                return info;
            }
        }
        return null;
    }

    /**
     * 查询微信信息
     *
     * @param appletId
     * @param userId
     * @return
     */
    public WeChantInfo selectWeChantInfo(Integer appletId, Integer userId) {
        WeChantInfoExample example = new WeChantInfoExample();
        example.createCriteria().andAppletIdEqualTo(appletId).andUserIdEqualTo(userId);
        List<WeChantInfo> list = weChantInfoMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)) {
            WeChantInfo info = list.get(0);
            if (info.getStatus()) {
                return info;
            }
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
    public void updateWeChantUserId(ViewAppletInfo appletInfo, ViewWeChantInfo weChantInfo, UserInfo userInfo, String mobile, UserInfo rmdInfo) {
        if (userInfo == null) {
            //未注册手机号则自动完成注册
            userInfo = new UserInfo();
            userInfo.setId(null);
            userInfo.setMobile(mobile);
            userInfo.setNickName(weChantInfo.getNickName());
            userInfo.setEncrypted(RandomUtil.getRandomStr32());
            String cipher = DesUtil.encrypt(mobile.substring(5, 11), userInfo.getEncrypted());
            cipher = MD5Util.MD5(cipher);
            userInfo.setPassword(cipher);
            String avatarUrl = NullUtil.isNotNullOrEmpty(weChantInfo.getUserId()) ?
                    weChantInfo.getAvatarUrl() : updateUserAvatar(weChantInfo.getAvatarUrl(), "/api/image/USER-A" + RandomUtil.getTimeStamp());
            userInfo.setAvatarUrl(avatarUrl);
            userInfo.setGender(weChantInfo.getGender().intValue() == 1 ? true : false);
            userInfo.setBirthday(weChantInfo.getBirthday());
            userInfo.setEmail(weChantInfo.getEmail());
            userInfo.setIsDealer(false);
            userInfo.setBalance(0.0d);
            userInfo.setFreeBalance(0.0d);
            userInfo.setIntegral(0);
            userInfo.setRecommendId(null == rmdInfo ? null : rmdInfo.getId());
            userInfo.setStatus(true);
            userService.updateUserInfo(userInfo);
        }

        //添加操作日志记录
        addBindMobileLog(appletInfo, weChantInfo, userInfo);

        //更新微信信息
        WeChantInfo wxInfo = new WeChantInfo();
        wxInfo.setId(weChantInfo.getId());
        wxInfo.setUserId(userInfo.getId());
        weChantInfoMapper.updateByPrimaryKeySelective(wxInfo);
    }

    @Async
    public void addBindMobileLog(ViewAppletInfo appletInfo, ViewWeChantInfo weChantInfo, UserInfo userInfo) {
        UserOperationLog log = new UserOperationLog();
        log.setUserId(userInfo.getId());
        if (NullUtil.isNotNullOrEmpty(weChantInfo.getUserId())) {
            log.setType(UserOperationType.BIND_APPLET_UPDATE.toString());
            log.setDescribeContent("在小程序【(ID：" + appletInfo.getId() + ")" + appletInfo.getAppletName() + "】中进行换绑小程序的操作，" +
                    "将原绑定账户【" + userInfo.getMobile() + "】替换为新账户【" + weChantInfo.getMobile() + "】,微信小程序用户ID：" + weChantInfo.getId());
        } else {
            log.setType(UserOperationType.BIND_APPLET.toString());
            log.setDescribeContent("在小程序【(ID：" + appletInfo.getId() + ")" + appletInfo.getAppletName() + "】中进行绑定小程序的操作，" +
                    "绑定了账户【" + userInfo.getMobile() + "】,微信小程序用户ID：" + weChantInfo.getId());
        }
        log.setCreateTime(new Date());
        userOperationLogMapper.insertSelective(log);
        if (NullUtil.isNotNullOrEmpty(weChantInfo.getUserId())) {
            log.setId(null);
            log.setUserId(weChantInfo.getUserId());
            log.setCreateTime(new Date());
            userOperationLogMapper.insertSelective(log);
        }
    }

    /**
     * 更新微信头像到七牛空间
     *
     * @param netUrl
     * @param key
     */
    @Async
    public String updateUserAvatar(String netUrl, String key) {
        try {
            byte[] pdfFile = GetImageUtil.getImageFromNetByUrl(netUrl);
            InputStream inputStream = new ByteArrayInputStream(pdfFile);
            MultipartFile file = new MockMultipartFile("newFileName", "oldFileName", ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
            QiNiuUtil.uploadFile(file, key);
            return key;
        } catch (Exception e) {
            log.error("上传微信头像到七牛空间出错{}", e);
            return null;
        }
    }
}
