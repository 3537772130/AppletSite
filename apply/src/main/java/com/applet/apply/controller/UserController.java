package com.applet.apply.controller;

import com.applet.apply.config.annotation.CancelAuth;
import com.applet.apply.config.annotation.SessionScope;
import com.applet.common.entity.*;
import com.applet.apply.service.*;
import com.applet.common.entity.other.CheckResult;
import com.applet.common.util.*;
import com.applet.common.util.encryption.DesUtil;
import com.applet.common.util.encryption.MD5Util;
import com.applet.common.util.enums.SMSEnum;
import com.applet.common.util.qiniu.QiNiuUtil;
import jodd.datetime.JDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhouhuahu
 * @date 2018/9/19
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/applet/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthCodeService authCodeService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private WeChantService weChantService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private CommentService commentService;

    /**
     * 检查手机号
     *
     * @param mobile
     * @return
     */
    @RequestMapping(value = "checkMobile")
    @CancelAuth
    public Object checkMobile(@RequestParam("mobile") String mobile) {
        if (NullUtil.isNullOrEmpty(mobile)) {
            return AjaxResponse.error("账号不能为空");
        }
        if (!RegularUtil.checkMobile(mobile)) {
            return AjaxResponse.error("账号格式不正确");
        }
        UserInfo userInfo = userService.getUserInfo(mobile);
        if (null == userInfo) {
            return AjaxResponse.msg("0", "");
        }
        if (!userInfo.getStatus()) {
            return AjaxResponse.error("账号已禁用");
        }
        return AjaxResponse.success();
    }

    /**
     * 发送修改密码验证码
     *
     * @param weChantInfo
     * @param ipAddress
     * @return
     */
    @RequestMapping(value = "sendUpdatePassVerifyCode")
    public Object sendUpdatePassVerifyCode(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                           @SessionScope(Constants.CLIENT_PUBLIC_IP) String ipAddress) {
        try {
            UserInfo userInfo = userService.getUserInfo(weChantInfo.getUserId());
            if (null == userInfo) {
                return AjaxResponse.error("参数错误");
            }
            if (!userInfo.getStatus()) {
                return AjaxResponse.error("账号已禁用");
            }
            int senNum = authCodeService.getTodaySendCodeCount(userInfo.getMobile(), SMSEnum.type.UPDATE_PASS.toString());
            if (senNum >= Constants.SMS_CODE_AMOUNT.intValue()) {
                return AjaxResponse.error("今日短信发送次数已达上限，请明日再试");
            }
            int validityNum = authCodeService.selectVerifyCodeValidityCount(userInfo.getMobile(), SMSEnum.type.UPDATE_PASS.toString());
            if (validityNum > 0) {
                return AjaxResponse.error("操作频繁，请稍后再试");
            }
            String channel = SMSEnum.channel.ALIYUN.toString();
            String remark = "修改密码";

            AuthCode authCode = new AuthCode();
            authCode.setUserId(userInfo.getId());
            authCode.setMobile(userInfo.getMobile());
            authCode.setAuthType(SMSEnum.type.UPDATE_PASS.toString());
            authCode.setAuthCode(RandomUtil.getRandomStr(6));
            JDateTime time = new JDateTime(new Date());
            authCode.setSendTime(time.convertToDate());
            authCode.setOverTime(time.addMinute(10).convertToDate());
            authCode.setRemark(remark);
            authCode.setChannel(channel);
            authCode.setIpAddress(ipAddress);
            boolean bool = smsService.sendCode(authCode);
            if (bool) {
                authCodeService.addAuthCode(authCode);
                return AjaxResponse.success("发送成功，验证码10分钟内有效");
            }
        } catch (Exception e) {
            log.error("修改密码发送验证码出错{}", e);
        }
        return AjaxResponse.error("发送失败");
    }

    /**
     * 更新用户密码
     *
     * @param weChantInfo
     * @param pass
     * @param code
     * @return
     */
    @RequestMapping(value = "updateUserPassToCode")
    public Object updateUserPassToCode(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                       String pass, String code) {
        try {
            if (NullUtil.isNullOrEmpty(code)) {
                return AjaxResponse.success("请输入验证码");
            }
            if (NullUtil.isNullOrEmpty(pass)) {
                return AjaxResponse.success("请输入密码");
            }
            if (pass.length() < 6 && pass.length() > 20) {
                return AjaxResponse.error("密码长度为6-20位");
            }
            UserInfo userInfo = userService.getUserInfo(weChantInfo.getUserId());
            if (null == userInfo) {
                return AjaxResponse.error("参数错误");
            }
            if (!userInfo.getStatus()) {
                return AjaxResponse.error("账号已禁用");
            }
            AuthCode authCode = authCodeService.selectAuthCodeByMobile(userInfo.getMobile(), SMSEnum.type.UPDATE_PASS.toString());
            if (null == authCode) {
                return AjaxResponse.error("验证码已过期，请重新发送");
            }
            if (!authCode.getAuthCode().equals(code)) {
                return AjaxResponse.error("验证码输入不匹配");
            }
            String cipher = DesUtil.encrypt(pass, userInfo.getEncrypted());
            cipher = MD5Util.MD5(cipher);
            userInfo.setPassword(cipher);
            userService.updateUserInfo(userInfo);
            return AjaxResponse.success("修改成功");
        } catch (Exception e) {
            log.error("CODE-更新用户密码出错{}", e);
            return AjaxResponse.error("修改失败");
        }
    }

    /**
     * 通过旧密码更新密码
     *
     * @param weChantInfo
     * @param oldPass
     * @param newPass
     * @return
     */
    @RequestMapping(value = "updateUserPassToPass")
    public Object updateUserPassToPass(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                       String oldPass, String newPass) {
        try {
            if (NullUtil.isNullOrEmpty(oldPass) || NullUtil.isNullOrEmpty(newPass)) {
                return AjaxResponse.error("参数缺失");
            }
            UserInfo userInfo = userService.getUserInfo(weChantInfo.getUserId());
            if (null == userInfo) {
                return AjaxResponse.error("参数错误");
            }
            if (!userInfo.getStatus()) {
                return AjaxResponse.error("账号已禁用");
            }
            String cipher = DesUtil.encrypt(oldPass, userInfo.getEncrypted());
            cipher = MD5Util.MD5(cipher);
            if (!cipher.equals(userInfo.getPassword())) {
                return AjaxResponse.error("修改失败，旧密码不匹配");
            }
            cipher = DesUtil.encrypt(newPass, userInfo.getEncrypted());
            cipher = MD5Util.MD5(cipher);
            userInfo.setPassword(cipher);
            userService.updateUserInfo(userInfo);
            return AjaxResponse.success("修改成功");
        } catch (Exception e) {
            log.error("PASS-更新用户密码出错{}", e);
            return AjaxResponse.error("修改失败");
        }
    }


    /**
     * 上传用户头像
     *
     * @param weChantInfo
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "uploadUserAvatar")
    public Object uploadUserAvatar(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                                   @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                   @RequestParam("avatar") MultipartFile multipartFile) {
        try {
            //校验文件信息
            CheckResult result = CheckFileUtil.checkImageFile(multipartFile);
            if (!result.getBool()) {
                return AjaxResponse.error(result.getMsg());
            }
            UserInfo userInfo = userService.getUserInfo(weChantInfo.getUserId());
            if (null == userInfo) {
                return AjaxResponse.error("没有权限");
            }
            if (!userInfo.getStatus()) {
                return AjaxResponse.error("账号已禁用");
            }
            if (null == multipartFile) {
                return AjaxResponse.error("参数错误");
            }
            if (NullUtil.isNullOrEmpty(userInfo.getAvatarUrl())) {
                userInfo.setAvatarUrl("/api/image/USER-A" + RandomUtil.getTimeStamp());
            }
            QiNiuUtil.uploadFile(multipartFile, userInfo.getAvatarUrl());
            Map map = new HashMap();
            map.put("avatarUrl", userInfo.getAvatarUrl());

            // 更新redis信息
            ViewWeChantInfo wxInfo = weChantService.selectViewWeChantInfo(weChantInfo.getId(), appletInfo.getId());
            redisService.setValue(wxInfo.getWxCode(), wxInfo);
            return AjaxResponse.success(map);
        } catch (Exception e) {
            log.error("小程序上传用户头像出错{}", e);
            return AjaxResponse.error("上传失败");
        }
    }

    /**
     * 更新用户信息
     *
     * @param weChantInfo
     * @param nickName
     * @param gender
     * @param birthday
     * @param email
     * @return
     */
    @RequestMapping(value = "updateUserInfo")
    public Object updateUserInfo(@SessionScope("appletInfo") ViewAppletInfo appletInfo,
                                 @SessionScope("weChantInfo") ViewWeChantInfo weChantInfo,
                                 String nickName, Integer gender, String birthday, String email) {
        try {
            if (NullUtil.isNullOrEmpty(nickName)) {
                return AjaxResponse.error("请输入昵称");
            }
            if (nickName.getBytes().length > 60) {
                return AjaxResponse.error("昵称长度为1-20个字符");
            }
            if (NullUtil.isNotNullOrEmpty(email) && !RegularUtil.checkEmail(email)) {
                return AjaxResponse.error("邮箱格式不正确");
            }
            UserInfo userInfo = userService.getUserInfo(weChantInfo.getUserId());
            if (null == userInfo) {
                return AjaxResponse.error("没有权限");
            }
            if (!userInfo.getStatus()) {
                return AjaxResponse.error("账号已禁用");
            }
            userInfo.setNickName(nickName);
            userInfo.setGender(gender.intValue() == 1);
            userInfo.setBirthday(birthday);
            userInfo.setEmail(email);
            userService.updateUserInfo(userInfo);

            // 更新redis信息
            ViewWeChantInfo wxInfo = weChantService.selectViewWeChantInfo(weChantInfo.getId(), appletInfo.getId());
            redisService.setValue(wxInfo.getWxCode(), wxInfo);
            return AjaxResponse.success("提交成功");
        } catch (Exception e) {
            log.error("小程序更新用户信息出错{}", e);
            return AjaxResponse.error("提交失败");
        }
    }

    /**
     * 查询收货人列表
     * @param weChantInfo
     * @return
     */
    @RequestMapping(value = "queryReceiveAddressList")
    public Object queryReceiveAddressList(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo){
        List<ReceiveAddress> list = userService.selectReceiveAddressList(weChantInfo.getUserId());
        if (NullUtil.isNotNullOrEmpty(list)){
            return AjaxResponse.success(list);
        }
        return AjaxResponse.error("未找到相关记录");
    }

    /**
     * 查询收货人信息
     * @param weChantInfo
     * @param id
     * @return
     */
    @RequestMapping(value = "queryReceiveAddressInfo")
    public Object queryReceiveAddressInfo(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer id){
        ReceiveAddress receiveAddress = userService.selectReceiveAddressInfo(id, weChantInfo.getUserId());
        if (receiveAddress != null){
            return AjaxResponse.success(receiveAddress);
        }
        return AjaxResponse.error("未找到相关记录");
    }

    /**
     * 保存收货人信息
     * @param weChantInfo
     * @param record
     * @return
     */
    @RequestMapping(value = "addReceiveAddress")
    public Object addReceiveAddress(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, @Param("record") ReceiveAddress record){
        try {
            if (!NullUtil.isNotNullOrEmpty(weChantInfo.getUserId())){
                return AjaxResponse.msg("0","未绑定账号");
            }
            if (record == null){
                return AjaxResponse.error("参数错误");
            }
            if (NullUtil.isNotNullOrEmpty(record.getId())){
                record.setId(record.getId().intValue() == 0 ? null : record.getId());
            }
            if (NullUtil.isNullOrEmpty(record.getName())){
                return AjaxResponse.error("收货人不能为空");
            }
            if (record.getName().trim().getBytes().length > 60){
                return AjaxResponse.error("收货人输入过长");
            }
            if (NullUtil.isNullOrEmpty(record.getMobile())){
                return AjaxResponse.error("联系电话不能为空");
            }
            if (!RegularUtil.checkMobile(record.getMobile())){
                return AjaxResponse.error("联系电话格式不正确");
            }
            if (NullUtil.isNullOrEmpty(record.getRegion())){
                return AjaxResponse.error("地区不能为空");
            }
            if (NullUtil.isNullOrEmpty(record.getAddress())){
                return AjaxResponse.error("详细地址不能为空");
            }
            if (record.getAddress().trim().getBytes().length > 150){
                return AjaxResponse.error("详细地址输入过长");
            }
            if (NullUtil.isNullOrEmpty(record.getLon()) || NullUtil.isNullOrEmpty(record.getLat())){
                return AjaxResponse.error("参数错误");
            }
            Map map = TencentLocationUtils.getLocation(record.getLon(), record.getLat());
            record.setProvince(map.get("province").toString());
            record.setProvinceCode(map.get("provinceCode").toString());
            record.setCity(map.get("city").toString());
            record.setCityCode(map.get("cityCode").toString());
            record.setCounty(map.get("district").toString());
            record.setUserId(weChantInfo.getUserId());
            userService.updateReceiveAddressInfo(record);
            return AjaxResponse.success("保存成功");
        } catch (Exception e) {
            log.error("保存收货人信息出错{}", e);
        }
        return AjaxResponse.error("保存失败");
    }

    /**
     * 删除收货地址
     * @param weChantInfo
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteReceiveAddress")
    public Object deleteReceiveAddress(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer id){
        try {
            ReceiveAddress record = userService.selectReceiveAddressInfo(id, weChantInfo.getUserId());
            if (record == null){
                return AjaxResponse.error("未找到相关记录");
            }
            userService.updateReceiveAddressStatus(id);
            return AjaxResponse.success("操作成功");
        } catch (Exception e) {
            log.error("删除收货地址出错{}", e);
        }
        return AjaxResponse.error("操作失败");
    }

    /**
     * 加载未读消息记录数
     * @param weChantInfo
     * @return
     */
    @RequestMapping(value = "loadUnreadNewsCount")
    public Object loadUnreadNewsCount(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo) {
        int systemCount = 0;
        int commentCount = 0;
        if (NullUtil.isNotNullOrEmpty(weChantInfo.getUserId())){
            systemCount = userService.selectUserRemindRecordByCount(weChantInfo.getUserId(), Constants.RELATION_TYPE_SYSTEM);
            commentCount = userService.selectUserRemindRecordByCount(weChantInfo.getUserId(), Constants.RELATION_TYPE_COMMENT);
        }
        Map map = new HashMap();
        map.put("systemCount", systemCount);
        map.put("commentCount", commentCount);
        return AjaxResponse.success(map);
    }

    /**
     * 加载消息列表
     * @param weChantInfo
     * @param type
     * @param request
     * @return
     */
    @RequestMapping(value = "loadNewByPage")
    public Object loadNewByPage(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer type, HttpServletRequest request){
        Page page = PageUtil.initPage(request);
        if (NullUtil.isNotNullOrEmpty(weChantInfo.getUserId())){
            switch (type.intValue()){
                case 1:
                    page = userService.selectUserSystemNoticeByPage(weChantInfo.getUserId(), page);
                    break;
                case 2:
                    page = userService.selectUserCommentByPage(weChantInfo.getUserId(), page);
                    break;
            }
        }
        if (null == page.getDataSource()){
            return AjaxResponse.error("未找到相关记录");
        }
        return AjaxResponse.success(page);
    }

    /**
     * 加载信息通知消息详情
     * @param weChantInfo
     * @param id
     * @return
     */
    @RequestMapping(value = "loadSystemNoticeDetails")
    public Object loadSystemNoticeDetails(@SessionScope("weChantInfo") ViewWeChantInfo weChantInfo, Integer id) {
        if (NullUtil.isNotNullOrEmpty(weChantInfo.getUserId())){
            ViewUserSystemNotice info = userService.selectUserSystemNotice(id, weChantInfo.getUserId());
            // 查询消息阅读量
            Integer readCount = userService.countUserSystemNoticeByRead(info.getRelationId());
            Map map = new HashMap();
            map.put("info", info);
            map.put("readCount", readCount.intValue() + 1030);
            return AjaxResponse.success(map);
        }
        return AjaxResponse.error("未找到相关信息");
    }
}
