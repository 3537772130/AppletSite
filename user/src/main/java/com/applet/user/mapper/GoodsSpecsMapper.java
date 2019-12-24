package com.applet.user.mapper;

import com.applet.user.entity.GoodsSpecs;
import com.applet.user.entity.GoodsSpecsExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
