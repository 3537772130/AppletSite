package com.applet.apply.mapper;

import com.applet.apply.entity.UserOperationLog;
import com.applet.apply.entity.UserOperationLogExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserOperationLogMapper {
    long countByExample(UserOperationLogExample example);

    int deleteByExample(UserOperationLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserOperationLog record);

    int insertSelective(UserOperationLog record);

    List<UserOperationLog> selectByExample(UserOperationLogExample example);

    UserOperationLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserOperationLog record, @Param("example") UserOperationLogExample example);

    int updateByExample(@Param("record") UserOperationLog record, @Param("example") UserOperationLogExample example);

    int updateByPrimaryKeySelective(UserOperationLog record);

    int updateByPrimaryKey(UserOperationLog record);
}
