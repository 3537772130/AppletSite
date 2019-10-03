package com.applet.user.mapper;

import com.applet.user.entity.GoodsSpecs;
import com.applet.user.entity.GoodsSpecsExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsSpecsMapper {
    long countByExample(GoodsSpecsExample example);

    int deleteByExample(GoodsSpecsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsSpecs record);

    int insertSelective(GoodsSpecs record);

    List<GoodsSpecs> selectByExample(GoodsSpecsExample example);

    GoodsSpecs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsSpecs record, @Param("example") GoodsSpecsExample example);

    int updateByExample(@Param("record") GoodsSpecs record, @Param("example") GoodsSpecsExample example);

    int updateByPrimaryKeySelective(GoodsSpecs record);

    int updateByPrimaryKey(GoodsSpecs record);
}
