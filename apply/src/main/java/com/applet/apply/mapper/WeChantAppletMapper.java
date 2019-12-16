package com.applet.apply.mapper;

import com.applet.apply.entity.WeChantApplet;
import com.applet.apply.entity.WeChantAppletExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WeChantAppletMapper {
    long countByExample(WeChantAppletExample example);

    int deleteByExample(WeChantAppletExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WeChantApplet record);

    int insertSelective(WeChantApplet record);

    List<WeChantApplet> selectByExample(WeChantAppletExample example);

    WeChantApplet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WeChantApplet record, @Param("example") WeChantAppletExample example);

    int updateByExample(@Param("record") WeChantApplet record, @Param("example") WeChantAppletExample example);

    int updateByPrimaryKeySelective(WeChantApplet record);

    int updateByPrimaryKey(WeChantApplet record);
}
