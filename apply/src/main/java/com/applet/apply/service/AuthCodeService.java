package com.applet.apply.service;

import com.applet.apply.entity.AuthCode;
import com.applet.apply.entity.AuthCodeExample;
import com.applet.apply.entity.SmsTemplate;
import com.applet.apply.mapper.AuthCodeMapper;
import com.applet.apply.mapper.SmsTemplateMapper;
import com.applet.apply.util.EnumUtil;
import com.applet.apply.util.NullUtil;
import com.applet.apply.util.Constants;
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
    @Autowired
    private SmsTemplateMapper smsTemplateMapper;

    /**
     * 查询短信模板
     *
     * @param type
     * @return
     */
    public SmsTemplate selectSmsTemplateByType(String type, String channel) {
//        SmsTemplateExample example = new SmsTemplateExample();
//        example.createCriteria().andTypeEqualTo(type).andStatusEqualTo(true);
//        List<SmsTemplate> list = smsTemplateMapper.selectByExample(example);
//        if (NullUtil.isNotNullOrEmpty(list)) {
//            return list.get(0);
//        }
        return null;
    }

    /**
     * 查询10分钟内最新的验证码
     *
     * @param mobile
     * @return
     */
    public AuthCode selectAuthCodeByMobile(String mobile) {
        JDateTime time = new JDateTime(new Date());
        time.setMinute(-10);
        AuthCodeExample example = new AuthCodeExample();
        example.setOrderByClause("id desc");
        example.createCriteria()
                .andMobileEqualTo(mobile)
                .andSendTimeGreaterThanOrEqualTo(time.convertToDate());
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
     * 添加验证码发送记录
     *
     * @param id
     * @param mobile
     * @param type
     * @param code
     * @param ip
     */
    public void addAuthCode(Integer id, String mobile, String type, String code, String channel, String ip) {
        AuthCode authCode = new AuthCode();
        authCode.setUserId(id);
        authCode.setMobile(mobile);
        authCode.setAuthType(type);
        authCode.setAuthCode(code);
        JDateTime time = new JDateTime(new Date());
        authCode.setSendTime(time.convertToDate());
        authCode.setOverTime(time.addMinute(10).convertToDate());
        authCode.setChannel(channel);
        authCode.setIpAddress(ip);
        authCodeMapper.insertSelective(authCode);
    }


}
