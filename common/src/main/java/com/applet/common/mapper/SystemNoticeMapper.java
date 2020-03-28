package com.applet.common.mapper;

import com.applet.common.entity.SystemNotice;
import com.applet.common.entity.SystemNoticeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SystemNoticeMapper {
    long countByExample(SystemNoticeExample example);

    int deleteByExample(SystemNoticeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SystemNotice record);

    int insertSelective(SystemNotice record);

    List<SystemNotice> selectByExample(SystemNoticeExample example);

    SystemNotice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SystemNotice record, @Param("example") SystemNoticeExample example);

    int updateByExample(@Param("record") SystemNotice record, @Param("example") SystemNoticeExample example);

    int updateByPrimaryKeySelective(SystemNotice record);

    int updateByPrimaryKey(SystemNotice record);
}
