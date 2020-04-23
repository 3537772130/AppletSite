package com.applet.common.mapper;

import com.applet.common.entity.UserAppletRecommendGoods;
import com.applet.common.entity.UserAppletRecommendGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAppletRecommendGoodsMapper {
    long countByExample(UserAppletRecommendGoodsExample example);

    int deleteByExample(UserAppletRecommendGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserAppletRecommendGoods record);

    int insertSelective(UserAppletRecommendGoods record);

    List<UserAppletRecommendGoods> selectByExample(UserAppletRecommendGoodsExample example);

    UserAppletRecommendGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserAppletRecommendGoods record, @Param("example") UserAppletRecommendGoodsExample example);

    int updateByExample(@Param("record") UserAppletRecommendGoods record, @Param("example") UserAppletRecommendGoodsExample example);

    int updateByPrimaryKeySelective(UserAppletRecommendGoods record);

    int updateByPrimaryKey(UserAppletRecommendGoods record);
}