package com.applet.common.mapper;

import com.applet.common.entity.GoodsFile;
import com.applet.common.entity.GoodsFileExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GoodsFileMapper {
    long countByExample(GoodsFileExample example);

    int deleteByExample(GoodsFileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsFile record);

    int insertSelective(GoodsFile record);

    List<GoodsFile> selectByExample(GoodsFileExample example);

    GoodsFile selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsFile record, @Param("example") GoodsFileExample example);

    int updateByExample(@Param("record") GoodsFile record, @Param("example") GoodsFileExample example);

    int updateByPrimaryKeySelective(GoodsFile record);

    int updateByPrimaryKey(GoodsFile record);
}
