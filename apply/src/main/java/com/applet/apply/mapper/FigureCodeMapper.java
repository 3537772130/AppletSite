package com.applet.apply.mapper;

import com.applet.apply.entity.FigureCode;
import com.applet.apply.entity.FigureCodeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FigureCodeMapper {
    long countByExample(FigureCodeExample example);

    int deleteByExample(FigureCodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FigureCode record);

    int insertSelective(FigureCode record);

    List<FigureCode> selectByExample(FigureCodeExample example);

    FigureCode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FigureCode record, @Param("example") FigureCodeExample example);

    int updateByExample(@Param("record") FigureCode record, @Param("example") FigureCodeExample example);

    int updateByPrimaryKeySelective(FigureCode record);

    int updateByPrimaryKey(FigureCode record);
}
