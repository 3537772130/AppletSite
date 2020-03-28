package com.applet.common.mapper;

import com.applet.common.entity.ViewUserSystemNotice;
import com.applet.common.entity.ViewUserSystemNoticeExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewUserSystemNoticeMapper {
    long countByExample(ViewUserSystemNoticeExample example);

    List<ViewUserSystemNotice> selectByExample(ViewUserSystemNoticeExample example);
}
