package com.applet.common.mapper;

import com.applet.common.entity.ViewUserAppletRecommendGoods;
import com.applet.common.entity.ViewUserAppletRecommendGoodsExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewUserAppletRecommendGoodsMapper {
    long countByExample(ViewUserAppletRecommendGoodsExample example);

    List<ViewUserAppletRecommendGoods> selectByExample(ViewUserAppletRecommendGoodsExample example);
}
