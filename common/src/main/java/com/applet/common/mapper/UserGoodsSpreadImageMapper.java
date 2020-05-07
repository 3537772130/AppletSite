package com.applet.common.mapper;

import com.applet.common.entity.UserGoodsSpreadImage;
import com.applet.common.entity.UserGoodsSpreadImageExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserGoodsSpreadImageMapper {
    long countByExample(UserGoodsSpreadImageExample example);

    int deleteByExample(UserGoodsSpreadImageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserGoodsSpreadImage record);

    int insertSelective(UserGoodsSpreadImage record);

    List<UserGoodsSpreadImage> selectByExample(UserGoodsSpreadImageExample example);

    UserGoodsSpreadImage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserGoodsSpreadImage record, @Param("example") UserGoodsSpreadImageExample example);

    int updateByExample(@Param("record") UserGoodsSpreadImage record, @Param("example") UserGoodsSpreadImageExample example);

    int updateByPrimaryKeySelective(UserGoodsSpreadImage record);

    int updateByPrimaryKey(UserGoodsSpreadImage record);
}
