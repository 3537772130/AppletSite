package com.applet.user.service;

import com.applet.common.entity.AuthCode;
import com.applet.common.entity.AuthCodeExample;
import com.applet.common.entity.SmsTemplate;
import com.applet.common.entity.SmsTemplateExample;
import com.applet.common.mapper.AuthCodeMapper;
import com.applet.common.mapper.SmsTemplateMapper;
import com.applet.common.util.NullUtil;
import com.applet.common.util.aliyun.SmsUtil;
import com.applet.common.util.enums.SMSType;
import jodd.datetime.JDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 短信验证码
 * Created by zhouhuahu on 2018/7/17.
 */
@Slf4j
@SuppressWarnings("ALL")
@Service
public class AuthCodeService {
    @Autowired
    private AuthCodeMapper authCodeMapper;
    @Autowired
    private SmsTemplateMapper smsTemplateMapper;

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

    /**
     * 查询短信模板
     *
     * @param type
     * @return
     */
    public SmsTemplate selectSmsTemplateByType(String type, String channel) {
        SmsTemplateExample example = new SmsTemplateExample();
        example.createCriteria().andTypeEqualTo(type).andChannelEqualTo(channel).andStatusEqualTo(true);
        List<SmsTemplate> list = smsTemplateMapper.selectByExample(example);
        if (NullUtil.isNotNullOrEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 发送验证码
     *
     * @param authCode
     * @return
     */
    public Boolean sendCode(AuthCode authCode) {
        try {
            SmsTemplate template = selectSmsTemplateByType(authCode.getAuthType(), authCode.getChannel());
            String param = "";
            if (SMSType.BIND_APPLET.toString().equals(authCode.getAuthType())){
                param = "{\"userName\":\"" + authCode.getMobile() + "\",\"operate\":\"" + authCode.getRemark() + "\",\"code\":\"" + authCode.getAuthCode() + "\"}";
            } else if (SMSType.UPDATE_PASS.toString().equals(authCode.getAuthType())){
                param = "{\"code\":\"" + authCode.getAuthCode() + "\"}";
            } else if (SMSType.REGISTER_ACCOUNT.toString().equals(authCode.getAuthType())){
                param = "{\"code\":\"" + authCode.getAuthCode() + "\"}";
            }  else {
                log.error("未匹配到相关验证码类型.......");
                return false;
            }
            SmsUtil.sendSms(authCode.getMobile(), template.getSingName(), template.getCode(), param);
            return true;
        } catch (Exception e) {
            log.error("发送验证码出错");
        }
        return false;
    }

}
