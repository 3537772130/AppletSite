package com.applet.common.mapper;

import com.applet.common.entity.ViewUserAppletRecommendGoods;
import com.applet.common.entity.ViewUserAppletRecommendGoodsExample;
import java.util.List;

public interface ViewUserAppletRecommendGoodsMapper {
    long countByExample(ViewUserAppletRecommendGoodsExample example);

    List<ViewUserAppletRecommendGoods> selectByExample(ViewUserAppletRecommendGoodsExample example);
}