package com.applet.apply.mapper;

import com.applet.apply.entity.AuthCode;
import com.applet.apply.entity.AuthCodeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthCodeMapper {
    long countByExample(AuthCodeExample example);

    int deleteByExample(AuthCodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AuthCode record);

    int insertSelective(AuthCode record);

    List<AuthCode> selectByExample(AuthCodeExample example);

    AuthCode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AuthCode record, @Param("example") AuthCodeExample example);

    int updateByExample(@Param("record") AuthCode record, @Param("example") AuthCodeExample example);

    int updateByPrimaryKeySelective(AuthCode record);

    int updateByPrimaryKey(AuthCode record);
}
