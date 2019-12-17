package com.applet.apply.mapper;

import com.applet.apply.entity.WeChantInfo;
import com.applet.apply.entity.WeChantInfoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WeChantInfoMapper {
    long countByExample(WeChantInfoExample example);

    int deleteByExample(WeChantInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WeChantInfo record);

    int insertSelective(WeChantInfo record);

    List<WeChantInfo> selectByExample(WeChantInfoExample example);

    WeChantInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WeChantInfo record, @Param("example") WeChantInfoExample example);

    int updateByExample(@Param("record") WeChantInfo record, @Param("example") WeChantInfoExample example);

    int updateByPrimaryKeySelective(WeChantInfo record);

    int updateByPrimaryKey(WeChantInfo record);
}
