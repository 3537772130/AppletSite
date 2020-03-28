package com.applet.common.mapper;

import com.applet.common.entity.UserRemindRecord;
import com.applet.common.entity.UserRemindRecordExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRemindRecordMapper {
    long countByExample(UserRemindRecordExample example);

    int deleteByExample(UserRemindRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserRemindRecord record);

    int insertSelective(UserRemindRecord record);

    List<UserRemindRecord> selectByExample(UserRemindRecordExample example);

    UserRemindRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserRemindRecord record, @Param("example") UserRemindRecordExample example);

    int updateByExample(@Param("record") UserRemindRecord record, @Param("example") UserRemindRecordExample example);

    int updateByPrimaryKeySelective(UserRemindRecord record);

    int updateByPrimaryKey(UserRemindRecord record);
}
