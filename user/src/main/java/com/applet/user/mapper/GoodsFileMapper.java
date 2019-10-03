package com.applet.user.mapper;

import com.applet.user.entity.GoodsFile;
import com.applet.user.entity.GoodsFileExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
