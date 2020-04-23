package com.applet.apply.service;

import com.applet.common.entity.*;
import com.applet.common.mapper.*;
import com.applet.common.util.*;
import com.applet.common.util.aliyun.SmsUtil;
import com.applet.common.util.enums.SMSType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mr.周华虎
 * Date: 2019/12/18
 * Time: 16:33
 * To change this template use File | Settings | File Templates.
 * Description: 短信发送服务类
 */
@SuppressWarnings("ALL")
@Service
public class SmsService {
    private final static Logger log = LoggerFactory.getLogger(SmsService.class);
    @Autowired
    private SmsTemplateMapper smsTemplateMapper;

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
            } else {
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
