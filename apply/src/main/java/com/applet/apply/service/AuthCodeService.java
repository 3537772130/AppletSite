package com.applet.apply.service;

import com.applet.apply.entity.*;
import com.applet.apply.mapper.*;
import com.applet.apply.util.NullUtil;
import jodd.datetime.JDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 短信验证码
 * Created by zhouhuahu on 2018/7/17.
 */
@Service
public class AuthCodeService {
    @Autowired
    private AuthCodeMapper authCodeMapper;

    /**
     * 查询10分钟内最新的验证码
     *
     * @param mobile
     * @return
     */
    public AuthCode selectAuthCodeByMobile(String mobile, String type) {
        AuthCodeExample example = new AuthCodeExample();
        example.setOrderByClause("id desc");
        example.createCriteria()
                .andMobileEqualTo(mobile)
                .andAuthTypeEqualTo(type)
                .andOverTimeGreaterThanOrEqualTo(new Date());
        List<AuthCode> list = authCodeMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 获取手机号当天短信验证码发送次数
     *
     * @param mobile 接收手机号
     * @param type   短信类型
     * @return
     */
    public Integer getTodaySendCodeCount(String mobile, String type) {
        JDateTime time1 = new JDateTime(new Date());
        time1.setHour(0).setMinute(0).setSecond(0);
        JDateTime time2 = new JDateTime(new Date());
        time2.setHour(23).setMinute(59).setSecond(59);
        AuthCodeExample example = new AuthCodeExample();
        example.createCriteria()
                .andMobileEqualTo(mobile)
                .andAuthTypeEqualTo(type)
                .andSendTimeGreaterThanOrEqualTo(time1.convertToDate())
                .andSendTimeLessThanOrEqualTo(time2.convertToDate());
        return (int) authCodeMapper.countByExample(example);
    }

    /**
     * 获取当前有效验证码数量
     * @param mobile
     * @param type
     * @return
     */
    public Integer selectVerifyCodeValidityCount(String mobile, String type){
        Date date = new Date();
        AuthCodeExample example = new AuthCodeExample();
        example.createCriteria()
                .andMobileEqualTo(mobile)
                .andAuthTypeEqualTo(type)
                .andSendTimeLessThanOrEqualTo(date)
                .andOverTimeGreaterThanOrEqualTo(date);
        return (int) authCodeMapper.countByExample(example);
    }

    /**
     * 添加验证码发送记录
     *
     * @param authCode
     */
    public void addAuthCode(AuthCode authCode) {
        authCodeMapper.insertSelective(authCode);
    }


}
