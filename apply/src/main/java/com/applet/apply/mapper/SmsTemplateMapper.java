package com.applet.apply.mapper;

import com.applet.apply.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SmsTemplateMapper {
    long countByExample(SmsTemplateExample example);

    List<SmsTemplate> selectByExample(SmsTemplateExample example);

    SmsTemplate selectByPrimaryKey(Integer id);
}