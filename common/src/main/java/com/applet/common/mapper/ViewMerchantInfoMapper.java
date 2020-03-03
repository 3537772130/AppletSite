package com.applet.common.mapper;

import com.applet.common.entity.ViewMerchantInfo;
import com.applet.common.entity.ViewMerchantInfoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewMerchantInfoMapper {
    long countByExample(ViewMerchantInfoExample example);

    List<ViewMerchantInfo> selectByExample(ViewMerchantInfoExample example);
}
