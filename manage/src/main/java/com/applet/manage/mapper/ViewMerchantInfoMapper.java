package com.applet.manage.mapper;

import com.applet.manage.entity.ViewMerchantInfo;
import com.applet.manage.entity.ViewMerchantInfoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewMerchantInfoMapper {
    long countByExample(ViewMerchantInfoExample example);

    List<ViewMerchantInfo> selectByExample(ViewMerchantInfoExample example);
}
