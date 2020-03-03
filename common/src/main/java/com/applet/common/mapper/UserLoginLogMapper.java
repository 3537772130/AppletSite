package com.applet.common.mapper;

import com.applet.common.entity.UserLoginLog;
import com.applet.common.entity.UserLoginLogExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserLoginLogMapper {
    long countByExample(UserLoginLogExample example);

    int deleteByExample(UserLoginLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserLoginLog record);

    int insertSelective(UserLoginLog record);

    List<UserLoginLog> selectByExample(UserLoginLogExample example);

    UserLoginLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserLoginLog record, @Param("example") UserLoginLogExample example);

    int updateByExample(@Param("record") UserLoginLog record, @Param("example") UserLoginLogExample example);

    int updateByPrimaryKeySelective(UserLoginLog record);

    int updateByPrimaryKey(UserLoginLog record);
}
