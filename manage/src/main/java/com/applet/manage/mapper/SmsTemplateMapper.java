package com.applet.manage.mapper;

import com.applet.manage.entity.SmsTemplate;
import com.applet.manage.entity.SmsTemplateExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SmsTemplateMapper {
    long countByExample(SmsTemplateExample example);

    List<SmsTemplate> selectByExample(SmsTemplateExample example);

    SmsTemplate selectByPrimaryKey(Integer id);
}
